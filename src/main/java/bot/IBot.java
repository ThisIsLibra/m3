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
package bot;

import java.time.LocalDateTime;

/**
 * This interface is used to have a single interface for all implemented bot
 * families, regardless of how many are implemented. The functions that are
 * defined here are required for the scheduler to work with the bots.<br>
 * <br>
 * It also forces the programmer of any additional family implementations to
 * adhere to the same format. The abstract <em>bot.Bot</em> class contains the
 * backbone of any new added bot, meaning the programmer only has to implement
 * the bot's logic, and the framework will take care of the rest.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public interface IBot {

    /**
     * This function registers the bot to the C2. If the registration is
     * successful, the bot's <em>registered</em> boolean is set to true.
     */
    public void register();

    /**
     * This method is used to poll the C2 at the previously specified interval.
     * As such, incoming commands will be handled by this function, which can
     * use and call different classes and functions, depending on the bot's
     * implementation.
     */
    public void poll();

    /**
     * This function convers the bot into an IConfig object, which is then saved
     * into the local file system's folder as <em>config.json</em>.
     */
    public void save();

    /**
     * Adds the given string to the log. The log is printed to the standard
     * output, and to the <em>log.txt</em> file in the bot's local file system
     *
     * @param log the log content to add to the log
     */
    public void addLog(String log);

    /**
     * Gets the bot's registration status
     *
     * @return true if the bot is registered at the given C2, false if not
     */
    public boolean isRegistered();

    /**
     * Sets the bot's registration status
     *
     * @param status the status to set
     */
    public void setRegistration(boolean status);

    /**
     * Gets the bot's interval in seconds
     *
     * @return the interval in seconds
     */
    public int getInterval();

    /**
     * Sets the bot's interval in seconds
     *
     * @param interval the interval to set
     */
    public void setInterval(int interval);

    /**
     * Gets the next polling moment
     *
     * @return the next polling moment
     */
    public LocalDateTime getNextPollMoment();

    /**
     * Sets the next polling moment
     *
     * @param nextPollMoment the next moment to poll
     */
    public void setNextPollMoment(LocalDateTime nextPollMoment);

    /**
     * Defines if the bot is active
     *
     * @return true if the bot is active, false if not
     */
    public boolean isActive();

    /**
     * Sets the bot's activity status
     *
     * @param status true if the bot is active, false if not
     */
    public void setActive(boolean status);
}
