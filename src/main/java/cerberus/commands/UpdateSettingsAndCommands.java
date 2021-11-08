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
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

/**
 * This class updates the settings and handles any command from the C2. The
 * logic to handle a command is specified per command in the classes in
 * cerberus.commands.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class UpdateSettingsAndCommands {

    /**
     *  * This class updates the settings and handles any command from the C2.
     * The logic to handle a command is specified per command in the classes in
     * cerberus.commands.
     *
     * @param bot the bot
     * @param jsonObject the JSON input from the C2
     * @throws Exception if a HTTP request fails
     */
    public UpdateSettingsAndCommands(CerberusBot bot, JSONObject jsonObject) throws Exception {
        if (jsonObject.getString("this").equals("no_command") && (bot.getPhone().getSharedPreferences().read("checkupdateInjection").equals("1"))) { //downloading injections
            bot.addLog("Received no command in the polling, but the bot is set to update the injections");

            //Gets all applications
            String allApplications = "";
            for (String installedApplication : bot.getPhone().getInstalledApplications()) {
                allApplications = allApplications + installedApplication + ":";
            }

            //Deletes last character
            if (allApplications.length() > 0 && allApplications.charAt(allApplications.length() - 1) == 'x') {
                allApplications = allApplications.substring(0, allApplications.length() - 1);
            }

            JSONObject jsonCheckInj = new JSONObject();
            jsonCheckInj.put("id", bot.getId());
            jsonCheckInj.put("apps", allApplications);

            String response = bot.sendData("action=injcheck&data=", jsonCheckInj);
            bot.addLog("The response from the server when downloading the injections is:\n\n" + response);

            if (!response.equals("||no||") && (!response.isEmpty())) {
                String[] arrayInjection = response.split(":");
                for (int i = 0; i < arrayInjection.length; i++) {
                    if (!arrayInjection[i].isEmpty()) {
                        bot.getPhone().getSharedPreferences().write(arrayInjection[i], "");//html fake app
                        bot.getPhone().getSharedPreferences().write("icon_" + arrayInjection[i], "");//icon

                        bot.addLog("Added \"" + arrayInjection[i] + "\" to the shared preferences");
                    }
                }
                bot.getPhone().getSharedPreferences().write("arrayInjection", response);
                bot.getPhone().getSharedPreferences().write("checkupdateInjection", "");
                bot.getPhone().getSharedPreferences().write("whileStartUpdateInection", "1");
            }
        } else if (jsonObject.getString("this").equals("~settings~")) {
            String temp = "";

            bot.getPhone().getSharedPreferences().write("idSettings", jsonObject.getString("saveID"));

            if (jsonObject.getString("arrayUrl").length() > 7) {
                bot.getPhone().getSharedPreferences().write("urls", bot.getPhone().getSharedPreferences().read("urlAdminPanel") + "," + jsonObject.getString("arrayUrl"));
                temp += "\tKey: \"urls\" was appended with \"" + jsonObject.getString("arrayUrl") + "\"\n";
            }
            if (bot.getPhone().getSharedPreferences().read("timeInject").equals("-1")) {
                bot.getPhone().getSharedPreferences().write("timeInject", jsonObject.getString("timeInject"));
                temp += "\tKey: \"timeInject\" was changed to \"" + jsonObject.getString("timeInject") + "\"\n";
            }
            if (bot.getPhone().getSharedPreferences().read("timeCC").equals("-1")) {
                bot.getPhone().getSharedPreferences().write("timeCC", jsonObject.getString("timeCC"));
                temp += "\tKey: \"timeCC\" was changed to \"" + jsonObject.getString("timeCC") + "\"\n";
            }
            if (bot.getPhone().getSharedPreferences().read("timeMails").equals("-1")) {
                bot.getPhone().getSharedPreferences().write("timeMails", jsonObject.getString("timeMail"));
                temp += "\tKey: \"timeMails\" was changed to \"" + jsonObject.getString("timeMail") + "\"\n";
            }
            if (bot.getPhone().getSharedPreferences().read("timeProtect").equals("-1")) {
                bot.getPhone().getSharedPreferences().write("timeProtect", jsonObject.getString("timeProtect"));
                temp += "\tKey: \"timeProtect\" was changed to \"" + jsonObject.getString("timeProtect") + "\"\n";
            }

            if (temp.isEmpty() == false) {
                String settingsUpdate = "Updated one or more shared preference values, as can be seen below:\n" + temp;
                //Removes the last newly from the items, thus avoiding an empty line in the log file
                settingsUpdate = settingsUpdate.substring(0, settingsUpdate.length() - 1);
                bot.addLog(settingsUpdate);
            }

        } else if (jsonObject.getString("this").equals("~mySettings~")) {
            bot.getPhone().getSharedPreferences().write("hiddenSMS", jsonObject.getString("hideSMS")); // +
            bot.getPhone().getSharedPreferences().write("lockDevice", jsonObject.getString("lockDevice"));// +
            bot.getPhone().getSharedPreferences().write("offSound", jsonObject.getString("offSound"));// +
            bot.getPhone().getSharedPreferences().write("keylogger", jsonObject.getString("keylogger"));// +
            bot.getPhone().getSharedPreferences().write("actionSettingInection", jsonObject.getString("activeInjection"));// +

            String mySettingsUpdate = "Updated several shared preference values, as can be seen below:\n"
                    + "\tKey: \"hiddenSMS\" with value \"" + jsonObject.getString("hideSMS") + "\"\n"
                    + "\tKey: \"lockDevice\" with value \"" + jsonObject.getString("lockDevice") + "\"\n"
                    + "\tKey: \"offSound\" with value \"" + jsonObject.getString("offSound") + "\"\n"
                    + "\tKey: \"keylogger\" with value \"" + jsonObject.getString("keylogger") + "\"\n"
                    + "\tKey: \"actionSettingInection\" with value \"" + jsonObject.getString("activeInjection") + "\"";
            bot.addLog(mySettingsUpdate);
        } else if (jsonObject.getString("this").equals("~commands~")) {
            byte[] byteData = new Base64().decode(jsonObject.getString("data"));
            JSONObject jsonCommand = new JSONObject(new String(byteData, "UTF-8"));
            switch (jsonCommand.getString("name")) {
                case "sendSms":
                    new SendSms(bot, jsonCommand.getString("number"), jsonCommand.getString("text"));
                    break;
                case "startUssd":
                    new StartUssd(bot, jsonCommand.getString("ussd"));
                    break;
                case "forwardCall":
                    new ForwardCall(bot, jsonCommand.getString("number"));
                    break;
                case "push":
                    //SettingsWrite(mContext, "startpush", app);
                    new SendNotification(bot, jsonCommand.getString("app"), jsonCommand.getString("title"), jsonCommand.getString("text"));
                    break;
                case "getContacts":
                    new GetContacts(bot);
                case "getInstallApps":
                    new GetInstalledApps(bot);
                    break;
                case "getSMS":
                    new GetSms(bot);
                    break;
                case "startInject":
                    new StartInject(bot, jsonCommand.getString("app"));
                    break;
                case "openUrl":
                    new OpenUrl(bot, jsonCommand.getString("url"));
                    break;
                case "startAuthenticator2":
                    new StartAuthenticator2(bot);
                    break;
                case "SendSMSALL":
                    new SendSmsAll(bot, jsonCommand.getString("text"));
                    break;
                case "startApp":
                    new StartApplication(bot, jsonCommand.getString("app"));
                    break;
                case "deleteApplication":
                    new DeleteApplication(bot, jsonCommand.getString("app"));
                    break;
                case "startAdmin":
                    new StartAdmin(bot);
                    break;
                case "killMe":
                    new KillMe(bot);
                    break;
                case "updateInjectAndListApps":
                    new UpdateInjectAndListApps(bot);
                    break;
                case "updateModule":
                    new UpdateModule(bot);
                    break;
            }
        }
    }
}
