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
package cerberus.bot;

import bot.Bot;
import bot.IBot;
import bot.IConfig;
import cerberus.commands.*;
import device.Phone;
import device.enums.Permissions;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

/**
 * This class contains all logic to emulate the Cerberus family. All required
 * variables are present within the CerberusConfig class (which is located
 * within this package).<br>
 *<br>
 * This class implements the IBot interface, and extends the abstract Bot class.
 * This abstract class contains all the logic that is required to interact with
 * the framework, barring the poll, register, and save functionality. These
 * three functions needs to be implemented per bot, as these contain the bot's
 * logic.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class CerberusBot extends Bot implements IBot {

    /**
     * A 17 character long ID which is based on the following scheme:
     * [a-z0-9]{17}
     */
    private String id;

    /**
     * Gets 17 character long ID which is based on the following scheme:
     * [a-z0-9]{17}
     *
     * @return the bot's ID
     */
    public String getId() {
        return id;
    }

    /**
     * The RC-4 key to encrypt and decrypt data
     */
    private String rc4Key;

    /**
     * The encryption helper class
     */
    private CerberusEncryptionHandler encryptionHandler;

    /**
     * Gets the encryption helper class for the Cerberus implementation
     *
     * @return the encryption helper
     */
    public CerberusEncryptionHandler getEncryptionHandler() {
        return encryptionHandler;
    }

    /**
     * Creates a Cerberus bot for the emulator. It requires all variables in
     * order to properly interact with the bot's C2
     *
     * @param nextPollMoment the next moment in time when the polling should
     * take place
     * @param botName the internal name of the bot
     * @param botFamily the bot's family, as defined in the bot.Families class
     * @param tag the bot's tag
     * @param localFileSystem the full path to the location that is used as the
     * bot's local file system
     * @param phone the phone object for this bot
     * @param server the C2 server for this bot
     * @param oldServers a list of old C2 servers that this bot has used
     * @param registered true if this bot has registered itself at the C2, false
     * if not
     * @param defaultSmsManager true if the bot is the default SMS manager on
     * the phone
     * @param interval the interval in seconds after which the C2 should be
     * contacted again
     * @param active true if the bot is active, false if the bot has been
     * decommissioned
     * @param proxyAddress the address of the proxy server (provide null if no
     * proxy should be used)
     * @param proxyPort the port of the proxy server (provide null if no proxy
     * should be used)
     * @param rc4Key the RC-4 key that is used to encrypt and decrypt data for
     * this sample
     * @param id the 17 character long ID, based upon the following scheme:
     * [a-z0-9]{17}
     */
    public CerberusBot(LocalDateTime nextPollMoment, String botName, String botFamily, String tag, String localFileSystem, Phone phone, String server, List<String> oldServers, boolean registered, boolean defaultSmsManager, int interval, boolean active, String proxyAddress, Integer proxyPort, String rc4Key, String id) {
        super(nextPollMoment, botName, botFamily, tag, localFileSystem, phone, server, oldServers, registered, defaultSmsManager, interval, active, proxyAddress, proxyPort);
        this.id = id;
        this.rc4Key = rc4Key;
        this.encryptionHandler = new CerberusEncryptionHandler(rc4Key);
    }

    /**
     * Gets the C2 URL to connect with
     *
     * @return the C2 URl
     */
    public String getUrl() {
        return getServer() + "/gate.php";
    }

    /**
     * This function registers the bot to the C2. If the registration is
     * successful, the bot's <em>registered</em> boolean is set to true.
     */
    @Override
    public void register() {
        try {
            addLog("Starting the registration of \"" + getBotName() + "\" (" + getBotFamily() + ") as \"" + id + "\" at \"" + getServer() + "\"");
            getPhone().getSharedPreferences().setPreferences(getDefaultSharedPreferences());
            addLog("Set the phone's shared preferences to the bot's default settings");

            String model = getPhone().getModel();
            if (model.isEmpty() == false) {
                //Capitalise the first character
                char first = model.charAt(0);
                if (Character.isUpperCase(first) == false) {
                    model = Character.toUpperCase(first) + model.substring(1);
                }
            }

            JSONObject jsonRegistrationBot = new JSONObject();
            jsonRegistrationBot.put("id", id);
            jsonRegistrationBot.put("android", getPhone().getVersion());
            jsonRegistrationBot.put("tag", getTag());
            jsonRegistrationBot.put("country", getPhone().getLocale());
            jsonRegistrationBot.put("operator", getPhone().getNetworkOperatorName());
            jsonRegistrationBot.put("model", model);

            String result = sendData("action=registration&data=", jsonRegistrationBot);

            if (result.equals("ok")) {
                addLog("Registered \"" + getBotName() + "\" (" + getBotFamily() + ") as " + id + " at \"" + getServer() + "\"");
                setRegistration(true);
                getPhone().getSharedPreferences().write("checkupdateInjection", "1");
                setInterval(600);
            } else {
                addLog("Registration of \"" + getBotName() + "\" (" + getBotFamily() + ") failed with \"" + result + "\" as a response");
            }
        } catch (Exception ex) {
            addLog("Registration of \"" + getBotName() + "\" (" + getBotFamily() + ") failed with the following error:\n" + ex.getMessage());
        }
    }

    /**
     * This function creates the default shared preferences instance on which
     * the bot is based
     *
     * @return the default shared preferences
     */
    private Map<String, String> getDefaultSharedPreferences() {
        Map<String, String> preferences = new HashMap<>();
        preferences.put("idbot", id);

        preferences.put("initialization", "good");
        preferences.put("urlAdminPanel", getServer());
        preferences.put("starterService", "");
        preferences.put("statusInstall", "");
        preferences.put("kill", "");
        preferences.put("step", "0");
        preferences.put("timestop", "0");
        preferences.put("activityAccessibilityVisible", "");
        preferences.put("arrayInjection", "");
        preferences.put("checkupdateInjection", "");
        preferences.put("whileStartUpdateInection", "");
        preferences.put("actionSettingInection", "");
        preferences.put("listSaveLogsInjection", "");
        preferences.put("LogSMS", "");
        preferences.put("hiddenSMS", "");
        preferences.put("idSettings", "");
        preferences.put("statAdmin", "0");
        preferences.put("statProtect", "0");
        preferences.put("statCards", "0");
        preferences.put("statBanks", "0");
        preferences.put("statMails", "0");
        preferences.put("activeDevice", "0");
        preferences.put("timeWorking", "0");
        preferences.put("statDownloadModule", "0");
        preferences.put("lockDevice", "0");
        preferences.put("offSound", "0");
        preferences.put("keylogger", "");
        preferences.put("activeInjection", "0");
        preferences.put("timeInject", "-1");
        preferences.put("timeCC", "-1");
        preferences.put("timeMails", "-1");
        preferences.put("timeProtect", "-1");
        preferences.put("dataKeylogger", "");
        preferences.put("autoClick", "");
        preferences.put("statAccessibilty", "0");
        preferences.put("key", rc4Key);
        preferences.put("checkProtect", "2");
        preferences.put("goOffProtect", "");
        //preferences.put("packageName", "");
        //preferences.put("packageNameActivityInject", actViewInjection.class.getCanonicalName());
        preferences.put("logsContacts", "");
        preferences.put("logsSavedSMS", "");
        preferences.put("logsApplications", "");
        preferences.put("killApplication", "");
        preferences.put("urls", "");
        preferences.put("getPermissionsToSMS", "");
        preferences.put("startInstalledTeamViewer", "");
        preferences.put("schetBootReceiver", "0");
        preferences.put("schetAdmin", "0");
        preferences.put("day1PermissionSMS", "1");
        preferences.put("startpush", "");
        preferences.put("start_admin", "");
        preferences.put("inj_start", "0");
        preferences.put("old_start_inj", "0");
        preferences.put("app_inject", "");
        preferences.put("nameInject", "");
        //preferences.put("getIdentifier", "" + R.mipmap.ic_launcher);
        // preferences.put("sms_sdk_Q", "" + activity_change_sms_manager_sdk_Q.class.getName()); //uses full class path

//        if (android.os.Build.VERSION.SDK_INT >= 19) {
//            preferences.put("packageNameDefultSmsMenager", Telephony.Sms.getDefaultSmsPackage(context).toString());
//        } else {
//            preferences.put("packageNameDefultSmsMenager", "");
//        }
        preferences.put("packageNameDefultSmsMenager", "");

        return preferences;
    }

    /**
     * A wrapper function around the connector and encryption handler to more
     * easily send data to the C2, and to avoid duplicate code
     *
     * @param parameter the parameter that specifies the action
     * @param jsonObject the JSON object that contains the data that needs to
     * accompany the given parameter
     * @return the decrypted response from the C2
     * @throws Exception if the HTTP request fails
     */
    public String sendData(String parameter, JSONObject jsonObject) throws Exception {
        parameter += encryptionHandler.encrypt(jsonObject.toString());
        String response = getConnector().post(getUrl(), parameter);
        return encryptionHandler.decrypt(response);
    }

    /**
     * This method is used to poll the C2 at the previously specified interval.
     * As such, incoming commands will be handled by this function, which can
     * use and call different classes and functions, depending on the bot's
     * implementation.
     */
    @Override
    public void poll() {
        try {
            addLog("Polling \"" + getBotName() + "\" (" + getBotFamily() + ") as \"" + id + "\" at \"" + getServer() + "\"");

            String deviceAdmin = "0";
            if (getPhone().isDeviceAdmin()) {
                deviceAdmin = "1";
            }

            String screenState = "0";
            if (getPhone().isLocked() == false) {
                screenState = "1";
            }

            String isDefaultSmsManager = "0";
            if (isDefaultSmsManager()) {
                isDefaultSmsManager = "1";
            }

            String isAccessibilityServiceEnabled = "0";
            if (getPhone().getPermissions().contains(Permissions.BIND_ACCESSIBILITY_SERVICE)) {
                isAccessibilityServiceEnabled = "1";
            }

            JSONObject json = new JSONObject();
            json.put("id", id);
            json.put("idSettings", getPhone().getSharedPreferences().read("idSettings"));
            json.put("number", getPhone().getNumber());
            json.put("statAdmin", deviceAdmin);
            json.put("statProtect", getPhone().getSharedPreferences().read("checkProtect"));
            //json.put("statProtect", "0");
            json.put("statScreen", screenState);
            json.put("statAccessibilty", isAccessibilityServiceEnabled);
            json.put("statSMS", isDefaultSmsManager);
            json.put("statCards", getPhone().getSharedPreferences().read("statCards"));
            json.put("statBanks", getPhone().getSharedPreferences().read("statBanks"));
            json.put("statMails", getPhone().getSharedPreferences().read("statMails"));
            json.put("activeDevice", getPhone().getSharedPreferences().read("step"));
            json.put("timeWorking", getPhone().getSharedPreferences().read("timeWorking"));

            //gets the "needmoreresources"
            if (getPhone().getSharedPreferences().read("statDownloadModule").equals("0")) {
                youNeedMoreResources();
                //{"this":"~settings~","saveID":"lkwBd6Qxarssi8T","arrayUrl":"http://example.com","timeInject":"15","timeProtect":"300","timeCC":"600","timeMail":"45"}
            }

            json.put("statDownloadModule", getPhone().getSharedPreferences().read("statDownloadModule"));
            json.put("batteryLevel", "" + getPhone().getBatteryPercentage());
            json.put("locale", getPhone().getLocale());

            String response = sendData("action=botcheck&data=", json);

            addLog("The polling at \"" + getServer() + "\" for \"" + id + "\" received \"" + response + "\"");

            if (response.contains("||youNeedMoreResources||")) {
                youNeedMoreResources();
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("params", "updateSettingsAndCommands");
                jsonObject.put("response", response);
                handleResponse(jsonObject);

                jsonObject = new JSONObject();
                jsonObject.put("params", "serviceWorkingWhile");
                jsonObject.put("tick", "" + getPhone().getSharedPreferences().read("timeWorking"));
                jsonObject.put("idbot", id);
                jsonObject.put("accessibility", isAccessibilityServiceEnabled);
                handleResponse(jsonObject);
            }

            Random random = new Random();

            getPhone().setLocked(random.nextBoolean());
            getPhone().setBatteryPercentage(random.nextInt(100) + 3);
        } catch (Exception ex) {
            addLog("Polling of \"" + getBotName() + "\" (" + getBotFamily() + ") failed with the following error:\n" + ex.getMessage());
        }
    }

    /**
     * A function to handle the response from the C2
     *
     * @param jsonObject the C2's resposne
     * @throws Exception if any HTTP request or I/O call fails
     */
    private void handleResponse(JSONObject jsonObject) throws Exception {
        switch (jsonObject.getString("params")) {
            case "updateSettingsAndCommands": //Commands, Global Settings and Bots Settings
                String response = jsonObject.getString("response");
                if ((!response.equals("||no||")) && ((response.contains("~settings~")) || (response.contains("~mySettings~")) || (response.contains("~commands~") || (response.contains("no_command"))))) {
                    new UpdateSettingsAndCommands(this, new JSONObject(response));
                }
                break;

            case "startViewInject":
                String componentPackage = jsonObject.getString("packageProject");
                String componentView = jsonObject.getString("packageView");
                String injectPackage = jsonObject.getString("packageAppStart");
                String injectName = jsonObject.getString("nameInject");
                new StartViewInject(this, componentPackage, componentView, injectPackage, injectName);
                break;

            case "serviceWorkingWhile":
                String idBot = jsonObject.getString("idbot");
                int tick = jsonObject.optInt("tick", 0);
                String accessibility = jsonObject.getString("accessibility");
                //serviceWorkingWhile(jsonObject);
                new ServiceWorkingWhile(this, idBot, tick, accessibility);
                break;
        }
    }

    /**
     * A function to handle the youneedMoreResources response, meaning that the
     * bot's module needs to be downloaded
     *
     * @throws Exception if any HTTP request or I/O call fails
     */
    private void youNeedMoreResources() throws Exception {
        addLog("More resources are required for the bot to run, as the module has not yet been downloaded");

        getPhone().getSharedPreferences().write("statDownloadModule", "1");
        addLog("Changed the shared preference key named \"statDownloadModule\" from \"0\" into \"1\"");

        JSONObject jsonDownloadModule = new JSONObject();
        jsonDownloadModule.put("idbot", id);

        String base64 = sendData("action=getModule&data=", jsonDownloadModule);
        byte[] dex = new Base64().decode(base64);

        String moduleFileName = "module.dex";

        int existingSize = getLocalFileSystemManager().readByteArrayFromPath(moduleFileName).length;

        LocalDateTime instance = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("__yyyy-MM-dd_HH-mm-ss");
        String appendix = formatter.format(instance);

        //Checking the latest version
        if (existingSize == 0 && dex.length == 0) {
            addLog("The module is not present on the disk, as its size is 0 bytes, but it is not available on the server either");
        } else if (existingSize != dex.length) {
            //Saving a dated copy, to have a history of all versions
            getLocalFileSystemManager().writeFile(dex, moduleFileName + appendix);

            //Save a copy with the default name, which is always the latest version
            getLocalFileSystemManager().writeFile(dex, moduleFileName);
            addLog("Downloaded the module (with a size of " + dex.length + " bytes)");
        } else {
            addLog("A module with the exact same size (" + existingSize + " bytes) has already been downloaded before, as it exists on the disk as \"" + moduleFileName + "\"");
        }
    }

    /**
     * This function convers the bot into an IConfig object, which is then saved
     * into the local file system's folder as <em>config.json</em>.
     */
    @Override
    public void save() {
        IConfig config = new CerberusConfig(getNextPollMoment(), getBotName(), getBotFamily(), getTag(), getLocalFileSystem(), getPhone().getImei(), getPhone().getNumber(), getPhone().getNetworkOperatorName(), getPhone().getLocale(), getPhone().getVersion(), getPhone().getModel(), getPhone().getProduct(), getPhone().getSmsManager(), getPhone().getContacts(), getPhone().getSharedPreferences(), getPhone().getPermissions(), getPhone().getInstalledApplications(), getServer(), getOldServers(), isRegistered(), getPhone().getUserAgent(), getInterval(), getPhone().isRooted(), isDefaultSmsManager(), getPhone().isDeviceAdmin(), getPhone().isLocked(), getPhone().getBatteryPercentage(), isActive(), getProxyAddress(), getProxyPort(), rc4Key, id);
        saveConfig(config);
    }

}
