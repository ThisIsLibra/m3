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
 * This class handles the command to start the Authenticator2 application
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class StartAuthenticator2 {

    /**
     * This class handles the command to start the Authenticator2 application
     *
     * @param bot the bot
     */
    public StartAuthenticator2(CerberusBot bot) {
        String app = "com.google.android.apps.authenticator2";
        String log = "Received the command to start the Authenticator2 application: \"" + app + "\"";
        bot.addLog(log);
    }
}
