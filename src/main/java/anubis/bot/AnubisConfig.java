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

import bot.Config;
import bot.IConfig;
import device.Contact;
import device.SharedPreferences;
import device.SmsManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * The configuration file for Anubis, which implements the IConfig interface and
 * the abstract Config class. The abstract class contains all generic fields,
 * meaning only additional values are required to be stored in this class.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class AnubisConfig extends Config implements IConfig {

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
     * Creates an Anubis bot based on the given data
     *
     * @param nextPollMoment the next moment in time to poll
     * @param botName the bot's internal name, which is only used locally
     * @param botFamily the bot's family
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
     * @param sharedPreferences shared preferences for the phone
     * @param permissions the bot's permissions on the phone
     * @param installedApplications a list of installed applications on the
     * phone
     * @param server the C2 server WITHOUT any trailing slashes
     * @param oldServers a list of old C2 servers
     * @param registered a boolean that defines if the bot is registered
     * @param userAgent the user-agent to use when contacting the C2
     * @param interval the interval in seconds between the polling moments
     * @param rooted a boolean that defines if the phone is rooted
     * @param defaultSmsManager the default SMS manager object
     * @param deviceAdmin a boolean that defines if the bot has admin privileges
     * on the device
     * @param locked a boolean that defines if the phone's screen is locked
     * @param batteryPercentage the battery percentage of the phone
     * @param active a boolean that defines if this bot is active
     * @param proxyAddress the address of the proxy server (provide null if no
     * proxy should be used)
     * @param proxyPort the port of the proxy server (provide null if no proxy
     * should be used)
     * @param rc4Key the RC-4 key that is used to encrypt and decrypt data
     * @param serverFolder the folder that contains the Anubis PHP files,
     * without trailing nor leading slashes
     */
    public AnubisConfig(LocalDateTime nextPollMoment, String botName, String botFamily, String tag, String localFileSystem, String imei, String number, String networkOperatorName, String locale, String version, String model, String product, SmsManager smsManager, List<Contact> contacts, SharedPreferences sharedPreferences, Set<String> permissions, Set<String> installedApplications, String server, List<String> oldServers, boolean registered, String userAgent, int interval, boolean rooted, boolean defaultSmsManager, boolean deviceAdmin, boolean locked, int batteryPercentage, boolean active, String proxyAddress, Integer proxyPort, String rc4Key, String serverFolder) {
        super(nextPollMoment, botName, botFamily, tag, localFileSystem, imei, number, networkOperatorName, locale, version, model, product, smsManager, contacts, sharedPreferences, permissions, installedApplications, server, oldServers, registered, userAgent, interval, rooted, defaultSmsManager, deviceAdmin, locked, batteryPercentage, active, proxyAddress, proxyPort);
        this.rc4Key = rc4Key;
        this.serverFolder = serverFolder;
    }

    /**
     * Gets the RC-4 key to encrypt and decrypt data
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

}
