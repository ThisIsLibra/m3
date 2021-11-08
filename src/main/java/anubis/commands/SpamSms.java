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
 * Handles the command to spam all contacts with a given message
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class SpamSms {

    /**
     * Handles the command to spam all contacts with a given message
     *
     * @param bot the bot
     * @param command the command
     */
    public SpamSms(AnubisBot bot, String command) {
        String message = bot.getEncryptionHandler().untag(command, "|spam=", "|endspam");

        String log = "Received command to spam all contacts with:\n"
                + "\t" + message;
        bot.addLog(log);

        bot.getPhone().getSharedPreferences().write("textSPAM", message);
        bot.getPhone().getSharedPreferences().write("spamSMS", "start");
        bot.getPhone().getSharedPreferences().write("indexSMSSPAM", "");

        log = "Wrote three values to the shared preferences:\n"
                + "\t'textSPAM': " + message + "\n"
                + "\t'spamSMS' : 'start'\n"
                + "\t'indexSMSSPAM' : \"\"";
        bot.addLog(log);
    }
}
