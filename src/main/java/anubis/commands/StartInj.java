/*
 * Copyright (C) 2020 Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
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
package anubis.commands;

import anubis.bot.AnubisBot;

/**
 * This class handles the command to start the injection for a (group of)
 * targets
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class StartInj {

    /**
     * This class handles the command to tart the injection for a (group of)
     * targets
     *
     * @param bot the bot
     * @param command
     */
    public StartInj(AnubisBot bot, String command) {
        String lock_inj = bot.getEncryptionHandler().untag(command, "|startinj=", "|endstartinj");

        String log = "Command recieved to inject for " + lock_inj; //can be found in ActivityInjection.java in the bot, which uses these values to determine what to steal
        bot.addLog(log);

        bot.getPhone().getSharedPreferences().write("lock_inj", lock_inj);

        log = "Wrote the injection request into the shared preferences under 'lock_inj'";
        bot.addLog(log);
    }
}
