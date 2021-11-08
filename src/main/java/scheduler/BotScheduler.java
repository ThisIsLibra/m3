/*
 * Copyright (C) 2021 Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package scheduler;

import bot.IBot;
import bot.IConfig;
import config.ConfigLoader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is used to schedule any amount of bots, based on a list of bot
 * configuration objects. The bots will be loaded in the given order, after
 * which they are sorted in chronologic order, starting with the bot which has
 * its next polling moment the earliest from the time when the scheduler is
 * executed. The list is sorted again after each bot emulation sequence.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class BotScheduler {

    /**
     * The provided list of bot configuration files are converted into bots,
     * after which they are scheduled. The bots are sorted in such a way that
     * the bot that is first due, is the first in the list, even if this moment
     * in time has already passed.<br>
     * <br>
     * Upon finishing the emulation of a specific bot, the list is sorted again.
     * This way, the bot that is first in line, is the one that needs to poll
     * the earliest in the future. If there is time between the moment the
     * sorting has finished, and the next bot, the scheduler will sleep.<br>
     * <br>
     * A known and unhandled edge case is a denial-of-service of sorts, where
     * the emulation of a specific bot is longer than the interval that is set.
     * As such, the same bot will get scheduled as the first to be executed and
     * thus (partially) blocking other bots from being emulated. Based on the
     * interval of bots in the wild, this is not an issue. However, if this
     * becomes an issue, a more rudimentary approach of simply iterating the
     * whole list can be used. Alternatively, one can add code to handle this
     * edge case in this scheduler.
     *
     * @param configs the list of configuration files for the bots that need to
     * be scheduled
     */
    public void schedule(List<IConfig> configs) {
        //Initialise the config loader
        ConfigLoader configLoader = new ConfigLoader();

        //Instantiate the list of bots, and load all bots into the list
        List<IBot> bots = configLoader.loadBots(configs);

        //Start the scheduling loop
        while (true) {
            try {
                /**
                 * The list can be empty for two reasons, which are given below.
                 *
                 * 1. No bots were loaded, as the config list was empty
                 *
                 * 2. All bots that were once added, have become inactive, and
                 * have thus been removed from the list of bots.
                 */
                if (bots.isEmpty()) {
                    System.out.println("[+]No active bots are present in the schedule");
                    return;
                }

                //Sort the bots in a chronological order
                Collections.sort(bots, new Comparator<IBot>() {
                    @Override
                    public int compare(IBot o1, IBot o2) {
                        return o1.getNextPollMoment().compareTo(o2.getNextPollMoment());
                    }
                });

                //The earlier check already made sure that the list was not empty, meaning there is at least one entry available
                IBot bot = bots.get(0);

                //If this bot is inactive, it is removed from the list, and the next bot is loaded
                if (bot.isActive() == false) {
                    bots.remove(bot);
                    continue;
                }

                /**
                 * The selected bot is the first one that needs to be emulated,
                 * but that does not mean that it should be done right now. Only
                 * if the moment in time when it should execute has passed, it
                 * should do so.
                 *
                 * If it should not execute yet, it should wait until that
                 * moment in time has just passed. This is done by sleeping for
                 * ten seconds, before the next iteration in this loop starts,
                 * until the bot's scheduled moment just passed.
                 *
                 * In the meantime, the user is kept up to date on what the
                 * framework is doing via the standard output, where it lists
                 * the time until the next bot's execution.
                 *
                 */
                if (LocalDateTime.now().isAfter(bot.getNextPollMoment())) {
                    if (bot.isRegistered() == false) {
                        bot.register();
                    } else {
                        bot.poll();
                    }
                    bot.setNextPollMoment(LocalDateTime.now().plusSeconds(bot.getInterval()));

                    bot.save();
                } else {
                    LocalDateTime now = LocalDateTime.now();
                    long differenceInSeconds = ChronoUnit.SECONDS.between(now, bot.getNextPollMoment());
                    //Calculate the amount of seconds
                    long seconds = differenceInSeconds % 60;
                    //Calculate the amount of minutes
                    long minutes = (differenceInSeconds / 60) % 60;
                    //Calculate the amount of hours
                    long hours = (differenceInSeconds / (60 * 60)) % 24;
                    String time = String.format("%03d:%02d:%02d", hours, minutes, seconds);

                    System.out.println("[+]No bot has to be emulated right now, waiting for the next bot's timing in " + time + " (hhh:mm:ss)");

                    //Ten seconds
                    long sleep = 10 * 1000;
                    if (differenceInSeconds < 10) {
                        sleep = differenceInSeconds * 1000;
                    }
                    //Sleep a minimum of one second, avoiding spam in the console
                    if (sleep < 1000) {
                        sleep = 1000;
                    }
                    Thread.sleep(sleep);

                }
            } catch (InterruptedException e) {
                System.out.println("[+]The emulator has been interrupted whilst waiting for the the next bot, as is printed above");
            }
        }
    }
}
