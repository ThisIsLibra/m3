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
import device.enums.Permissions;

/**
 * This class handles the request for the accessibility service
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class StartAccessibilityService {

    /**
     * This class handles the request for the accessibility service
     *
     * @param bot the bot
     */
    public StartAccessibilityService(AnubisBot bot) {
        String log = "Received command to start the accessibility service";
        bot.addLog(log);

        String permission = bot.getPhone().getSharedPreferences().read("startRequest");

        if (permission.contains("Access=0")) {
            permission = permission.replace("Access=0", "Access=1");
            bot.getPhone().getSharedPreferences().write("startRequest", permission);

            log = "Replaced 'Access=0' to 'Access=1' in shared preference key 'startRequests'";
            bot.addLog(log);

            if (bot.getPhone().getPermissions().contains(Permissions.BIND_ACCESSIBILITY_SERVICE)) {
                log = "The bot was already granted the accessibility service permission";
            } else {
                bot.getPhone().getPermissions().add(Permissions.BIND_ACCESSIBILITY_SERVICE);
                log = "Granted the accessibility service permission to the bot";
            }
            bot.addLog(log);

        }
    }
}
