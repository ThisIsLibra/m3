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
package device;

import java.util.List;
import java.util.Set;

/**
 * The Phone object is used to contain all fields and functions that a normal
 * mobile phone would have. Only the parts that are often (ab)used by malware
 * are implemented here, as the overhead of providing too many details for each
 * bot would require too much overhead.<br>
 * <br>
 * Overall, this object only contains phone-related fields and functions. As
 * such, knowing if the bot is the default SMS manager on the fake Phone is
 * something that one will find within the bot specific class (this specific
 * example resides in the abstract Bot class). Obtaining the fake phone number
 * of the phone is something that is present in this class.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class Phone {

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
     * The user-agent to use when connecting the C2
     */
    private String userAgent;

    /**
     * A boolean that defines if the phone is rooted
     */
    private boolean rooted;

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
     * This creates a phone object, which contains the Android related logic.
     * Note that all information that is provided to this object, should be
     * controlled by whoever is using m3, and should not lead (not even
     * accidentally) to information that is of a third party!
     *
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
     * @param sharedPreferences shared preferences for the phone
     * @param permissions the bot's permissions on the phone
     * @param installedApplications a list of installed applications on the
     * phone
     * @param userAgent the user-agent to use when contacting the C2
     * @param rooted a boolean that defines if the phone is rooted
     * @param deviceAdmin a boolean that defines if the bot has admin privileges
     * on the device
     * @param locked a boolean that defines if the phone's screen is locked
     * @param batteryPercentage the battery percentage of the phone
     */
    public Phone(String imei, String number, String networkOperatorName, String locale, String version, String model, String product, SmsManager smsManager, List<Contact> contacts, SharedPreferences sharedPreferences, Set<String> permissions, Set<String> installedApplications, String userAgent, boolean rooted, boolean deviceAdmin, boolean locked, int batteryPercentage) {
        this.imei = imei;
        this.number = number;
        this.networkOperatorName = networkOperatorName;
        this.locale = locale;
        this.version = version;
        this.model = model;
        this.product = product;
        this.smsManager = smsManager;
        this.contacts = contacts;
        this.installedApplications = installedApplications;
        this.userAgent = userAgent;
        this.sharedPreferences = sharedPreferences;
        this.permissions = permissions;
        this.rooted = rooted;
        this.deviceAdmin = deviceAdmin;
        this.locked = locked;
        this.batteryPercentage = batteryPercentage;
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
     * The user-agent to use when connecting the C2
     *
     * @return the user-agent
     */
    public String getUserAgent() {
        return userAgent;
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
     * Sets the phone's fake IMEI number
     *
     * @param imei the new fake IMEI
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * Sets the phone's fake number
     *
     * @param number the new fake number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Sets the phone's fake network operator
     *
     * @param networkOperatorName the new fake network operator name
     */
    public void setNetworkOperatorName(String networkOperatorName) {
        this.networkOperatorName = networkOperatorName;
    }

    /**
     * Sets the phone's fake locale, which needs to be written as a ISO-3166-1
     * alpha-2 country code, although this is not enforced
     *
     * @param locale the new locale
     * @see
     * <a href="https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes">Wikipedia's
     * list of ISO 3166 country codes</a>
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Sets the phone's fake version, as defined in Build.VERSION.RELEASE
     *
     * @param version the new fake version
     * @see <a
     * href="https://developer.android.com/reference/android/os/Build.VERSION#RELEASE">Android's
     * developer website</a>
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Sets the phone's fake model, as defined in Build.MODEL
     *
     * @param model the new fake model
     * @see <a
     * href="https://developer.android.com/reference/android/os/Build#MODEL">Android's
     * developer website</a>
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Sets the phone's fake product, as defined in Build.PRODUCT
     *
     * @param product the new fake product
     * @see <a
     * href="https://developer.android.com/reference/android/os/Build#PRODUCT">Android's
     * developer website</a>
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Sets the SMS manager of the phone, which is filled with fake SMS messages
     *
     * @param smsManager the new SMS manager
     */
    public void setSmsManager(SmsManager smsManager) {
        this.smsManager = smsManager;
    }

    /**
     * Sets the fake contacts within the phone
     *
     * @param contacts the new fake contact list
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Sets the shared preferences in the phone. The bot specific shared
     * preferences are stored in here, but the values in here can also be used
     * by the programmer to store values that are only used locally.
     *
     * @param sharedPreferences the new shared preferences
     */
    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    /**
     * Sets the bot's permissions on the phone. Since this variable is a set,
     * there can be no duplicate entries. As such, one can always try to add a
     * value when in doubt.
     *
     * @param permissions the new list of permissions
     */
    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    /**
     * Sets the list of installed applications, where each entry is the package
     * name of the installed application. Since this variable is a set, there
     * can be no duplicate entries. As such, one can always try to add a value
     * when in doubt.
     *
     * @param installedApplications the new list of installed applications
     */
    public void setInstalledApplications(Set<String> installedApplications) {
        this.installedApplications = installedApplications;
    }

    /**
     * Sets the user-agent to use when connecting the C2
     *
     * @param userAgent the new user-agent
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * Sets the boolean that defines if the phone is rooted
     *
     * @param rooted true if the phone is rooted, false if not
     */
    public void setRooted(boolean rooted) {
        this.rooted = rooted;
    }

    /**
     * Sets the boolean that defines if the bot is administrator on the device
     *
     * @param deviceAdmin true if the bot is administrator on the device, false
     * if not
     */
    public void setDeviceAdmin(boolean deviceAdmin) {
        this.deviceAdmin = deviceAdmin;
    }

    /**
     * Sets the boolean that defines if the phone's screen is locked
     *
     * @param locked true if the screen is locked, false if not
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * The battery percentage of the phone, ranging between 1 and 100. Any value
     * lower than 1 will result in a value of 1. Any value of higher than 100
     * will result in a value of 100
     *
     * @param batteryPercentage the new battery percentage
     */
    public void setBatteryPercentage(int batteryPercentage) {
        if (batteryPercentage < 1) {
            batteryPercentage = 1;
        } else if (batteryPercentage > 100) {
            batteryPercentage = 100;
        }

        this.batteryPercentage = batteryPercentage;
    }
}
