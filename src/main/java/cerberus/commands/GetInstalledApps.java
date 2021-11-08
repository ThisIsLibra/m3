/*
 * Copyright (C) 2021 Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
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
package cerberus.commands;

import cerberus.bot.CerberusBot;

/**
 * This class handles the command that gathers all the package names of
 * installed applications to the C2
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class GetInstalledApps {

    /**
     * This class handles the command that gathers all the package names of
     * installed applications to the C2
     *
     * @param bot the bot
     */
    public GetInstalledApps(CerberusBot bot) {
        String logs = "";
        for (String packageName : bot.getPhone().getInstalledApplications()) {
            logs = logs + packageName + ":end:";
        }
        bot.getPhone().getSharedPreferences().write("logsApplications", logs);

        bot.addLog("Received the command to get a list of all installed applications and wrote them to the shared preference key named \"logsApplications\", from where they will be uploaded later on");
    }
}
