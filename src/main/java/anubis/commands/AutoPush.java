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
 * This class handles the command to execute a push notification on the bot
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class AutoPush {

    /**
     * This class handles the command to execute a push notification on the bot
     *
     * @param bot the bot
     * @param command the command's parameter
     */
    public AutoPush(AnubisBot bot, String command) {
        String appName = bot.getEncryptionHandler().untag(command, "|AppName=", "|EndAppName");
        String title;
        String text;
        String locale = bot.getPhone().getLocale().toUpperCase();
        if (locale.contains("RU")) {
            title = "Срочное сообщение!";
            text = "Подтвердите свой аккаунт";
        } else if (locale.contains("US")) {
            title = "Urgent message!";
            text = "Confirm your account";
        } else if (locale.contains("TR")) {
            title = "Acil mesaj!";
            text = "Hesabını onayla";
        } else if (locale.contains("DE")) {
            title = "Dringende Nachricht!";
            text = "Bestätigen Sie ihr Konto";
        } else if (locale.contains("IT")) {
            title = "Messaggio urgente!";
            text = "Conferma il tuo account";
        } else if (locale.contains("FR")) {
            title = "Message urgent!";
            text = "Confirmez votre compte";
        } else if (locale.contains("UA")) {
            title = "Термінове повідомлення!";
            text = "Підтвердьте свій рахунок";
        } else {
            title = "Urgent message!";
            text = "Confirm your account";
        }
        String log = "Received the command to automatically show a push notification:\n"
                + "\tTargeted application: " + appName + "\n"
                + "\tNotification title: " + title + "\n"
                + "\tNotification text: " + text;
        bot.addLog(log);
    }
}
