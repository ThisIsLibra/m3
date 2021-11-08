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
 * Handles the SOCKS-5 proxy command
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class Socks5 {

    /**
     * Handles the SOCKS-5 proxy command
     *
     * @param bot the bot
     * @param command the command
     * @throws Exception if the HTTP request fails
     */
    public Socks5(AnubisBot bot, String command) throws Exception {
        String host = bot.getEncryptionHandler().untag(command, "|sockshost=", "|user=");
        String user = bot.getEncryptionHandler().untag(command, "|user=", "|pass=");
        String password = bot.getEncryptionHandler().untag(command, "|pass=", "|port=");
        String port = bot.getEncryptionHandler().untag(command, "|port=", "|endssh");

        String log = "Received command to start a SOCKS5 server with the following details:\n"
                + "\tHost:" + host + "\n"
                + "\tUser: " + user + "\n"
                + "\tPassword: " + password + "\n"
                + "\tPort: " + port;
        bot.addLog(log);

        bot.getPhone().getSharedPreferences().write("socks", "");
        log = "Set \"socks\" in the shared preferences to \"\"";
        bot.addLog(log);

        //sleep for 8 seconds in-between updates, continue until the stop sign is given
        Connector connector = bot.getConnector();
        String url = bot.getServer() + "/" + bot.getServerFolder() + "/a6.php";
        String parameter = "p=" + bot.getEncryptionHandler().encrypt(bot.getPhone().getImei() + "|Working Proxy Server, Connection: ssh -L " + port + ":127.0.0.1:" + port + " " + user + "@" + host);
        connector.post(url, parameter);

        log = "Responded to the server with all details";
        bot.addLog(log);
    }
}
