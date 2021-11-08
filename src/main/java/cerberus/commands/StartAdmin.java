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
 * This class handles the command that sets the setting to request the
 * administrative privileges
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class StartAdmin {

    /**
     * This class handles the command that sets the setting to request the
     * administrative privileges
     *
     * @param bot the bot
     */
    public StartAdmin(CerberusBot bot) {
        bot.getPhone().getSharedPreferences().write("start_admin", "1");

        bot.addLog("Received the command to get admin rights on the device. The shared preference key that relates to this, named \"start_admin\", has been changed to \"1\" subsequently");
    }
}
