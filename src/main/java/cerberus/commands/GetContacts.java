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
 * This class handles the command that uploads all contacts to the C2 server
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class GetContacts {

    /**
     * This class handles the command that uploads all contacts to the C2 server
     *
     * @param bot the bot
     */
    public GetContacts(CerberusBot bot) {
        String phoneNumber = "";

        for (Contact contact : bot.getPhone().getContacts()) {
            String number = contact.getNumber();
            String name = contact.getName();
            if ((!number.contains("*")) && (!number.contains("#")) && (number.length() > 6) && (!phoneNumber.contains(number))) {
                phoneNumber = phoneNumber + number + " / " + name + ":end:";
            }
        }

        bot.getPhone().getSharedPreferences().write("logsContacts", phoneNumber);

        bot.addLog("Received command to get all contacts and wrote them to the shared preference key named \"logsContacts\", from where they will be uploaded later on");
    }
}
