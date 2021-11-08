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
 * This class handles the decryption of the bot's file system (albeit that no
 * files were ever encrypted in this emulation)
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class DecryptFileSystem {

    /**
     * Handles the decryption command
     *
     * @param bot the bot
     * @param command the command
     */
    public DecryptFileSystem(AnubisBot bot, String command) {
        String key = bot.getEncryptionHandler().untag(command, "|decryptokey=", "|enddecrypt");

        String log = "Received the decrypt command with the following key: " + key;
        bot.addLog(log);

        bot.getPhone().getSharedPreferences().write("status", "decrypt");
        bot.getPhone().getSharedPreferences().write("key", key);

        log = "Changed the following shared preferences:\n"
                + "\t'status': 'decrypt'\n"
                + "\t'key' : " + key;
        bot.addLog(log);
    }
}
