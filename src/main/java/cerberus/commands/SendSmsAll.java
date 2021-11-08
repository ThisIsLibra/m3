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
package cerberus.commands;

import cerberus.bot.CerberusBot;
import device.Contact;

/**
 * This class handles the command to send a given text message to all contacts
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class SendSmsAll {

    /**
     * This class handles the command to send a given text message to all
     * contacts
     *
     * @param bot the bot
     * @param text the text message's body
     */
    public SendSmsAll(CerberusBot bot, String text) {
        String log = "Received the command to send a text message to all contacts. The given message is:\n"
                + "\t" + text;
        bot.addLog(log);

        for (Contact contact : bot.getPhone().getContacts()) {
            String logSMS = "Output SMS:" + contact.getNumber() + " text:" + text + "::endLog::";
            bot.getPhone().getSharedPreferences().append("LogSMS", logSMS);
        }

        bot.addLog("Wrote the SMS sending output to the shared preference key named \"LogSMS\", which will be uploaded later on");
    }
}
