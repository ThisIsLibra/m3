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
import org.apache.commons.codec.binary.Base64;

/**
 * Handles the download of the Play Protect module
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class GetPlayProtectApk {

    /**
     * Handles the download of the Play Protect module
     *
     * @param bot the bot
     * @throws Exception if a HTTP request fails
     */
    public GetPlayProtectApk(AnubisBot bot) throws Exception {
        String fileName = "playProtect.apk";

        //Instantly return if the play protect APK is already present on the local file system
        if (bot.getLocalFileSystemManager().exists(fileName)) {
            return;
        }

        String url = bot.getServer() + "/" + bot.getServerFolder() + "/a14.php";
        String parameter = "p=1";

        String log = "Attempting to download the Play Protect APK module";
        bot.addLog(log);

        String response = bot.getConnector().post(url, parameter);
        String noTags = bot.getEncryptionHandler().untag(response);
        String decrypted = bot.getEncryptionHandler().decrypt(noTags);

        if (decrypted.length() > 1000) {
            byte[] apk = new Base64().decode(decrypted);
            String path = bot.getLocalFileSystemManager().writeFile(apk, fileName);
            log = "Downloaded the Play Protect APK module (with a size of " + apk.length + " bytes)\n"
                    + "\tThe file is located at \"" + path + "\"";
        } else {
            log = "Download failed as the length is less than 1000 bytes, per the bot's original code";
        }
        bot.addLog(log);
    }
}
