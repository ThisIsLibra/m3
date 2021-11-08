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
 * This class handles the start of the RAT capability of the bot
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class StartRat {

    /**
     * This class handles the start of the RAT capability of the bot
     *
     * @param bot the bot
     * @param command the command
     */
    public StartRat(AnubisBot bot, String command) {
        String websocket = bot.getEncryptionHandler().untag(command, "|endrat=", "|endurl");

        String log = "Received command to start the RAT with websocket: " + websocket;
        bot.addLog(log);

        bot.getPhone().getSharedPreferences().write("websocket", websocket);

        log = "Changed 'websocket' to " + websocket + " in the shared preferences";
        bot.addLog(log);
    }
}
