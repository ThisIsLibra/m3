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
package config;

import anubis.bot.AnubisConfig;
import bot.Families;
import bot.IConfig;
import bot.LocalFileSystemManager;
import cerberus.bot.CerberusConfig;
import com.google.gson.Gson;
import device.Contact;
import device.SharedPreferences;
import device.SmsManager;
import device.SmsMessage;
import device.enums.Permissions;
import emulator.OptionsGenerator;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * This class allows the user to create configuration files for bots in an easy
 * manner.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class ConfigCreator {

    /**
     * In the manual creation method, the user provides all input for all fields
     * via the standard input method. Guidance as to what is what is given for
     * each step along the way
     */
    public void manualCreation() {
        Scanner scanner = new Scanner(System.in);
        ConfigHelperManual manualHelper = new ConfigHelperManual(scanner);

        System.out.println("[+]Manual bot configuration creation mode has been selected");
        System.out.println("[+]Please select the family for which the configuration should be created:");

        List<String> families = Families.getAllFamilies();
        for (int i = 0; i < families.size(); i++) {
            System.out.println("\t" + i + ") " + families.get(i));
        }

        String botFamily = "";
        while (true) {
            Integer index = manualHelper.getInt("[+]Enter the number of one of the supported families in the above list to proceed");
            if (index >= 0 && index < families.size()) {
                botFamily = families.get(index);
                break;
            } else {
                System.out.println("[+]The provided index does not match any of the supported families, please try again!");
            }
        }

        String botName = manualHelper.getString("[+]Please provide a name for this bot, this name is only used in local emulation logging, and never shared with any C2");

        String tag = manualHelper.getString("[+]Please provide the bot's tag");

        String localFileSystem = manualHelper.getString("[+]Please provide the full path to the local file system's folder");

        String imei = manualHelper.getString("[+]Please provide the fake phone's IMEI");

        String number = manualHelper.getString("[+]Please provide the fake phone's number");

        String networkOperatorName = manualHelper.getString("[+]Please provide the fake phone's network operator name");

        String locale = manualHelper.getString("[+]Please provide the fake phone's locale, which is a two letter country code (as defined in ISO-3166-1 alpha-2)");

        //Build.VERSION.RELEASE
        String version = manualHelper.getString("[+]Please provide the fake phone's version (as defined in Android's Build.VERSION.RELEASE)");

        //Build.MODEL
        String model = manualHelper.getString("[+]Please provide the fake phone's model (as defined in Android's Build.MODEL)");

        //Build.PRODUCT
        String product = manualHelper.getString("[+]Please provide the fake phone's product (as defined in Android's Build.PRODUCT)");

        List<SmsMessage> inbox = manualHelper.getSmsMessages("[+]Please provide fake text messages for the fake phone's inbox (both the recipient and the text message itself are required, and both should be fake)");

        List<SmsMessage> drafts = manualHelper.getSmsMessages("[+]Please provide fake text messages for the fake phone's drafts folder (both the recipient and the text message itself are required, and both should be fake)");

        List<SmsMessage> outbox = manualHelper.getSmsMessages("[+]Please provide fake text messages for the fake phone's outbox (both the recipient and the text message itself are required, and both should be fake)");

        SmsManager smsManager = new SmsManager(inbox, drafts, outbox);

        List<Contact> contacts = manualHelper.getContacts("[+]Please provide fake contacts for the fake phone, where both the name and the number is fake");

        SharedPreferences sharedPreferences = new SharedPreferences();

        Set<String> permissions = manualHelper.getPermissions("[+]Please provide the permissions one by one, as defined in the bot's manifest");

        Set<String> installedApplications = manualHelper.getInstalledApplications("[+]Please provide a list of all installed applications based on package names");

        String server = manualHelper.getString("[+]Please provide the C2 address (excluding anything but the domain or IP, but including the HTTP protocol, i.e. https://127.0.0.1)");

        List<String> oldServers = new ArrayList<>();

        boolean registered = false;

        String userAgent = manualHelper.getString("[+]Please provide the user-agent that should be used in HTTP connections");

        boolean rooted = manualHelper.getBoolean("[+]Please provide if the fake phone is rooted");

        boolean defaultSmsManager = manualHelper.getBoolean("[+]Please provide if the bot is the default SMS manager on the fake phone");

        int interval = manualHelper.getInt("[+]Please provide the interval (in whole seconds) at which the bot should connect by default (note that most bots have this as a server side variable, which replaces this value if it is received)");

        boolean deviceAdmin = manualHelper.getBoolean("[+]Please provide if the bot is admin on the fake phone");

        boolean locked = manualHelper.getBoolean("[+]Please provide if the fake phone is locked initially (meaning the screen is turned off)");

        int batteryPercentage = manualHelper.getInt("[+]Please provide the initial value for the fake phone's battery (between 1 and 100, going below or above these limits will result in the maximum or minimum value respecitvely)");

        if (batteryPercentage < 0) {
            batteryPercentage = 1;
        } else if (batteryPercentage > 100) {
            batteryPercentage = 100;
        }

        boolean active = true;

        String proxyAddress = manualHelper.optString("[+]Please provide the proxy server's address, if want to use any");

        Integer proxyPort = null;

        if (proxyAddress != null) {
            proxyPort = manualHelper.getInt("[+]Please provide the proxy server's port, if you want to use any");
        }

        String rc4Key = "";
        if (botFamily.equalsIgnoreCase(Families.ANUBIS) || botFamily.equalsIgnoreCase(Families.CERBERUS)) {
            rc4Key = manualHelper.getString("[+]Please provide the RC4 key that is used when communicating with this server");
        }

        String serverFolder = "";
        if (botFamily.equalsIgnoreCase(Families.ANUBIS)) {
            serverFolder = manualHelper.getString("[+]Please provide the folder where the PHP reside on the server (this is usually a three letter name, but it can be longer or shorter). Note that no leading nor trailing slashes need to be added!");
        }

        IConfig config = null;

        if (botFamily.equalsIgnoreCase(Families.ANUBIS)) {
            config = new AnubisConfig(LocalDateTime.now().minusYears(1), botName, botFamily, tag, localFileSystem, imei, number, networkOperatorName, locale, version, model, product, smsManager, contacts, sharedPreferences, permissions, installedApplications, server, oldServers, registered, userAgent, interval, rooted, defaultSmsManager, deviceAdmin, locked, batteryPercentage, active, proxyAddress, proxyPort, rc4Key, serverFolder);
        } else if (botFamily.equalsIgnoreCase(Families.CERBERUS)) {

            String id = "";
            if (botFamily.equalsIgnoreCase(Families.CERBERUS)) {
                //The ID is a 17 character long random string based on the given alphabet
                String alphabet = "qwertyuiopasdfghjklzxcvbnm1234567890";
                Random random = new Random();
                StringBuilder buffer = new StringBuilder();
                for (int i = 0; i < 17; i++) {
                    buffer.append(alphabet.charAt(random.nextInt(alphabet.length())));
                }
                id = buffer.toString();
            }

            config = new CerberusConfig(LocalDateTime.now().minusYears(1), botName, botFamily, tag, localFileSystem, imei, number, networkOperatorName, locale, version, model, product, smsManager, contacts, sharedPreferences, permissions, installedApplications, server, oldServers, registered, userAgent, interval, rooted, defaultSmsManager, deviceAdmin, locked, batteryPercentage, active, proxyAddress, proxyPort, rc4Key, id);
        }

        if (config != null) {
            try {
                saveConfig(config);
            } catch (IOException ex) {
                System.out.println("ERROR:\n" + ex.getMessage());
            }
        } else {
            System.out.println("[+]An error has occured, as not suitable family has been found. Ensure the family is correctly implemented within m3!");
        }
    }

    /**
     * The user can create a config file via the command-line interface. This
     * function verifies if all required arguments are present, after which the
     * cofiguration file is created, and written to the disk
     *
     * @param args the command-line arguments to create a bot
     */
    public void cliCreation(String[] args) {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        OptionsGenerator optionsGenerator = new OptionsGenerator();

        Options options = optionsGenerator.generateConfigCreationCli();
        ConfigHelperCli cliHelper = new ConfigHelperCli();

        try {
            CommandLine cli = parser.parse(options, args);

            List<String> missingArguments = cliHelper.containsAllArguments(cli);
            if (missingArguments.size() > 0) {
                String missing = "\nThe following arguments are missing:\n";
                for (int i = 0; i < missingArguments.size(); i++) {
                    missing += "-" + missingArguments.get(i);
                    if (i != missingArguments.size() - 1) {
                        missing += "\n";
                    }
                }
                formatter.printHelp(150, "m3 -cc [required options for the requested bot family]", "", options, missing);
                return;
            }

            String botFamily = cli.getOptionValue("bf");
            String botName = cli.getOptionValue("bn");
            String tag = cli.getOptionValue("t");
            String localFileSystem = cli.getOptionValue("lfs");

            String imei = cli.getOptionValue("i");
            String number = cli.getOptionValue("n");
            String networkOperatorName = cli.getOptionValue("op");
            String locale = cli.getOptionValue("lo");
            String version = cli.getOptionValue("v");
            String model = cli.getOptionValue("m");
            String product = cli.getOptionValue("p");

            List<SmsMessage> inbox = new ArrayList<>();
            for (String raw : cli.getOptionValues("smsI")) {
                try {
                    String[] parsed = raw.split("||");
                    String recpient = parsed[0];
                    String text = parsed[1];
                    inbox.add(new SmsMessage(recpient, text));
                } catch (Exception e) {
                    System.out.println("[+]EROR: failed to parse the following inbox text message: \"" + raw + "\"");
                    return;
                }
            }

            List<SmsMessage> drafts = new ArrayList<>();
            for (String raw : cli.getOptionValues("smsD")) {
                try {
                    String[] parsed = raw.split("||");
                    String recpient = parsed[0];
                    String text = parsed[1];
                    drafts.add(new SmsMessage(recpient, text));
                } catch (Exception e) {
                    System.out.println("[+]EROR: failed to parse the following draft text message: \"" + raw + "\"");
                    return;
                }
            }

            List<SmsMessage> outbox = new ArrayList<>();
            for (String raw : cli.getOptionValues("smsO")) {
                try {
                    String[] parsed = raw.split("||");
                    String recpient = parsed[0];
                    String text = parsed[1];
                    outbox.add(new SmsMessage(recpient, text));
                } catch (Exception e) {
                    System.out.println("[+]EROR: failed to parse the following outbox text message: \"" + raw + "\"");
                    return;
                }
            }

            SmsManager smsManager = new SmsManager(inbox, drafts, outbox);

            List<Contact> contacts = new ArrayList<>();
            for (String raw : cli.getOptionValues("c")) {
                try {
                    String[] parsed = raw.split("||");
                    String name = parsed[0];
                    String contactNumber = parsed[1];
                    contacts.add(new Contact(name, contactNumber));
                } catch (Exception e) {
                    System.out.println("[+]EROR: failed to parse the following contact: \"" + raw + "\"");
                    return;
                }
            }

            SharedPreferences sharedPreferences = new SharedPreferences();

            Set<String> permissions = new HashSet<>();
            for (String permission : cli.getOptionValues("perm")) {
                if (permission.equalsIgnoreCase("permissions_all")) {
                    permissions = new HashSet<>();
                    permissions.addAll(Permissions.getAll());
                    break;
                } else {
                    permissions.add(permission);
                }
            }

            Set<String> installedApplications = new HashSet<>();
            for (String application : cli.getOptionValues("apps")) {
                installedApplications.add(application);
            }

            String server = cli.getOptionValue("c2");

            List<String> oldServers = new ArrayList<>();

            boolean registered = false;

            String userAgent = cli.getOptionValue("ua");

            boolean rooted = false;
            try {
                rooted = cliHelper.getBooleanFromCli(cli.getOptionValue("r"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            boolean defaultSmsManager = false;
            try {
                defaultSmsManager = cliHelper.getBooleanFromCli(cli.getOptionValue("man"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            int interval = -1;
            try {
                interval = Integer.parseInt(cli.getOptionValue("int"));
            } catch (Exception e) {
                System.out.println("[+]ERROR: the provided interval value (\"" + cli.getOptionValue("int") + "\") for \"-int\" is not an integer");
                return;
            }

            boolean deviceAdmin = false;
            try {
                deviceAdmin = cliHelper.getBooleanFromCli(cli.getOptionValue("da"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            boolean locked = false;
            try {
                locked = cliHelper.getBooleanFromCli(cli.getOptionValue("scrn"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            int batteryPercentage = -1;
            try {
                batteryPercentage = Integer.parseInt(cli.getOptionValue("bp"));

                if (batteryPercentage < 0) {
                    batteryPercentage = 1;
                } else if (batteryPercentage > 100) {
                    batteryPercentage = 100;
                }

            } catch (Exception e) {
                System.out.println("[+]ERROR: the provided interval value (\"" + cli.getOptionValue("int") + "\") for \"-bp\" is not an integer");
                return;
            }

            boolean active = true;

            String proxyAddress = null;
            if (cli.hasOption("proxyA") && cli.hasOption("proxyP")) {
                proxyAddress = cli.getOptionValue("proxyA");
            }

            Integer proxyPort = null;
            if (cli.hasOption("proxyA") && cli.hasOption("proxyP")) {
                try {
                    proxyPort = Integer.parseInt(cli.getOptionValue("proxyP"));
                } catch (Exception e) {
                    System.out.println("[+]ERROR: the provided proxy port value (\"" + cli.getOptionValue("proxyP") + "\") for \"-proxyP\" is not an integer");
                    return;
                }
            }

            String rc4Key = "";
            if (botFamily.equalsIgnoreCase(Families.ANUBIS) || botFamily.equalsIgnoreCase(Families.CERBERUS)) {
                rc4Key = cli.getOptionValue("rc4");
            }

            String serverFolder = "";
            if (botFamily.equalsIgnoreCase(Families.ANUBIS)) {
                serverFolder = cli.getOptionValue("sf");
            }

            IConfig config = null;

            if (botFamily.equalsIgnoreCase(Families.ANUBIS)) {
                config = new AnubisConfig(LocalDateTime.now().minusYears(1), botName, botFamily, tag, localFileSystem, imei, number, networkOperatorName, locale, version, model, product, smsManager, contacts, sharedPreferences, permissions, installedApplications, server, oldServers, registered, userAgent, interval, rooted, defaultSmsManager, deviceAdmin, locked, batteryPercentage, active, proxyAddress, proxyPort, rc4Key, serverFolder);
            } else if (botFamily.equalsIgnoreCase(Families.CERBERUS)) {

                String id = "";
                if (botFamily.equalsIgnoreCase(Families.CERBERUS)) {
                    //The ID is a 17 character long random string based on the given alphabet
                    String alphabet = "qwertyuiopasdfghjklzxcvbnm1234567890";
                    Random random = new Random();
                    StringBuilder buffer = new StringBuilder();
                    for (int i = 0; i < 17; i++) {
                        buffer.append(alphabet.charAt(random.nextInt(alphabet.length())));
                    }
                    id = buffer.toString();
                }

                config = new CerberusConfig(LocalDateTime.now().minusYears(1), botName, botFamily, tag, localFileSystem, imei, number, networkOperatorName, locale, version, model, product, smsManager, contacts, sharedPreferences, permissions, installedApplications, server, oldServers, registered, userAgent, interval, rooted, defaultSmsManager, deviceAdmin, locked, batteryPercentage, active, proxyAddress, proxyPort, rc4Key, id);
            }

            if (config != null) {
                System.out.println("[+]The configuration object was initiliased succesfully! Writing the output to the disk now!");
                saveConfig(config);
            } else {
                System.out.println("[+]An error has occured, as not suitable family has been found. Ensure the family is correctly implemented within m3!");
            }
        } catch (ParseException ex) {
            formatter.printHelp(150, "m3 -cc [required options for the requested bot family]", "", options, ex.getMessage());
        } catch (IOException ex) {
            System.out.println("[+]ERROR:\n" + ex.getMessage());
        }

    }

    /**
     * Saves the given configuration file as "config.json" to the given local
     * file system location, creating all folders that are missing along the way
     *
     * @param config the configuration file to save
     * @throws IOException if there is an error writing the files
     */
    private void saveConfig(IConfig config) throws IOException {
        Gson gson = new Gson();

        //Check if the full path should use backslashes (on Windows) or forward slashes (MacOS and Linux)
        String slash = "";
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            slash = "\\";
        } else {
            slash = "/";
        }

        String path = "";
        if (config.getLocalFileSystem().endsWith("\\") || config.getLocalFileSystem().endsWith("/")) {
            path = config.getLocalFileSystem() + "config.json";
        } else {
            path = config.getLocalFileSystem() + slash + "config.json";
        }

        //Create all the required folders
        new File(config.getLocalFileSystem()).mkdirs();
        System.out.println("[+]All folders for \"" + config.getLocalFileSystem() + "\" have been created");

        LocalFileSystemManager manager = new LocalFileSystemManager(config.getLocalFileSystem());
        if (manager.exists("config.json")) {
            System.out.println("[+]The file \"" + path + "\" already exists.");
            String oldConfig = manager.readStringFromPath("config.json");
            LocalDateTime instance = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("__yyyy-MM-dd_HH-mm-ss");
            String appendix = formatter.format(instance);
            manager.overwriteFile(oldConfig.getBytes(), "config.json" + appendix);
            System.out.println("[+]Saved the old existing file as \"" + path + appendix + "\"");
        }
        manager.overwriteFile(gson.toJson(config).getBytes(), "config.json");

        System.out.println("[+]The configuration file has been saved at \"" + path + "\"");

    }
}
