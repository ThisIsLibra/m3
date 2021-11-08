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
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This class handles the execution of a given USSD command
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class StartUssd {

    /**
     * This class handles the execution of a given USSD command
     *
     * @param bot the bot
     * @param command the command
     * @throws URISyntaxException if the given USSD is not a valid URI
     * @throws Exception if the HTTP request fails
     */
    public StartUssd(AnubisBot bot, String command) throws Exception {
        String ussd = bot.getEncryptionHandler().untag(command, "|ussd=", "|endUssD");
        ussd = ussd.replace("AAA", "#");

        String log = "Recieved USSD command: \"" + ussd + "\"";
        bot.addLog(log);

        ussd = new URI(ussd).toString();
        String url = bot.getServer() + "/" + bot.getServerFolder() + "/a6.php";

        Connector connector = bot.getConnector();
        String parameter = "p=" + bot.getEncryptionHandler().encrypt(bot.getPhone().getImei() + "|Request USSD is executed (" + ussd + ")|");

        log = "Responded to the server that the USSD command (" + ussd + ") has been executed successfully";
        bot.addLog(log);

        connector.post(url, parameter);
    }
}
