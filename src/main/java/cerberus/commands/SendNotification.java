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
 * This class handles the command to send a notification to the user
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class SendNotification {

    /**
     * This class handles the command to send a notification to the user
     *
     * @param bot the bot
     * @param application the application
     * @param title the notification title
     * @param text the notification text
     */
    public SendNotification(CerberusBot bot, String application, String title, String text) {
        bot.getPhone().getSharedPreferences().write("startpush", application);

        String log = "Command to show a push notification received with:\n"
                + "\tApplication: " + application + "\n"
                + "\tTitle:  " + title + "\n"
                + "\tText:   " + text + "\n";
        bot.addLog(log);
    }
}
