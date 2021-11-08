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
 * Handles the command that updates the C2 URL
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class ReplaceUrl {

    /**
     * Handles the command that updates the C2 URL
     *
     * @param bot the bot
     * @param command the command
     */
    public ReplaceUrl(AnubisBot bot, String command) {
        String url = bot.getEncryptionHandler().untag(command, "|replaceurl=", "|endurl");
        String log = "Received new command to replace the bot's C2\n"
                + "\tNew C2 URL: " + url;
        bot.addLog(log);
        bot.getPhone().getSharedPreferences().write("url", url);
        bot.getPhone().getSharedPreferences().write("urls", url);
        log = "Wrote the new C2 URL to the shared preference keys named \"url\" and \"'urls\"";
        bot.addLog(log);

        bot.setServer(url);
    }
}
