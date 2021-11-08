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
 * Handles the command to show a message to the user
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class ShowMessageBox {

    /**
     * Handles the command to display a message to the user
     *
     * @param bot the bot
     * @param command the command
     */
    public ShowMessageBox(AnubisBot bot, String command) {
        String title = bot.getEncryptionHandler().untag(command, "|title=", "|text=");
        String text = command.split("text=")[1];

        String log = "Received a command to show a messagebox with:\n"
                + "\tTitle: " + title + "\n"
                + "\tText:  " + text;
        bot.addLog(log);
    }
}
