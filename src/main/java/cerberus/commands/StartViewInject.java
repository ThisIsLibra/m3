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
 * This class handles the incoming command to start the injection with a
 * specific view
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class StartViewInject {

    /**
     * This class handles the incoming command to start the injection with a
     * specific view
     *
     * @param bot the bot
     * @param componentPackage the component's package
     * @param componentView the component's view
     * @param injectPackage the injection's package
     * @param injectName the injection's name
     */
    public StartViewInject(CerberusBot bot, String componentPackage, String componentView, String injectPackage, String injectName) {
        String log = "Received the command to start the injection with a specific view:\n"
                + "\tComponent package name: " + componentPackage
                + "\tComponent view: " + componentView
                + "\tInjection package: " + injectPackage
                + "\tInjection name: " + injectName;
        bot.addLog(log);
    }
}
