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

import bot.network.Connector;
import com.google.gson.Gson;
import device.Phone;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This abstract class contains all the logic that the framework requires, aside
 * from the register, poll, and save functionality of a family specific bot
 * implementation.<br>
 * <br>
 * The logging functionality is implemented in this class, and is used in
 * several functions. The functions that automatically add logging state this in
 * their respective documentation.<br>
 * <br>
 * The goal of this class is to contain all fields and functions that are
 * required to interact with the bot. Additionally, the embedded phone object
 * will allow the programmer to easily interact with the fake device.
 * Functionality that is related to the bot is present in this class, whereas
 * functionality that relate to the phone, is present in the phone object.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public abstract class Bot {

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
     * The phone object, which contains all device related logic and information
     */
    private Phone phone;

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
     * The full path toe the folder that is used as the local file system for
     * this bot
     */
    private String localFileSystem;

    /**
     * The local file system manager, which abstracts the file logic away from
     * the bot class. The instance uses the local file system folder as its base
     */
    private LocalFileSystemManager localFileSystemManager;

    /**
     * Defines if the bot is registered
     */
    private boolean registered;

    /**
     * Defines if the bot is the default SMS manager
     */
    private boolean defaultSmsManager;

    /**
     * The interval between C2 connections in seconds
     */
    private int interval;

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
     * A boolean that defines if a proxy server is to be used. If, and only if,
     * both proxyAddress and proxyPort are null, this value is set to false. If
     * both are not null, the values are used to connect with the proxy server
     */
    private boolean proxy;

    /**
     * The connector object that is used to send HTTP requests
     */
    private Connector connector;

    /**
     * The constructor is used via the <em>super</em> call in bot specific
     * classes. Since this class is abstract, it cannot be instantiated on
     * itself. This class contains all bot related logic, including the phone
     * related logic via the embedded phone object.
     *
     * @param nextPollMoment the next moment in time to contact the C2
     * @param botName the bot's name, which is only used locally
     * @param botFamily the bot's family name, as defined in bot.Families
     * @param tag the bot's tag
     * @param localFileSystem the full path to the local file system folder
     * @param phone the embedded phone object
     * @param server the bot's C2 server
     * @param oldServers a list of old C2 servers
     * @param registered true if the bot is registered, false if not
     * @param defaultSmsManager true if the bot is the default SMS manager on
     * the phone, false if not
     * @param interval the interval between connecting the C2 in seconds
     * @param active true if the bot is active, false if not
     * @param proxyAddress the address for the proxy server, if one is to be
     * used. Provide null as a value if no proxy server is to be used
     * @param proxyPort the port for the proxy server, if one is to be used.
     * Provide null as a value if no proxy server is to be used
     */
    public Bot(LocalDateTime nextPollMoment, String botName, String botFamily, String tag, String localFileSystem, Phone phone, String server, List<String> oldServers, boolean registered, boolean defaultSmsManager, int interval, boolean active, String proxyAddress, Integer proxyPort) {
        this.botName = botName;
        this.botFamily = botFamily;
        this.tag = tag;
        this.oldServers = oldServers;
        this.registered = registered;
        this.defaultSmsManager = defaultSmsManager;

        this.phone = phone;
        this.server = server;

        this.localFileSystem = localFileSystem;
        localFileSystemManager = new LocalFileSystemManager(localFileSystem);

        this.active = active;

        this.nextPollMoment = nextPollMoment;

        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
        this.connector = new Connector(proxyAddress, proxyPort, phone.getUserAgent());
        if (proxyAddress == null && proxyPort == null) {
            proxy = false;
        } else {
            proxy = true;
        }
        this.interval = interval;
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
     * Sets the next moment in time to contact the C2 server.
     *
     * @param nextPollMoment the next moment to contact the C2 server
     */
    public void setNextPollMoment(LocalDateTime nextPollMoment) {
        this.nextPollMoment = nextPollMoment;
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
     * Sets the interval between moments to contact the C2 server in seconds.
     * This function also adds a logging entry about the change. This function
     * only logs the change if the newly provided value is not the same as the
     * value it already had.
     *
     * @param interval the new interval in seconds
     */
    public void setInterval(int interval) {
        if (interval != interval) {
            this.interval = interval;
            addLog("Changed the interval from " + this.interval + " to " + interval);
        }
    }

    /**
     * This function saves the given IConfig object to the local file system
     * folder as "config.json". Any file with the same name in that location is
     * overwritten, thereby updating the configuration file of this bot on the
     * disk.<br>
     * <br>
     * Additionally, this action is logged via the implemented logging function.
     *
     * @param config the config object to save
     */
    public void saveConfig(IConfig config) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(config);
            String localPath = "config.json";

            localFileSystemManager.overwriteFile(json.getBytes(), localPath);
            addLog("Updated the \"config.json\" file in the local file system at \"" + getLocalFileSystem() + "\"");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Adds the given string to the log. The log is printed to the standard
     * output, and to the <em>log.txt</em> file in the bot's local file system
     *
     * @param log the log content to add to the log
     */
    public void addLog(String log) {
        localFileSystemManager.addLog(log, "log.txt");
    }

    /**
     * Gets the embedded phone object, which contains all logic and information
     * related to the Android parts of the bot
     *
     * @return the embedded phone object
     */
    public Phone getPhone() {
        return phone;
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
     * Gets the C2 server of the bot
     *
     * @return the C2 server
     */
    public String getServer() {
        return server;
    }

    /**
     * Sets the C2 server of the bot to a new server, and adds the old one to
     * the list of old C2 servers. Both actions are logged in the bot's log
     * file. This function only logs the change if the newly provided value is
     * not the same as the value it already had
     *
     * @param server the new C2 address, excluding any trailing slashes
     */
    public void setServer(String server) {
        if (this.server.equalsIgnoreCase(server) == false) {
            oldServers.add(server);
            addLog("Added \"" + this.server + "\" as an old C2 server");

            this.server = server;
            addLog("Changed the C2 from \"" + this.server + "\" to \"" + server + "\"!");
        }
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
     * Gets the local file system manager, which abstracts the file logic away
     * from the bot class. The instance uses the local file system folder as its
     * base
     *
     * @return the local file system manager to interact with the local file
     * system
     */
    public LocalFileSystemManager getLocalFileSystemManager() {
        return localFileSystemManager;
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
     * Sets the registration status of the bot, and logs the change. This
     * function only logs the change if the newly provided value is not the same
     * as the value it already had
     *
     * @param status the new registration status
     */
    public void setRegistration(boolean status) {
        if (registered != status) {
            addLog("Registration status changed from \"" + this.registered + "\" to \"" + status + "\"");
            this.registered = status;
        }
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
     * Sets the boolean that defines if the bot is the default SMS manager. This
     * function only logs the change if the newly provided value is not the same
     * as the value it already had
     *
     * @param status the new status
     */
    public void setDefaultSmsManager(boolean status) {
        if (defaultSmsManager != status) {
            addLog("The bot's default SMS manager status changed from \"" + this.defaultSmsManager + "\" to \"" + status + "\"");
            this.defaultSmsManager = status;
        }
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
     * Sets the active status of the bot, and logs the change from the old value
     * to the new value via the bot's logging function. This function only logs
     * the change if the newly provided value is not the same as the value it
     * already had
     *
     * @param status the new status
     */
    public void setActive(boolean status) {
        if (active != status) {
            addLog("The bot's active status changed from \"" + this.active + "\" to \"" + status + "\"");
            this.active = status;
        }
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

    /**
     * Returns a boolean that defines if a proxy is used by this specific bot
     *
     * @return true if a proxy is used, false if not
     */
    public boolean usesProxy() {
        return proxy;
    }

    /**
     * Gets the connector object
     *
     * @return the connector object
     */
    public Connector getConnector() {
        return connector;
    }
}
