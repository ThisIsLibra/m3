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
 * This class handles the command that specifies if the injections should be
 * updated
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class UpdateInjectAndListApps {

    /**
     * This class handles the command that specifies if the injections should be
     * updated
     *
     * @param bot the bot
     */
    public UpdateInjectAndListApps(CerberusBot bot) {
        bot.getPhone().getSharedPreferences().write("checkupdateInjection", "1");

        bot.addLog("Received the command to update the injections and targeted applications list. The shared preference key named \"checkupdateInjection\" has been set to \"1\", which will initiate the download sequence shortly");
    }
}
