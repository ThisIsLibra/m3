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
package bot;

import device.Contact;
import device.SharedPreferences;
import device.SmsManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * This abstract class is meant to contain all fields that are required to
 * initialise a bot specific family object. The structure of this class is flat,
 * meaning its not user friendly when programming, but it does allow Gson to
 * easily write the files to disk, and read them from the disk.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public abstract class Config {

    /**
     * The bot's name, which is only meant to be used locally for logging
     * purposes
     */
    private String botName;

    /**
     * The bot's family, as defined in bot.Families
     */
    private String botFamily;

    /**
     * The bot's tag, which is visible for the C2 operator, and must be in line
     * with the (few) tag(s) that are used within a given campaign
     */
    private String tag;

    /**
     * The full path toe the folder that is used as the local file system for
     * this bot
     */
    private String localFileSystem;

    /**
     * The phone's fake IMEI number
     */
    private String imei;

    /**
     * The phone's fake number
     */
    private String number;

    /**
     * The phone's fake network operator
     */
    private String networkOperatorName;

    /**
     * The phone's fake locale, as a ISO-3166-1 alpha-2 country code
     *
     * @see
     * <a href="https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes">Wikipedia's
     * list of ISO 3166 country codes</a>
     */
    private String locale;

    /**
     * The phone's fake version, as defined in Build.VERSION.RELEASE
     *
     * @see <a
     * href="https://developer.android.com/reference/android/os/Build.VERSION#RELEASE">Android's
     * developer website</a>
     */
    private String version;

    /**
     * The phone's fake model, as defined in Build.MODEL
     *
     * @see <a
     * href="https://developer.android.com/reference/android/os/Build#MODEL">Android's
     * developer website</a>
     */
    private String model;

    /**
     * The phone's fake product, as defined in Build.PRODUCT
     *
     * @see <a
     * href="https://developer.android.com/reference/android/os/Build#PRODUCT">Android's
     * developer website</a>
     */
    private String product;

    /**
     * The SMS manager of the phone, which is filled with fake SMS messages
     */
    private SmsManager smsManager;

    /**
     * Fake contacts within the phone
     */
    private List<Contact> contacts;

    /**
     * The shared preferences in the phone. The bot specific shared preferences
     * are stored in here, but the values in here can also be used by the
     * programmer to store values that are only used locally.
     */
    private SharedPreferences sharedPreferences;

    /**
     * The permissions that the bot has on the phone are present in this list.
     * Since this variable is a set, there can be no duplicate entries. As such,
     * one can always try to add a value when in doubt.
     */
    private Set<String> permissions;

    /**
     * A list of installed applications, where each entry is the package name of
     * the installed application. Since this variable is a set, there can be no
     * duplicate entries. As such, one can always try to add a value when in
     * doubt.
     */
    private Set<String> installedApplications;

    /**
     * A boolean that defines if the phone is rooted
     */
    private boolean rooted;

    /**
     * Defines if the bot is the default SMS manager
     */
    private boolean defaultSmsManager;

    /**
     * The C2 server of the bot
     */
    private String server;

    /**
     * A list of old C2 servers that were used by this bot. Duplicates may exist
     * if an older C2 was reused
     */
    private List<String> oldServers;

    /**
     * Defines if the bot is registered
     */
    private boolean registered;

    /**
     * The user-agent to use when connecting the C2
     */
    private String userAgent;

    /**
     * The interval between C2 connections in seconds
     */
    private int interval;

    /**
     * A boolean that defines if the bot is administrator on the device
     */
    private boolean deviceAdmin;

    /**
     * A boolean that defines if the phone's screen is locked
     */
    private boolean locked;

    /**
     * The battery percentage of the phone, ranging between 1 and 100
     */
    private int batteryPercentage;

    /**
     * Defines if the bot is active
     */
    private boolean active;

    /**
     * The next moment in time when the C2 should be contact
     */
    private LocalDateTime nextPollMoment;

    /**
     * The address of the proxy server to use. This variable is null if no proxy
     * server is to be used
     */
    private String proxyAddress;

    /**
     * The port of the proxy server. This variable is null if no proxy server is
     * to be used
     */
    private Integer proxyPort;

    /**
     * The constructor is used via the <em>super</em> call in family specific
     * config classes. Since this class is abstract, it cannot be instantiated
     * on itself. This class contains all basic fields that a bots need
     *
     * @param nextPollMoment the next moment in time to contact the C2
     * @param botName the bot's name, which is only used locally
     * @param botFamily the bot's family name, as defined in bot.Families
     * @param tag the bot's tag
     * @param localFileSystem the full path to the local file system folder
     * @param imei a fake IMEI number
     * @param number a fake phone number
     * @param networkOperatorName a fake network operator name
     * @param locale an ISO-3166-1 alpha-2 country code
     * @param version the phone's fake version, as defined in
     * Build.VERSION.RELEASE
     * @param model the phone's fake model, as defined in Build.MODEL
     * @param product the phone's fake product, as defined in Build.PRODUCT
     * @param smsManager the phone's SMS manager
     * @param contacts fake contacts
     * @param server the bot's C2 server
     * @param sharedPreferences shared preferences for the phone
     * @param permissions the bot's permissions on the phone
     * @param oldServers a list of old C2 servers
     * @param registered true if the bot is registered, false if not
     * @param installedApplications a list of installed applications on the
     * phone
     * @param defaultSmsManager true if the bot is the default SMS manager on
     * the phone, false if not
     * @param userAgent the user-agent to use when contacting the C2
     * @param rooted a boolean that defines if the phone is rooted
     * @param interval the interval between connecting the C2 in seconds
     * @param active true if the bot is active, false if not
     * @param locked a boolean that defines if the phone's screen is locked
     * @param batteryPercentage the battery percentage of the phone
     * @param proxyAddress the address for the proxy server, if one is to be
     * used. Provide null as a value if no proxy server is to be used
     * @param deviceAdmin a boolean that defines if the bot has admin privileges
     * on the device
     * @param proxyPort the port for the proxy server, if one is to be used.
     * Provide null as a value if no proxy server is to be used
     */
    public Config(LocalDateTime nextPollMoment, String botName, String botFamily, String tag, String localFileSystem, String imei, String number, String networkOperatorName, String locale, String version, String model, String product, SmsManager smsManager, List<Contact> contacts, SharedPreferences sharedPreferences, Set<String> permissions, Set<String> installedApplications, String server, List<String> oldServers, boolean registered, String userAgent, int interval, boolean rooted, boolean defaultSmsManager, boolean deviceAdmin, boolean locked, int batteryPercentage, boolean active, String proxyAddress, Integer proxyPort) {
        this.nextPollMoment = nextPollMoment;
        this.botName = botName;
        this.botFamily = botFamily;
        this.tag = tag;
        this.localFileSystem = localFileSystem;
        this.imei = imei;
        this.number = number;
        this.networkOperatorName = networkOperatorName;
        this.locale = locale;
        this.version = version;
        this.model = model;
        this.product = product;
        this.smsManager = smsManager;
        this.contacts = contacts;
        this.sharedPreferences = sharedPreferences;
        this.permissions = permissions;
        this.installedApplications = installedApplications;
        this.server = server;
        this.oldServers = oldServers;
        this.registered = registered;
        this.userAgent = userAgent;
        this.interval = interval;
        this.rooted = rooted;
        this.defaultSmsManager = defaultSmsManager;
        this.deviceAdmin = deviceAdmin;
        this.locked = locked;
        this.batteryPercentage = batteryPercentage;
        this.active = active;
        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
    }

    /**
     * Gets the bot name, which is only intended for local use
     *
     * @return the name of the bot, meant for local use
     */
    public String getBotName() {
        return botName;
    }

    /**
     * Gets the bot's family, as defined in bot.Families
     *
     * @return the bot's family name
     */
    public String getBotFamily() {
        return botFamily;
    }

    /**
     * Gets the bot's tag, which is visible for the C2 operator, and must be in
     * line with the (few) tag(s) that are used within a given campaign
     *
     * @return the bot's tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Gets the full path to the local file system folder
     *
     * @return the full path to the local file system folder
     */
    public String getLocalFileSystem() {
        return localFileSystem;
    }

    /**
     * The phone's fake IMEI number
     *
     * @return the fake IMEI
     */
    public String getImei() {
        return imei;
    }

    /**
     * The phone's fake number
     *
     * @return the fake number
     */
    public String getNumber() {
        return number;
    }

    /**
     * The phone's fake network operator
     *
     * @return the fake network operator
     */
    public String getNetworkOperatorName() {
        return networkOperatorName;
    }

    /**
     * The phone's fake locale, as a ISO-3166-1 alpha-2 country code
     *
     * @return the ISO-3166-1 alpha-2 country code as the locale
     * @see
     * <a href="https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes">Wikipedia's
     * list of ISO 3166 country codes</a>
     */
    public String getLocale() {
        return locale;
    }

    /**
     * The phone's fake version, as defined in Build.VERSION.RELEASE
     *
     * @return the fake version
     * @see <a
     * href="https://developer.android.com/reference/android/os/Build.VERSION#RELEASE">Android's
     * developer website</a>
     */
    public String getVersion() {
        return version;
    }

    /**
     * The phone's fake model, as defined in Build.MODEL
     *
     * @return the fake model
     * @see <a
     * href="https://developer.android.com/reference/android/os/Build#MODEL">Android's
     * developer website</a>
     */
    public String getModel() {
        return model;
    }

    /**
     * The phone's fake product, as defined in Build.PRODUCT
     *
     * @return the fake product
     * @see <a
     * href="https://developer.android.com/reference/android/os/Build#PRODUCT">Android's
     * developer website</a>
     */
    public String getProduct() {
        return product;
    }

    /**
     * The SMS manager of the phone, which is filled with fake SMS messages
     *
     * @return the SMS manager
     */
    public SmsManager getSmsManager() {
        return smsManager;
    }

    /**
     * Fake contacts within the phone
     *
     * @return a list of fake contacts in the phone
     */
    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * The shared preferences in the phone. The bot specific shared preferences
     * are stored in here, but the values in here can also be used by the
     * programmer to store values that are only used locally.
     *
     * @return the shared preferences
     */
    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    /**
     * A list of installed applications, where each entry is the package name of
     * the installed application. Since this variable is a set, there can be no
     * duplicate entries. As such, one can always try to add a value when in
     * doubt.
     *
     * @return a list of installed application package names
     */
    public Set<String> getInstalledApplications() {
        return installedApplications;
    }

    /**
     * Gets the C2 server of the bot
     *
     * @return the C2 server
     */
    public String getServer() {
        return server;
    }

    /**
     * Gets the list of old C2 servers that were used by this bot. Duplicates
     * may exist if an older C2 was reused
     *
     * @return a list of old C2 servers
     */
    public List<String> getOldServers() {
        return oldServers;
    }

    /**
     * Gets the boolean which defines if the bot is registered
     *
     * @return true if the bot is registered, false if not
     */
    public boolean isRegistered() {
        return registered;
    }

    /**
     * The user-agent to use when connecting the C2
     *
     * @return the user-agent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Gets the interval between moments to contact the C2 server in seconds
     *
     * @return the polling interval in seconds
     */
    public int getInterval() {
        return interval;
    }

    /**
     * A boolean that defines if the phone is rooted
     *
     * @return if the devices is rooted
     */
    public boolean isRooted() {
        return rooted;
    }

    /**
     * A boolean that defines if the bot is device admin
     *
     * @return if the bot is device admin
     */
    public boolean isDeviceAdmin() {
        return deviceAdmin;
    }

    /**
     * Gets the boolean that defines if the bot is the default SMS manager.
     *
     * @return true if the bot is the default SMS manager, false if not
     */
    public boolean isDefaultSmsManager() {
        return defaultSmsManager;
    }

    /**
     * A boolean that defines if the phone's screen is locked
     *
     * @return if the phone's screen is locked
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * The battery percentage of the phone, ranging between 1 and 100
     *
     * @return the battery percentage of the phone
     */
    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    /**
     * The permissions that the bot has on the phone are present in this list.
     * Since this variable is a set, there can be no duplicate entries. As such,
     * one can always try to add a value when in doubt.
     *
     * @return a list of the permissions of the bot
     */
    public Set<String> getPermissions() {
        return permissions;
    }

    /**
     * Gets the boolean that defines if this bot is active. An inactive bot is
     * removed from the scheduler, based on this value. Cases where a bot is
     * inactive can be due to the fact that the C2 was taken offline, or if the
     * bot received the command to uninstall the malware from the device
     *
     * @return true if the bot is active, false if not
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Gets the next moment in time to contact the C2 server
     *
     * @return the next moment in time to contact the C2 server
     */
    public LocalDateTime getNextPollMoment() {
        return nextPollMoment;
    }

    /**
     * Gets the address at which the proxy server should be connected. If no
     * proxy server is to be used for this instance, the returned value is null!
     *
     * @return the address at which the proxy server should be connected, or
     * null if no proxy server is to be used for this instance
     */
    public String getProxyAddress() {
        return proxyAddress;
    }

    /**
     * Gets the port at which the proxy server should be connected. If no proxy
     * server is to be used for this instance, the returned value is null!
     *
     * @return the port at which the proxy server should be connected, or null
     * if no proxy server is to be used for this instance
     */
    public Integer getProxyPort() {
        return proxyPort;
    }
}
