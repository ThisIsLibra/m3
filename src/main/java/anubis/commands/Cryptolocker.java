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
 * This class handles the cryptolocker command
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class Cryptolocker {

    /**
     * This class handles the cryptolocker command
     *
     * @param bot the bot
     * @param command the command
     */
    public Cryptolocker(AnubisBot bot, String command) {
        String[] values = bot.getEncryptionHandler().untag(command, "|cryptokey=", "|endcrypt").split("/:/");
        if (values.length == 3) {
            String key = values[0];
            String amount = values[1];
            String btc = values[2];

            String log = "Received command to cryptolock the device:\n"
                    + "\tKey: " + key + "\n"
                    + "\tAmount: " + amount + "\n"
                    + "\tBTC: " + btc;
            bot.addLog(log);

            //store lock_amount, lock_btc, status (equals crypt), and the key in the shared preferences
            bot.getPhone().getSharedPreferences().write("lock_amount", amount);
            bot.getPhone().getSharedPreferences().write("lock_btc", btc);
            bot.getPhone().getSharedPreferences().write("status", "crypt");
            bot.getPhone().getSharedPreferences().write("key", key);

            log = "Changed the following shared preferences:\n"
                    + "\t'lock_amount' : " + amount + "\n"
                    + "\t'lock_btc' : " + btc + "\n"
                    + "\t'status' : 'crypt'\n"
                    + "\t'key' : " + key;
            bot.addLog(log);
        } else {
            bot.addLog("Failed to properly handle the cryptolocker command, as the incoming command was malformed");
        }
    }
}
