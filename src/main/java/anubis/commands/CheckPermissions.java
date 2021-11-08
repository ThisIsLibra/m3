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
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the check permission command
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class CheckPermissions {

    /**
     * Checks the permissions of the bot, and reports back to the C2 with the
     * permission status of the few listed permissions within this function
     *
     * @param bot the bot
     * @throws Exception if the HTTP request fails
     */
    public CheckPermissions(AnubisBot bot) throws Exception {
        //Permissions to check for, per the bot's source
        List<String> localPermissions = new ArrayList<>();
        localPermissions.add(Permissions.SEND_SMS);
        localPermissions.add(Permissions.WRITE_EXTERNAL_STORAGE);
        localPermissions.add(Permissions.READ_CONTACTS);
        localPermissions.add(Permissions.ACCESS_FINE_LOCATION);
        localPermissions.add(Permissions.CALL_PHONE);
        localPermissions.add(Permissions.RECORD_AUDIO);

        String permissionOutput = "All permissions:" + "\n";

        for (String localPermission : localPermissions) {
            if (bot.getPhone().getPermissions().contains(localPermission)) {
                permissionOutput = permissionOutput + localPermission + ": OFF" + "\n";
            } else {
                permissionOutput = permissionOutput + localPermission + ": ON" + "\n";
            }
        }

        String url = bot.getServer() + "/" + bot.getServerFolder() + "/a6.php";
        String parameter = "p=" + bot.getEncryptionHandler().encrypt(bot.getPhone().getImei() + "|" + permissionOutput + "|");

        bot.getConnector().post(url, parameter);
    }
}
