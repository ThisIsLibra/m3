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
package anubis.bot;

import anubis.commands.*;
import bot.Bot;
import bot.IBot;
import bot.IConfig;
import device.Phone;
import device.enums.Permissions;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains all logic to emulate the Anubis family. All required
 * variables are present within the AnubisConfig class (which is located within
 * this package).<br>
 *<br>
 * This class implements the IBot interface, and extends the abstract Bot class.
 * This abstract class contains all the logic that is required to interact with
 * the framework, barring the poll, register, and save functionality. These
 * three functions needs to be implemented per bot, as these contain the bot's
 * logic.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class AnubisBot extends Bot implements IBot {

    /**
     * The RC-4 key to encrypt and decrypt data
     */
    private String rc4Key;

    /**
     * The folder where the PHP files reside on the Anubis C2 folder, excluding
     * the domain, and without any leading or trailing slashes. In this example
     * URL: "127.0.0.1/abc/a11.php", the folder name is "abc"
     */
    private String serverFolder;

    /**
     * The encryption handler that contains helpful code to avoid clutter in
     * this class
     */
    private AnubisEncryptionHandler encryptionHandler;

    /**
     * Gets the RC-4 key for this bot
     *
     * @return the RC-4 key
     */
    public String getRc4Key() {
        return rc4Key;
    }

    /**
     * The folder where the PHP files reside on the Anubis C2 folder, excluding
     * the domain, and without any leading or trailing slashes. In this example
     * URL: "127.0.0.1/abc/a11.php", the folder name is "abc"
     *
     * @return the server folder
     */
    public String getServerFolder() {
        return serverFolder;
    }

    /**
     * Gets the encryption handler of this bot
     *
     * @return the encryption handler
     */
    public AnubisEncryptionHandler getEncryptionHandler() {
        return encryptionHandler;
    }

    /**
     * Creates an Anubis bot for the emulator. It requires all variables in
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
     * @param registered true if this bot has registered itself at the C2,
     * false if not
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
     * @param serverFolder the folder name of the server where the PHP files of
     * Anubis reside
     */
    public AnubisBot(LocalDateTime nextPollMoment, String botName, String botFamily, String tag, String localFileSystem, Phone phone, String server, List<String> oldServers, boolean registered, boolean defaultSmsManager, int interval, boolean active, String proxyAddress, Integer proxyPort, String rc4Key, String serverFolder) {
        super(nextPollMoment, botName, botFamily, tag, localFileSystem, phone, server, oldServers, registered, defaultSmsManager, interval, active, proxyAddress, proxyPort);
        this.rc4Key = rc4Key;
        encryptionHandler = new AnubisEncryptionHandler(rc4Key);
        this.serverFolder = serverFolder;
    }

    /**
     * This function converts the bot into an IConfig object, which is then saved
     * into the local file system's folder as <em>config.json</em>.
     */
    @Override
    public void save() {
        IConfig config = new AnubisConfig(getNextPollMoment(), getBotName(), getBotFamily(), getTag(), getLocalFileSystem(), getPhone().getImei(), getPhone().getNumber(), getPhone().getNetworkOperatorName(), getPhone().getLocale(), getPhone().getVersion(), getPhone().getModel(), getPhone().getProduct(), getPhone().getSmsManager(), getPhone().getContacts(), getPhone().getSharedPreferences(), getPhone().getPermissions(), getPhone().getInstalledApplications(), getServer(), getOldServers(), isRegistered(), getPhone().getUserAgent(), getInterval(), getPhone().isRooted(), isDefaultSmsManager(), getPhone().isDeviceAdmin(), getPhone().isLocked(), getPhone().getBatteryPercentage(), isActive(), getProxyAddress(), getProxyPort(), getRc4Key(), getServerFolder());
        saveConfig(config);
    }

    /**
     * This function registers the bot to the C2. If the registration is
     * successful, the bot's <em>registered</em> boolean is set to true.
     */
    @Override
    public void register() {
        try {
            addLog("Starting the registration of \"" + getBotName() + "\" (" + getBotFamily() + ") as \"" + getPhone().getImei() + "\" at \"" + getServer() + "\"");
            SecureRandom random = new SecureRandom();
            String targetedApplications = getRandomTargetedApplications(random.nextInt(2) + 1);
            getPhone().getInstalledApplications().addAll(Arrays.asList(targetedApplications.split(",")));
            getPhone().getSharedPreferences().write("emuTargetedApplications", targetedApplications);

            String url = getServer() + "/" + serverFolder + "/a3.php";
            String[] registration = new String[10];
            registration[0] = getPhone().getImei();
            registration[1] = "(" + getPhone().getNetworkOperatorName() + ")" + getPhone().getNumber();
            registration[2] = getPhone().getVersion(); //The user-visible version string.  E.g., "1.0" or "3.4b5". Build.VERSION.RELEASE
            registration[3] = getPhone().getLocale();
            registration[4] = targetedApplications;
            registration[5] = getPhone().getModel() + " (" + getPhone().getProduct() + ")"; //Build.MODEL and Build.PRODUCT
            registration[6] = getTag(); //Unique per campaign
            registration[7] = ""; //AV, empty in the bot source code
            //Last two are only set if PII is sent to the server, so these should remain zero when registering
            registration[8] = "0"; //ICON_CARD (not set in bot, 0 means false, 1 means true)
            registration[9] = "0"; //ICON_INJ (reads iconCJ from the shared preferences) (not set in bot, 0 means false, 1 means true)

            String temp = "";
            for (int i = 0; i < registration.length; i++) {
                temp = temp + registration[i] + ":";
            }

            String parameter = "p=" + encryptionHandler.encrypt(temp);

            String response = getConnector().post(url, parameter);
            String noTags = encryptionHandler.untag(response);
            String decrypted = encryptionHandler.decrypt(noTags); //|OK|
            if (decrypted.equalsIgnoreCase("|OK|")) {
                addLog("Registered \"" + getBotName() + "\" (\"" + getBotFamily() + "\") as \"" + getPhone().getImei() + "\" at \"" + getServer() + "\"");
                setRegistration(true);

                new GetRansomNote(this);
                //If playprot is enabled in the bot settings (yes by default)
                //If context mode private file dir is reachable via file object
                //If playprot module is not present on the file system
                new GetPlayProtectApk(this);

                //The settings (including the hash of the settings) need to be obtained for later requests
                response = allSettingsGo();
                String messageHash = tryGetValue(response.split("~"), 0);
                getPhone().getSharedPreferences().write("emuMessageHash", messageHash);
                addLog("Wrote \"" + messageHash + "\" to the shared preferences under the \"emuMessageHash\" key");
            } else {
                addLog("Registration of \"" + getBotName() + "\" (" + getBotFamily() + ") failed with \"" + decrypted + "\" as a response");
            }
        } catch (Exception ex) {
            addLog("Registration of \"" + getBotName() + "\" (" + getBotFamily() + ") failed with the following error:\n" + ex.getMessage());
        }
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
            String response = updateAndGetCommands();
            String log = "Polling " + getServer() + " for " + getPhone().getImei() + " and received \"" + response + "\"";
            addLog(log);
            if (response.contains("|NO|")) {
                response = registerResponseNo();
            } else if (response.contains("state1letsgotxt")) {
                response = state1letsgo();
            } else if (response.contains("ALLSETTINGSGO")) {
                response = allSettingsGo();
            } else if (response.equals("")) {
                response = responseEmpty();
            }

            for (String command : response.split("::")) {
                if (command.contains("|ussd=")) {
                    new StartUssd(this, command);
                }
                if (command.contains("|startapplication=")) {
                    new StartApplication(this, command);
                }
                if (command.contains("|replaceurl=")) {
                    new ReplaceUrl(this, command);
                }
                if (command.contains("GetSWSGO")) {
                    new GetSavedSmsMessages(this);
                }
                if (command.contains("getapps")) {
                    new GetAllInstalledApplications(this);
                }
                if (command.contains("getpermissions")) {
                    new CheckPermissions(this);
                }
                if (command.contains("getkeylogger")) {
                    new GetKeyloggerData(this);
                }
                if (command.contains("=ALERT|")) {
                    new ShowMessageBox(this, command);
                }
                if (command.contains("=PUSH|")) {
                    new SendPushNotification(this, command);
                }
                if (command.contains("startAutoPush")) {
                    new AutoPush(this, command);
                }
                if (command.contains("|startinj=")) {
                    new StartInj(this, command);
                }
                if (command.contains("nymBePsG0")) {
                    new GetPhoneBookNumbers(this);
                }
                if (command.contains("|telbookgotext=")) {
                    new SendSmsMessagesViaPhoneContacts(this, command);
                }
                if (command.contains("spam")) {
                    new SpamSms(this, command);
                }
                if (command.contains("RequestPermissionInj")) {
                    new RequestUsageAccessPermission(this);
                }
                if (command.contains("RequestPermissionGPS")) {
                    new RequestGpsAccessPermission(this);
                }
                if (command.contains("startaccessibility")) {
                    new StartAccessibilityService(this);
                }
                if (command.contains("startpermission")) {
                    new StartPermissions(this);
                }
                if (command.contains("startforward=")) {
                    new CallForwarder(this, command);
                }
                if (command.contains("stopforward")) {
                    new CallForwarder(this);
                }
                if (command.contains("|openbrowser=")) {
                    new OpenUrlInBrowser(this, command);
                }
                if (command.contains("|openactivity=")) {
                    new OpenActivity(this, command);
                }
                if (command.contains("|cryptokey=")) {
                    new Cryptolocker(this, command);
                }
                if (command.contains("|decryptokey=")) {
                    new DecryptFileSystem(this, command);
                }
                if (command.contains("|recordsound=")) {
                    new RecordSound(this, command);
                }
                if (command.contains("|sockshost=")) {
                    new Socks5(this, command);
                }
                if (command.contains("stopsocks5")) {
                    new StopSocks5(this);
                }
                if (command.contains("getIP")) {
                    new GetIp(this);
                }
                if (command.contains("killBot")) {
                    new KillBot(this);
                }
                if (command.contains("Send_GO_SMS")) {
                    new SendGoSms(this, command);
                }
                if (command.contains("|startrat=")) {
                    new StartRat(this, command);
                }
            }
        } catch (Exception ex) {
            addLog("Polling of \"" + getBotName() + "\" (" + getBotFamily() + ") failed with the following error:\n" + ex.getMessage());
        }
    }

    /**
     * Gets the update from the C2 server. The function name is based upon the
     * internal name within Anubis.
     *
     * @return the C2's update
     * @throws Exception if the HTTP connection fails
     */
    private String updateAndGetCommands() throws Exception {
        SecureRandom random = new SecureRandom();
        int steps = 0;
        if (getPhone().getSharedPreferences().read("emuSteps") != null) {
            steps = Integer.parseInt(getPhone().getSharedPreferences().read("emuSteps"));

        }
        int toAdd = random.nextInt(2000);
        steps += toAdd;
        getPhone().getSharedPreferences().write("emuSteps", "" + steps);

        String screenActive = "" + random.nextInt(2);
        String seconds = "15";
        if (getPhone().getSharedPreferences().read("time_work") != null) {
            seconds = getPhone().getSharedPreferences().read("time_work");
        }

        int rooted = 0;
        if (getPhone().isRooted()) {
            rooted = 1;
        }

        String[] array = new String[12];
        array[0] = getPhone().getImei(); //IMEI
        array[1] = "" + rooted; //ROOT (1 means device admin, 0 means not admin)
        array[2] = getPhone().getSharedPreferences().read("emuMessageHash"); //SETTINGS_ALL_HASH (get from returned settings)
        int usageStats = 0;
        if (getPhone().getPermissions().contains(Permissions.PACKAGE_USAGE_STATS)) {
            usageStats = 1;
        }
        array[3] = "" + usageStats; //CHECK_ICON_INJ (1 means usage stats enabled, 0 means not)
        int locationService = 0;
        if (getPhone().getPermissions().contains(Permissions.ACCESS_COARSE_LOCATION) || getPhone().getPermissions().contains(Permissions.ACCESS_FINE_LOCATION)) {
            locationService = 1;
        }
        array[4] = "" + locationService; //CHECK_ICON_GEO_LOCATION (1 means location service is on, 0 means not)
        int defaultSmsManager = 0;
        if (isDefaultSmsManager()) {
            defaultSmsManager = 1;
        }
        array[5] = "" + defaultSmsManager; //CHECK_ICON_SMS (is default sms manager)
        array[6] = screenActive; //SCREEN (1 if screen is unlocked, 0 if not)
        int accessibility = 0;
        if (getPhone().getPermissions().contains(Permissions.BIND_ACCESSIBILITY_SERVICE)) {
            accessibility = 1;
        }
        array[7] = "" + accessibility; //ACCESSIBILITY (1 if enabled, 0 if not)
        array[8] = seconds; //SECONDS (ERROR by default, tries to read time_work from the shared preferences, uses that value instead)
        array[9] = "1"; //PLAY_PROTECT (2 by default, if the shared preference play_protect is set to true the value is 1, if it is false it is set to 0)
        array[10] = "1"; //DOZE (always 1 when using SDK <23, only 1 when it uses ignore battery optimisations at SDK >23)
        array[11] = "" + steps;//STEP (steps taken, read from shared preference "emuSteps")

        String url = getServer() + "/" + serverFolder + "/a4.php";

        String parameter = "p=";
        String temp = "";
        for (int i = 0; i < array.length; i++) {
            temp = temp + array[i] + ":";
        }
        parameter += encryptionHandler.encrypt(temp);

        String response = getConnector().post(url, parameter);
        String noTags = encryptionHandler.untag(response);
        String decrypted = encryptionHandler.decrypt(noTags);
        return decrypted;
    }

    /**
     * This function is called if the response to registering is no
     *
     * @return the decrypted C2 response
     * @throws Exception if the HTTP connection fails
     */
    private String registerResponseNo() throws Exception {
        //ID_B + ":" + number + number_ + ":" + device + ":" + country + ":" + b_nk + ":" + model + ":" + const_.Version + ":" + AV + ":" + getIconCardInj + ":"
        String[] array = new String[9];
        array[0] = getPhone().getImei(); //IMEI
        array[1] = getPhone().getNumber(); //NUMBER
        array[2] = getPhone().getVersion(); //DEVICE = Build.VERSION.RELEASE
        array[3] = getPhone().getLocale(); //COUNTRY
        array[4] = getPhone().getSharedPreferences().read("emuTargetedApplications"); //BANK
        array[5] = getPhone().getModel(); //MODEL
        array[6] = getTag(); //BOT VERSION
        array[7] = ""; //AV
        array[8] = "0"; //ICON_CARD_INJ

        String url = getServer() + "/" + serverFolder + "/a3.php";
        String parameter = "p=";
        String temp = "";
        for (int i = 0; i < array.length; i++) {
            temp += array[i] + ":";
        }
        parameter += encryptionHandler.encrypt(temp);
        String response = getConnector().post(url, parameter);
        String noTags = encryptionHandler.untag(response);
        String decrypted = encryptionHandler.decrypt(noTags);
        return decrypted;
    }

    /**
     * A helper function to get a specific value from an array at a given index,
     * without worrying about the length of this index. If the index is not
     * present in the array, an empty string is returned instead.
     *
     * @param array the array to get the value from
     * @param index the index of the value to obtain
     * @return the value from the index, or an empty string if this fails
     */
    private String tryGetValue(String[] array, int index) {
        try {
            return array[index];
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * This function handles the <em>allSettingsGo</em> functionality of the
     * bot, which initialises all settings
     *
     * @return the decrypted C2 response
     * @throws Exception if the HTTP request fails
     */
    private String allSettingsGo() throws Exception {
        String url = getServer() + "/" + serverFolder + "/a8.php";

        String parameter = "p=" + encryptionHandler.encrypt(getPhone().getImei());

        String response = getConnector().post(url, parameter);
        String noTags = encryptionHandler.untag(response);
        String decrypted = encryptionHandler.decrypt(noTags);

        String log = "Received the following settings:\n";
        String[] output = decrypted.split("~");
        String hash = tryGetValue(output, 0);
        log += "\thash: " + hash + "\n";

        try {
            int interval = Integer.parseInt(tryGetValue(output, 1));
            log += "\tinterval: " + interval + "\n";

            setInterval(interval);
        } catch (Exception e) {
            log += "\tinterval not found, using the default value of 1400 seconds\n";
            setInterval(14000);
        }

        String checkSms = tryGetValue(output, 2);
        log += "\tcheckSms: " + checkSms + "\n";

        String checkHideSms = output[3];
        log += "\tcheckHideSms: " + checkHideSms + "\n";

        String checkGeoLocation = output[4];
        log += "\tcheckGeoLocation: " + checkGeoLocation + "\n";

        String secondInjectionApps = output[5];
        log += "\tsecondInjectionApps: " + secondInjectionApps + "\n";

        String secondRequestGeolocation = output[6];
        log += "\tsecondRequestGeolocation: " + secondRequestGeolocation + "\n";

        String grabAutoAndSecondGrabAuto = tryGetValue(output, 7);
        log += "\tgrabAutoAndSecondGrabAuto: " + grabAutoAndSecondGrabAuto + "\n";

        String injGrabAndSecondInjGrab = tryGetValue(output, 8);
        log += "\tinjGrabAndSecondInjGrab: " + injGrabAndSecondInjGrab + "\n";

        String secondPhoneContacts = tryGetValue(output, 9);
        log += "\tsecondPhoneContacts: " + secondPhoneContacts + "\n";

        String secondStartGeolocation = tryGetValue(output, 10);
        log += "\tsecondStartGeolocation: " + secondStartGeolocation + "\n";
        String urls = tryGetValue(output, 11);

        String urlInj = tryGetValue(output, 12);
        log += "\turlInj: " + urlInj + "\n";

        String findFiles = tryGetValue(output, 13);
        log += "\tfindFiles: " + findFiles + "\n";

        addLog(log);

        //$hash~$intInterval~$checksms~$checkhidesms~$checkgeolocation~$secondInjectionsApps~$secondRequestGeolocation~$Grab_auto/$secondGrab_auto~$InjGrab/$secondInjGrab~$secondPhoneContacts~$secondStartGeolocation~$urls~$urlInj~$findfiles~
        //77T5dSD6GQknN47~14000~true~false~false~-1~-1~/10~|2|8|15|16|17/6700~-1~-1~~~~
        /**
         * $hash = 77T5dSD6GQknN47
         *
         * $intInterval = 14000
         *
         * $checksms = true
         *
         * $checkhidesms = false
         *
         * $checkgeolocation = false
         *
         * $secondInjectionsApps = -1
         *
         * $secondRequestGeolocation = -1
         *
         * $Grab_auto/$secondGrab_auto = /10
         *
         * $InjGrab/$secondInjGrab = |2|8|15|16|17/6700
         *
         * $secondPhoneContacts = -1
         *
         * $secondStartGeolocation = -1
         *
         * $urls = null
         *
         * $urlInj = null
         *
         * $findfiles = null
         *
         * ~
         */
        String settingsAll = decrypted;
        getPhone().getSharedPreferences().write("SettingsAll", settingsAll);
        addLog("Wrote \"" + settingsAll + "\" to the shared preferences under the \"SettingsAll\" key");

        //Unknown
        String madeSettings = "1 2 3 4 5 6 7 8 9 10 11 12 13 ";
        String getMadeSettings = getPhone().getSharedPreferences().read("madeSettings");
        if (getMadeSettings == null) {
            getMadeSettings = "";
        }

        if (getMadeSettings.contains("5+")) {
            madeSettings = madeSettings.replace("5 ", "5+");
        }
        if (getMadeSettings.contains("6+")) {
            madeSettings = madeSettings.replace("6 ", "6+");
        }
        if (getMadeSettings.contains("7+")) {
            madeSettings = madeSettings.replace("7 ", "7+");
        }
        if (getMadeSettings.contains("8+")) {
            madeSettings = madeSettings.replace("8 ", "8+");
        }
        if (getMadeSettings.contains("9+")) {
            madeSettings = madeSettings.replace("9 ", "9+");
        }
        if (getMadeSettings.contains("10+")) {
            madeSettings = madeSettings.replace("10 ", "10+");
        }
        getPhone().getSharedPreferences().write("madeSettings", madeSettings);
        addLog("Wrote \"" + madeSettings + "\" to the shared preferences under the \"madeSettings\" key");

        return decrypted;
    }

    /**
     * This function handles the lack of a response by the C2, as it will look
     * for alternative C2s that are reachable.
     *
     * @return the C2's decrypted response
     * @throws Exception if the HTTP request fails
     */
    private String responseEmpty() throws Exception {
        String urlsRaw = "";
        String[] urls = urlsRaw.replace(" ", "").split(",");
        boolean isTwitter = false;
        String urlPostfix = "/" + serverFolder + "/a16.php";
        //note wrong key, but correct for given twitter sample
        //Connector commandHandler = new Connector("zanubis");
        String parameter = "";
        for (int i = 0; i < urls.length; i++) {
            String response = getConnector().post(urls[i] + urlPostfix, parameter);
            String noTags = encryptionHandler.untag(response);
            if (noTags.contains("**2**0**0**")) {
                getPhone().getSharedPreferences().write("url", urls[i]);
                addLog("New C2 URL stored in the shared preferences under 'url': " + urls[i]);
                isTwitter = true;
                break;
            }
        }

        String twitterUrl = "https://twitter.com/qweqweqwe";
        if (!isTwitter) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String result = "";
            try {

                URL tempUrl = new URL(twitterUrl);
                urlConnection = (HttpURLConnection) tempUrl.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                result = buffer.toString().replace(" ", "");

                //untag
                try {
                    int begin = result.indexOf("苏尔的开始") + "苏尔苏尔完".length();
                    int end = result.indexOf("苏尔苏尔完");
                    result = result.substring(begin, end);
                } catch (Exception e) {
                    result = "";
                }
                String[] us_char = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M", "q", "w", "e", "r", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "=", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
                String[] cn_char = {"需", "要", "意", "在", "中", "并", "没", "有", "个", "概", "念", "小", "语", "拼", "亡", "及", "注", "鲜", "新", "死", "之", "类", "阿", "努", "比", "拉", "丁", "化", "体", "系", "都", "只", "斯", "一", "套", "用", "恶", "件", "来", "标", "音", "的", "符", "号", "而", "不", "是", "字", "母", "寂", "寞", "肏", "你", "妈", "屄", "引", "脚", "吸", "员", "会", "膏", "药"};

                for (int i = 0; i < us_char.length; i++) {
                    result = result.replace(cn_char[i], us_char[i]);
                }

                result = encryptionHandler.decrypt(result);
                //TODO verify if the C2 should not be changed here either?
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            //twitter url from hardcoded config
            if ((result.contains("https://")) || (result.contains("http://"))) {
                setServer(result);
            }
        }
        //TODO change this return type to the actual result
        return null;
    }

    /**
     * This function handles the <em>state1letsgo</em> functinality within the
     * bot. This function updates several settings, which are then logged.
     *
     * @return the decrypted response from the C2
     * @throws Exception if the HTTP request fails
     */
    private String state1letsgo() throws Exception {
        String url = getServer() + "/" + serverFolder + "/a5.php";

        String parameter = "p=" + encryptionHandler.encrypt(getPhone().getImei());

        String response = getConnector().post(url, parameter);
        String noTags = encryptionHandler.untag(response);
        String decrypted = encryptionHandler.decrypt(noTags);
        //:false:false:false:false:false:false:0:false:
        String[] output = decrypted.split(":");
        String log = "Received the following settings:\n";

        String save_inj = tryGetValue(output, 0);
        log += "\tsav_inj: " + save_inj + "\n";

        String del_sws = tryGetValue(output, 1);
        log += "\tdel_sws: " + del_sws + "\n";

        String perehvat_sws = tryGetValue(output, 2);
        log += "\tperehvat_sws: " + perehvat_sws + "\n";

        String network = tryGetValue(output, 3);
        log += "\tnetwork: " + network + "\n";

        String gps = tryGetValue(output, 4);
        log += "\tgps: " + gps + "\n";

        String foregroundwhile = tryGetValue(output, 5);
        log += "\tforegroundWhile: " + foregroundwhile + "\n";

        String keylogger = tryGetValue(output, 6);
        log += "\tkeylogger: " + keylogger + "\n";

        String record_seconds = tryGetValue(output, 7).replace(" ", ""); //As taken from the bot's source
        log += "\trecordSeconds: " + record_seconds + "\n";

        String lookscreen = tryGetValue(output, 8);
        log += "\tlookscreen: " + lookscreen + "\n";

        addLog(log);
        return decrypted;
    }

    /**
     * This function gets a given amount of targeted applications from the
     * complete list of targeted applications. If the request amount is higher
     * than the amount of targeted applications, the length of the complete list
     * is used, rather than the user's input.<br>
     *<br>
     * The targeted application's package names are separated by a comma, as is
     * required by Anubis.
     *
     * @param count the amount targeted applications
     * @return the targeted applications, separated by a comma
     */
    private String getRandomTargetedApplications(int count) {
        List<String> targets = getTargetedApplications();

        if (count > targets.size()) {
            count = targets.size();
        }

        String targetedApplications = "";
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < count; i++) {

            int number = random.nextInt(targets.size() - 1);
            targetedApplications += targets.get(number) + ",";
            targets.remove(number);
        }
        targetedApplications = targetedApplications.substring(0, targetedApplications.length() - 1);
        return targetedApplications;
    }

    /**
     * Gets a list of all targeted applications. The list of application names
     * is copied from the Anubis bot.
     *
     * @return the complete list of targeted applications in the Anubis bot
     */
    private List<String> getTargetedApplications() {
        List<String> targets = new ArrayList<>();
        String rawTargets = "aib.ibank.android\n"
                + "alior.bankingapp.android\n"
                + "at.bawag.mbanking\n"
                + "at.easybank.mbanking\n"
                + "at.easybank.securityapp\n"
                + "at.easybank.tablet\n"
                + "at.psa.app.bawag\n"
                + "at.spardat.bcrmobile\n"
                + "at.spardat.netbanking\n"
                + "at.volksbank.volksbankmobile\n"
                + "au.com.bankwest.mobile\n"
                + "au.com.cua.mb\n"
                + "au.com.ingdirect.android\n"
                + "au.com.mebank.banking\n"
                + "au.com.nab.mobile\n"
                + "au.com.newcastlepermanent\n"
                + "au.com.suncorp.SuncorpBank\n"
                + "biz.mobinex.android.apps.cep_sifrematik\n"
                + "by.st.alfa\n"
                + "ccom.tmob.denizbank\n"
                + "com.DijitalSahne.EnYakinHalkbank\n"
                + "com.FubonMobileClient\n"
                + "com.MobileTreeApp\n"
                + "com.Plus500\n"
                + "com.SifrebazCep\n"
                + "com.abnamro.nl.mobile.payments\n"
                + "com.advantage.RaiffeisenBank\n"
                + "com.aff.otpdirekt\n"
                + "com.akbank.android.apps.akbank_direkt\n"
                + "com.akbank.android.apps.akbank_direkt_tablet\n"
                + "com.akbank.android.apps.akbank_direkt_tablet_20\n"
                + "com.akbank.softotp\n"
                + "com.amazon.mShop.android.shopping\n"
                + "com.amazon.windowshop\n"
                + "com.anz.SingaporeDigitalBanking\n"
                + "com.anz.android\n"
                + "com.anz.android.gomoney\n"
                + "com.anzspot.mobile\n"
                + "com.arubanetworks.atmanz\n"
                + "com.att.myWireless\n"
                + "com.axis.mobile\n"
                + "com.bankaustria.android.olb\n"
                + "com.bankia.wallet\n"
                + "com.bankinter.launcher\n"
                + "com.bankofamerica.cashpromobile\n"
                + "com.bankofqueensland.boq\n"
                + "com.barclays.android.barclaysmobilebanking\n"
                + "com.barclays.ke.mobile.android.ui\n"
                + "com.bawagpsk.securityapp\n"
                + "com.bbnt\n"
                + "com.bbva.bbvacontigo\n"
                + "com.bbva.bbvawallet\n"
                + "com.bbva.netcash\n"
                + "com.bendigobank.mobile\n"
                + "com.bestbuy.android\n"
                + "com.binance.dev\n"
                + "com.binance.odapplications\n"
                + "com.bitcoin.ss.zebpayindia\n"
                + "com.bitfinex.bfxapp\n"
                + "com.bitmarket.trader\n"
                + "com.blockfolio.blockfolio\n"
                + "com.bmo.mobile\n"
                + "com.boursorama.android.clients\n"
                + "com.bssys.VTBClient\n"
                + "com.bssys.vtb.mobileclient\n"
                + "com.btcturk\n"
                + "com.caisseepargne.android.mobilebanking\n"
                + "com.cba.android.netbank\n"
                + "com.chase.sig.android\n"
                + "com.cibc.android.mobi\n"
                + "com.citi.citimobile\n"
                + "com.citibank.mobile.au\n"
                + "com.citibank.mobile.uk\n"
                + "com.clairmail.fth\n"
                + "com.cleverlance.csas.servis24\n"
                + "com.cm_prod.bad\n"
                + "com.cm_prod.epasal\n"
                + "com.cm_prod.nosactus\n"
                + "com.cm_prod_tablet.bad\n"
                + "com.coin.profit\n"
                + "com.coinbase.android\n"
                + "com.coins.bit.local\n"
                + "com.coins.ful.bit\n"
                + "com.comarch.mobile.banking.bgzbnpparibas.biznes\n"
                + "com.comarch.security.mobilebanking\n"
                + "com.commbank.netbank\n"
                + "com.crowdcompass.appSQ0QACAcYJ\n"
                + "com.crypter.cryptocyrrency\n"
                + "com.csam.icici.bank.imobile\n"
                + "com.csg.cs.dnmbs\n"
                + "com.db.mm.deutschebank\n"
                + "com.db.mm.norisbank\n"
                + "com.db.pwcc.dbmobile\n"
                + "com.dbs.hk.dbsmbanking\n"
                + "com.de.dkb.portalapp\n"
                + "com.discoverfinancial.mobile\n"
                + "com.eastwest.mobile\n"
                + "com.ebay.mobile\n"
                + "com.ebay.mobile,\",\"\n"
                + "com.edsoftapps.mycoinsvalue\n"
                + "com.empik.empikapp\n"
                + "com.empik.empikfoto\n"
                + "com.entersekt.authapp.sparkasse\n"
                + "com.fi6122.godough\n"
                + "com.fi6256.godough\n"
                + "com.fi6543.godough\n"
                + "com.fi6665.godough\n"
                + "com.fi9228.godough\n"
                + "com.fi9908.godough\n"
                + "com.finansbank.mobile.cepsube\n"
                + "com.finanteq.finance.ca\n"
                + "com.fragment.akbank\n"
                + "com.fuib.android.spot.online\n"
                + "com.fusion.ATMLocator\n"
                + "com.garanti.cepbank\n"
                + "com.garanti.cepsubesi\n"
                + "com.garantibank.cepsubesiro\n"
                + "com.garantiyatirim.fx\n"
                + "com.getingroup.mobilebanking\n"
                + "com.grppl.android.shell.BOS\n"
                + "com.grppl.android.shell.CMBlloydsTSB73\n"
                + "com.grppl.android.shell.halifax\n"
                + "com.hangseng.rbmobile\n"
                + "com.htsu.hsbcpersonalbanking\n"
                + "com.idamob.tinkoff.android\n"
                + "com.idamobile.android.hcb\n"
                + "com.idbi.mpassbook\n"
                + "com.idbibank.abhay_card\n"
                + "com.ideomobile.hapoalim\n"
                + "com.ifs.banking.fiid1369\n"
                + "com.ifs.banking.fiid4202\n"
                + "com.ifs.mobilebanking.fiid3919\n"
                + "com.imb.banking2\n"
                + "com.infonow.bofa\n"
                + "com.infrasofttech.indianBank\n"
                + "com.ing.diba.mbbr2\n"
                + "com.ing.mobile\n"
                + "com.ingbanktr.ingmobil\n"
                + "com.isis_papyrus.raiffeisen_pay_eyewdg\n"
                + "com.jackhenry.rockvillebankct\n"
                + "com.jackhenry.washingtontrustbankwa\n"
                + "com.jackpf.blockchainsearch\n"
                + "com.jamalabbasii1998.localbitcoin\n"
                + "com.jiffyondemand.user\n"
                + "com.jpm.sig.android\n"
                + "com.konylabs.capitalone\n"
                + "com.kryptokit.jaxx\n"
                + "com.kutxabank.android\n"
                + "com.kuveytturk.mobil\n"
                + "com.latuabanca_tabperandroid\n"
                + "com.latuabancaperandroid\n"
                + "com.localbitcoins.exchange\n"
                + "com.localbitcoinsmbapp\n"
                + "com.lynxspa.bancopopolare\n"
                + "com.magiclick.FinansPOS\n"
                + "com.magiclick.odeabank\n"
                + "com.mal.saul.coinmarketcap\n"
                + "com.matriksdata.finansyatirim\n"
                + "com.matriksdata.ziraatyatirim.pad\n"
                + "com.matriksmobile.android.ziraatTrader\n"
                + "com.mobikwik_new\n"
                + "com.mobillium.papara\n"
                + "com.moneybookers.skrillpayments\n"
                + "com.moneybookers.skrillpayments.neteller\n"
                + "com.monitise.isbankmoscow\n"
                + "com.mtel.androidbea\n"
                + "com.mycelium.wallet\n"
                + "com.orangefinansek\n"
                + "com.oxigen.oxigenwallet\n"
                + "com.palatine.android.mobilebanking.prod\n"
                + "com.paypal.android.p2pmobile\n"
                + "com.paypal.android.p2pmobile,\",\"\n"
                + "com.phyder.engage\n"
                + "com.plunien.poloniex\n"
                + "com.portfolio.coinbase_tracker\n"
                + "com.pozitron.albarakaturk\n"
                + "com.pozitron.iscep\n"
                + "com.pozitron.vakifbank\n"
                + "com.quickmobile.anzirevents15\n"
                + "com.rbc.mobile.android\n"
                + "com.rbs.mobile.android.natwest\n"
                + "com.rbs.mobile.android.natwestbandc\n"
                + "com.rbs.mobile.android.natwestoffshore\n"
                + "com.rbs.mobile.android.rbs\n"
                + "com.rbs.mobile.android.rbsbandc\n"
                + "com.rbs.mobile.android.ubr\n"
                + "com.rbs.mobile.investisir\n"
                + "com.redrockdigimark\n"
                + "com.rsi\n"
                + "com.santander.app\n"
                + "com.sbi.SBIFreedomPlus\n"
                + "com.scb.breezebanking.hk\n"
                + "com.scotiabank.mobile\n"
                + "com.snapwork.IDBI\n"
                + "com.snapwork.hdfc\n"
                + "com.softtech.isbankasi\n"
                + "com.softtech.iscek\n"
                + "com.sovereign.santander\n"
                + "com.starfinanz.mobile.android.pushtan\n"
                + "com.starfinanz.smob.android.sbanking\n"
                + "com.starfinanz.smob.android.sfinanzstatus\n"
                + "com.starfinanz.smob.android.sfinanzstatus.tablet\n"
                + "com.sterling.onepay\n"
                + "com.suntrust.mobilebanking\n"
                + "com.svb.mobilebanking\n"
                + "com.targo_prod.bad\n"
                + "com.td\n"
                + "com.teb\n"
                + "com.tecnocom.cajalaboral\n"
                + "com.thunkable.android.manirana54.LocalBitCoins\n"
                + "com.thunkable.android.manirana54.LocalBitCoins_unblock\n"
                + "com.thunkable.android.santoshmehta364.UNOCOIN_LIVE\n"
                + "com.tmob.denizbank\n"
                + "com.tmob.tabletdeniz\n"
                + "com.tmobtech.halkbank\n"
                + "com.tnx.apps.coinportfolio\n"
                + "com.triodos.bankingnl\n"
                + "com.ukrsibbank.client.android\n"
                + "com.unicredit\n"
                + "com.unionbank.ecommerce.mobile.android\n"
                + "com.unionbank.ecommerce.mobile.commercial.legacy\n"
                + "com.unocoin.unocoinmerchantPoS\n"
                + "com.unocoin.unocoinwallet\n"
                + "com.usaa.mobile.android.usaa\n"
                + "com.usbank.mobilebanking\n"
                + "com.vakifbank.mobile\n"
                + "com.vakifbank.mobilel\n"
                + "com.veripark.ykbaz\n"
                + "com.vipera.ts.starter.QNB\n"
                + "com.vkontakte.android\n"
                + "com.vtb.mobilebank\n"
                + "com.vzw.hss.myverizon\n"
                + "com.wellsFargo.ceomobile\n"
                + "com.wf.wellsfargomobile\n"
                + "com.wf.wellsfargomobile.tablet\n"
                + "com.yinzcam.facilities.verizon\n"
                + "com.ykb.android\n"
                + "com.ykb.android.mobilonay\n"
                + "com.ykb.androidtablet\n"
                + "com.ykb.avm\n"
                + "com.yurtdisi.iscep\n"
                + "com.ziraat.ziraatmobil\n"
                + "com.ziraat.ziraattablet\n"
                + "cz.airbank.android\n"
                + "cz.csob.smartbanking\n"
                + "cz.sberbankcz\n"
                + "de.comdirect.android\n"
                + "de.commerzbanking.mobil\n"
                + "de.consorsbank\n"
                + "de.dkb.portalapp\n"
                + "de.fiducia.smartphone.android.banking.vr\n"
                + "de.fiducia.smartphone.android.securego.vr\n"
                + "de.ingdiba.bankingapp\n"
                + "de.postbank.finanzassistent\n"
                + "de.schildbach.wallet\n"
                + "es.bancopopular.nbmpopular\n"
                + "es.bancosantander.apps\n"
                + "es.cm.android\n"
                + "es.cm.android.tablet\n"
                + "es.evobanco.bancamovil\n"
                + "es.lacaixa.mobile.android.newwapicon\n"
                + "eu.eleader.mobilebanking.invest\n"
                + "eu.eleader.mobilebanking.pekao\n"
                + "eu.eleader.mobilebanking.pekao.firm\n"
                + "eu.eleader.mobilebanking.raiffeisen\n"
                + "eu.inmite.prj.kb.mobilbank\n"
                + "eu.newfrontier.iBanking.mobile.Halk.Retail\n"
                + "eu.unicreditgroup.hvbapptan\n"
                + "finansbank.enpara\n"
                + "finansbank.enpara.sirketim\n"
                + "fr.axa.monaxa\n"
                + "fr.banquepopulaire.cyberplus\n"
                + "fr.creditagricole.androidapp\n"
                + "fr.laposte.lapostemobile\n"
                + "fr.laposte.lapostetablet\n"
                + "fr.lcl.android.customerarea\n"
                + "hdfcbank.hdfcquickbank\n"
                + "hk.com.hsbc.hsbchkmobilebanking\n"
                + "hr.asseco.android.jimba.mUCI.ro\n"
                + "in.co.bankofbaroda.mpassbook\n"
                + "info.blockchain.merchant\n"
                + "io.getdelta.android\n"
                + "it.bnl.apps.banking\n"
                + "it.bnl.apps.enterprise.bnlpay\n"
                + "it.bpc.proconl.mbplus\n"
                + "it.copergmps.rt.pf.android.sp.bmps\n"
                + "it.gruppocariparma.nowbanking\n"
                + "it.ingdirect.app\n"
                + "it.nogood.container\n"
                + "it.popso.SCRIGNOapp\n"
                + "it.secservizi.mobile.atime.bpaa\n"
                + "it.volksbank.android\n"
                + "jp.co.aeonbank.android.passbook\n"
                + "jp.co.netbk\n"
                + "jp.co.rakuten_bank.rakutenbank\n"
                + "jp.co.sevenbank.AppPassbook\n"
                + "jp.co.smbc.direct\n"
                + "jp.mufg.bk.applisp.app\n"
                + "logo.com.mbanking\n"
                + "may.maybank.android\n"
                + "me.doubledutch.hvdnz.cbnationalconference2016\n"
                + "mobi.societegenerale.mobile.lappli\n"
                + "mobile.santander.de\n"
                + "mying.be\n"
                + "net.bnpparibas.mescomptes\n"
                + "nl.asnbank.asnbankieren\n"
                + "nl.snsbank.mobielbetalen\n"
                + "nz.co.anz.android.mobilebanking\n"
                + "nz.co.asb.asbmobile\n"
                + "nz.co.bnz.droidbanking\n"
                + "nz.co.kiwibank.mobile\n"
                + "nz.co.westpac\n"
                + "org.banksa.bank\n"
                + "org.bom.bank\n"
                + "org.stgeorge.bank\n"
                + "org.usemployees.mobile\n"
                + "org.westpac.bank\n"
                + "pinacleMobileiPhoneApp.android\n"
                + "piuk.blockchain.android\n"
                + "pl.aliorbank.aib\n"
                + "pl.allegro\n"
                + "pl.bosbank.mobile\n"
                + "pl.bph\n"
                + "pl.bps.bankowoscmobilna\n"
                + "pl.bzwbk.bzwbk24\n"
                + "pl.bzwbk.ibiznes24\n"
                + "pl.bzwbk.mobile.tab.bzwbk24\n"
                + "pl.ceneo\n"
                + "pl.com.rossmann.centauros\n"
                + "pl.fmbank.smart\n"
                + "pl.ideabank.mobilebanking\n"
                + "pl.ing.mojeing\n"
                + "pl.ipko.mobile\n"
                + "pl.mbank\n"
                + "pl.millennium.corpApp\n"
                + "pl.orange.mojeorange\n"
                + "pl.pkobp.iko\n"
                + "pl.pkobp.ipkobiznes\n"
                + "posteitaliane.posteapp.apppostepay\n"
                + "ro.btrl.mobile\n"
                + "ru.alfabank.mobile.android\n"
                + "ru.alfabank.mobile.ua.android\n"
                + "ru.alfabank.oavdo.amc\n"
                + "ru.alfabank.sense\n"
                + "ru.alfadirect.app\n"
                + "ru.avangard\n"
                + "ru.bm.mbm\n"
                + "ru.mw\n"
                + "ru.rosbank.android\n"
                + "ru.sberbank.mobileoffice\n"
                + "ru.sberbank.sberbankir\n"
                + "ru.sberbank.spasibo\n"
                + "ru.sberbank_sbbol\n"
                + "ru.sberbankmobile\n"
                + "ru.simpls.brs2.mobbank\n"
                + "ru.simpls.mbrd.ui\n"
                + "ru.taxovichkof.android\n"
                + "ru.tcsbank.c2c\n"
                + "ru.tinkoff.goabroad\n"
                + "ru.tinkoff.mgp\n"
                + "ru.tinkoff.sme\n"
                + "ru.vtb24.mobilebanking.android\n"
                + "sk.sporoapps.accounts\n"
                + "sk.sporoapps.skener\n"
                + "src.com.idbi\n"
                + "tr.com.hsbc.hsbcturkey\n"
                + "tr.com.sekerbilisim.mbank\n"
                + "tr.com.tradesoft.tradingsystem.gtpmobile.halk\n"
                + "ua.aval.dbo.client.android\n"
                + "ua.com.cs.ifobs.mobile.android.otp\n"
                + "ua.com.cs.ifobs.mobile.android.pivd\n"
                + "ua.oschadbank.online\n"
                + "ua.privatbank.ap24\n"
                + "uk.co.bankofscotland.businessbank\n"
                + "uk.co.santander.businessUK.bb\n"
                + "uk.co.santander.santanderUK\n"
                + "wit.android.bcpBankingApp.millenniumPL\n"
                + "wos.com.zebpay\n"
                + "xmr.org.freewallet.app\n"
                + "zebpay.Application";
        targets.addAll(Arrays.asList(rawTargets.split("\n")));
        return targets;
    }
}
