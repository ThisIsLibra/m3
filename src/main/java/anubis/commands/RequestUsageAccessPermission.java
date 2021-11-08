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
 * Handles the command to request the usage access permission
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class RequestUsageAccessPermission {

    /**
     * Handles the command to request the usage access permission
     *
     * @param bot the bot
     */
    public RequestUsageAccessPermission(AnubisBot bot) {
        String title = "";
        String message = "";
        String buttonText = "";
        String appName = "[bot app name]";
        String locale = bot.getPhone().getLocale();
        if (locale.contains("RU")) {
            title = "Получить разрешение";
            message = "Система не корректно работает, вам необходимо включить доступ к статистике '" + appName + "'";
            buttonText = "Включить сейчас";
        } else if (locale.contains("US")) {
            title = "Get permission";
            message = "The system does not work correctly, you need to enable access to the statistics '" + appName + "'";
            buttonText = "Enable now";
        } else if (locale.contains("TR")) {
            title = "İzin almak";
            message = "Sistem düzgün çalışmıyor, istatistiklere erişim etkinleştirmeniz gerekir '" + appName + "'";
            buttonText = "Şimdi etkinleştir";
        } else if (locale.contains("DE")) {
            title = "Get permission";
            message = "Das system funktioniert nicht richtig, Sie benötigen, um Zugang zu den Statistiken '" + appName + "'";
            buttonText = "Aktivieren Sie jetzt";
        } else if (locale.contains("IT")) {
            title = "Ottenere il permesso";
            message = "Il sistema non funziona correttamente, è necessario abilitare l'accesso alle statistiche'" + appName + "'";
            buttonText = "Attiva ora";
        } else if (locale.contains("FR")) {
            title = "Obtenir la permission";
            message = "Le système ne fonctionne pas correctement, vous devez activer l'accès aux statistiques'" + appName + "'";
            buttonText = "Activer maintenant";
        } else if (locale.contains("UA")) {
            title = "Отримати дозвіл";
            message = "Система не працює коректно, вам необхідно включити доступ до статистики'" + appName + "'";
            buttonText = "Включити зараз";
        } else {
            title = "Get permission";
            message = "The system does not work correctly, you need to enable access to the statistics '" + appName + "'";
            buttonText = "Enable now";
        }

        String log = "RReceived command to request usage access permission with the following details:\n"
                + "\tTitle: " + title + "\n"
                + "\tMessage: " + message + "\n"
                + "\tButton text: " + buttonText;
        bot.addLog(log);

        if (bot.getPhone().getPermissions().contains(Permissions.PACKAGE_USAGE_STATS)) {
            log = "The bot already had the usage access permission enabled";
        } else {
            log = "The bot was granted the package usage permission";
            bot.getPhone().getPermissions().add(Permissions.PACKAGE_USAGE_STATS);
        }
        bot.addLog(log);
    }
}
