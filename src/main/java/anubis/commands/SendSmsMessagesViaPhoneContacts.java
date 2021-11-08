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

import bot.network.Connector;
import anubis.bot.AnubisBot;
import device.Contact;

/**
 * This class handles the command to send a given text message to all contacts
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class SendSmsMessagesViaPhoneContacts {

    /**
     * This class handles the command to send a given text message to all
     * contacts
     *
     * @param bot the bot
     * @param command the command
     * @throws Exception if a HTTP request fails
     */
    public SendSmsMessagesViaPhoneContacts(AnubisBot bot, String command) throws Exception {
        String message = bot.getEncryptionHandler().untag(command, "|telbookgotext=", "|endtextbook");

        String log = "Received command to send a text message to all contacts:\n"
                + "\t" + message;
        bot.addLog(log);

        /**
         * If the mass sending is successful, the following message is added to
         * the bot's log on the C2:
         *
         *
         * "p=" + SF.trafEnCr(SF.ID_B(this) + "|The dispatch was successful, " +
         * schet_sws + " SMS sent|"));
         *
         * Otherwise, the following is added:
         *
         * "p=" + SF.trafEnCr(SF.ID_B(this) + "|Error sending SMS, maybe there
         * are no permission to send!|"));
         *
         * Both are using gate 4, aka a6.php
         */
        int sentSmsCount = 0; //amount of SMS sent

        /**
         * Phone numbers are mandatory to match several criteria, as are given
         * in the if-statement within th eloop
         */
        for (Contact contact : bot.getPhone().getContacts()) {
            String phoneNumber = contact.getNumber();
            if ((!phoneNumber.contains("*")) && (!phoneNumber.contains("#")) && (phoneNumber.length() > 7)) {
                sentSmsCount++;
            }
        }

        String url = bot.getServer() + "/" + bot.getServerFolder() + "/a6.php";
        Connector connector = bot.getConnector();
        String parameter = "p=" + bot.getEncryptionHandler().encrypt(bot.getPhone().getImei() + "|The dispatch was successful, " + sentSmsCount + " SMS sent|");
        connector.post(url, parameter);

        log = "Responded to the server that " + sentSmsCount + " message(s) were ssent succesfully";
        bot.addLog(log);
    }
}
