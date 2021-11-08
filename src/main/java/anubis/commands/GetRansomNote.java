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
 * Gets the ransom note from the server
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class GetRansomNote {

    /**
     * Gets the ransom note from the server
     *
     * @param bot the bot
     * @throws Exception if a HTTP request fails
     */
    public GetRansomNote(AnubisBot bot) throws Exception {
        String url = bot.getServer() + "/" + bot.getServerFolder() + "/a11.php";
        String parameter = "p=1";

        String log = "Obtaining the ransom note from the server";
        bot.addLog(log);

        String response = bot.getConnector().post(url, parameter);
        String noTags = bot.getEncryptionHandler().untag(response);
        String decrypted = bot.getEncryptionHandler().decrypt(noTags);

        String path = bot.getLocalFileSystemManager().writeFile(decrypted.getBytes(), "ransomNote.html");
        log = "The ransom note has been saved to \"" + path + "\"";
        bot.addLog(log);
    }

}
