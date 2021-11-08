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
 * Handles the command to request the GPS permission
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class RequestGpsAccessPermission {

    /**
     * Handles the command to request the GPS permission
     *
     * @param bot the bot
     */
    public RequestGpsAccessPermission(AnubisBot bot) {
        String title = "";
        String message = "";
        String buttonText = "";
        String locale = bot.getPhone().getLocale();
        if (locale.contains("RU")) {
            title = "Геолокация";
            message = "Для корректной работы системы, нужно получить координаты, вам необходимо включить геолокацию";
            buttonText = "Включить сейчас";
        } else if (locale.contains("US")) {
            title = "Geolocation";
            message = "For correct operation of the system, you need to get the coordinates, you need to enable geolocation";
            buttonText = "Enable now";
        } else if (locale.contains("TR")) {
            title = "Coğrafi konum";
            message = "Sistemin doğru çalışması için, koordinatları almak gerekir, coğrafi konum etkinleştirmeniz gerekir";
            buttonText = "Şimdi etkinleştir";
        } else if (locale.contains("DE")) {
            title = "Geolokalisierung";
            message = "Für den korrekten Betrieb des Systems müssen Sie die Koordinaten erhalten, müssen Sie die Geolokalisierung aktivieren";
            buttonText = "Aktivieren Sie jetzt";
        } else if (locale.contains("IT")) {
            title = "Geolocalizzazione";
            message = "Per il corretto funzionamento del sistema, è necessario ottenere le coordinate, è necessario abilitare la geolocalizzazione";
            buttonText = "Attiva ora";
        } else if (locale.contains("FR")) {
            title = "Géolocalisation";
            message = "Pour un fonctionnement correct du système, vous devez obtenir les coordonnées, vous devez activer la géolocalisation";
            buttonText = "Activer maintenant";
        } else if (locale.contains("UA")) {
            title = "Геолокація";
            message = "Для коректної роботи системи вам потрібно, щоб отримати координати, потрібно включити геолокацію";
            buttonText = "Включити зараз";
        } else {
            title = "Geolocation";
            message = "For correct operation of the system, you need to get the coordinates, you need to enable geolocation";
            buttonText = "Enable now";
        }

        String log = "Received command to request GPS permission with the following details:\n"
                + "\tTitle: " + title + "\n"
                + "\tMessage: " + message + "\n"
                + "\tButton text: " + buttonText;
        bot.addLog(log);

        if (bot.getPhone().getPermissions().contains(Permissions.ACCESS_FINE_LOCATION)) {
            log = "The bot already had the GPS permission enabled";
        } else {
            log = "The bot was granted the GPS permission";
            bot.getPhone().getPermissions().add(Permissions.ACCESS_FINE_LOCATION);
        }
        bot.addLog(log);
    }
}
