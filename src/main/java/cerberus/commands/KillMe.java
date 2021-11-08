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
 * This class handles the removal of the bot
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class KillMe {

    /**
     * This class handles the removal of the bot
     *
     * @param bot the bot
     */
    public KillMe(CerberusBot bot) {
        bot.addLog("The command to deactivate the bot has been received");

        bot.getPhone().getSharedPreferences().write("autoClick", "1");
        bot.getPhone().getSharedPreferences().write("killApplication", "self");

        bot.setActive(false);
    }
}
