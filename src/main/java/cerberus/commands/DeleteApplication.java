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
 * This class handles the deletion of a given application
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class DeleteApplication {

    /**
     * This class handles the deletion of a given application
     *
     * @param bot the bot
     * @param packageName the package name of the application that is to be
     * removed
     */
    public DeleteApplication(CerberusBot bot, String packageName) {
        String log = "Received the command to delete the following application: " + packageName;
        bot.addLog(log);

        if (bot.getPhone().getInstalledApplications().contains(packageName)) {
            bot.getPhone().getInstalledApplications().remove(packageName);
            log = "The given package, named\"" + packageName + "\", was installed on this device and has been removed";
            bot.addLog(log);
        }

        bot.getPhone().getSharedPreferences().write("autoClick", "1");
        bot.getPhone().getSharedPreferences().write("killApplication", packageName);
    }
}
