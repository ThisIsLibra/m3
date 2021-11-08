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
 * This class handles the stopping of the SOCKS proxy
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class StopSocks5 {

    /**
     * This class handles the stopping of the SOCKs proxy
     *
     * @param bot
     */
    public StopSocks5(AnubisBot bot) {
        String log = "Received command to stop the SOCKS5 proxy";
        bot.addLog(log);

        bot.getPhone().getSharedPreferences().write("socks", "stop");

        log = "Changed the value of \"socks\" in the shared preferences to \"stop\"";
        bot.addLog(log);
    }
}
