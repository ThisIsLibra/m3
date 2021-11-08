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
 * This class handles the commands to start and stop forwarding calls
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class CallForwarder {

    /**
     * The number to forward calls to, as received from the C2 server
     */
    private String number;

    /**
     * This constructor is used to start the forwarding of calls. The number to
     * forward the calls to is also stored in the phone's shared preferences,
     * under the "forwardingNumber" key
     *
     * @param bot the bot
     * @param command the command to handle
     */
    public CallForwarder(AnubisBot bot, String command) {
        number = bot.getEncryptionHandler().untag(command, "startforward=", "|endforward");
        bot.getPhone().getSharedPreferences().write("forwardingNumber", number);
        String log = "Started forwarding incoming calls to " + number;
        bot.addLog(log);
    }

    /**
     * This constructor is used to stop the forwarding of the calls
     *
     * @param bot the bot
     */
    public CallForwarder(AnubisBot bot) {
        if (bot.getPhone().getSharedPreferences().read("forwardingNumber") == null || bot.getPhone().getSharedPreferences().read("forwardingNumber").isEmpty()) {
            number = "[no-defined-number]";
        }
        String log = "Stopped forwarding incoming calls to " + number;
        bot.addLog(log);
    }
}
