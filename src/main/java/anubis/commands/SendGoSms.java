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
 * Handles the command to send a SMS to a given number
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class SendGoSms {

    /**
     * Handles the command to send a SMS to a given number
     *
     * @param bot the bot
     * @param command the command
     * @throws Exception if the HTTP request fails, or if the command is
     * malformed
     */
    public SendGoSms(AnubisBot bot, String command) throws Exception {
        String number = bot.getEncryptionHandler().untag(command, "|number=", "|text=");
        String text = command.split("text=")[1];

        String log = "Received a command to send a SMS to " + number + " with \"" + text + "\" as text";
        bot.addLog(log);

        Connector connector = bot.getConnector();
        String url = bot.getServer() + "/" + bot.getServerFolder() + "/a6.php";

        String parameter = "p=" + bot.getEncryptionHandler().encrypt(bot.getPhone().getImei() + "|Outgoing SMS" + '\n' + "Number: " + number + '\n' + "Text: " + text + '\n' + "|");
        String response = connector.post(url, parameter);

        log = "Responded to the server that the SMS was sent succesfully, to which the server replied with: \"" + response + "\"";
        bot.addLog(log);

    }
}
