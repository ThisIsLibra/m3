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
 * This class handles the incoming request to obtain all required permissions
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class StartPermissions {

    /**
     * This class handles the incoming request to obtain all required
     * permissions
     *
     * @param bot the bot
     */
    public StartPermissions(AnubisBot bot) {
        String log = "Received command to start requesting the permissions";
        bot.addLog(log);

        String permission = bot.getPhone().getSharedPreferences().read("startRequest");

        if (permission.contains("Perm=0")) {
            permission = permission.replace("Perm=0", "Perm=1");
            bot.getPhone().getSharedPreferences().write("startRequest", permission);

            log = "Replaced \"Perm=0\" to \"'Perm=1\" in shared preference key \"startRequests\"";
            bot.addLog(log);
        }
    }
}
