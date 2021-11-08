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
 * Handles the command to open a specific activity
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class OpenActivity {

    /**
     * Handles the command to open a specific activity
     *
     * @param bot the bot
     * @param command the command
     */
    public OpenActivity(AnubisBot bot, String command) {
        String activity = bot.getEncryptionHandler().untag(command, "|openactivity=", "|endactivity");

        String log = "Received command to open an activity with the name: " + activity;
        bot.addLog(log);
    }
}
