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
package emulator;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This class is used to generate Options objects for the command-line
 * interfaces. To keep the code clearly readable, this segment is placed in a
 * separate class
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class OptionsGenerator {

    /**
     * Generates the command-line options for the main command-line interface
     *
     * @return the main command-line interface options
     */
    public Options generateCli() {
        Options options = new Options();

        options.addOption(Option.builder("cm").longOpt("createManual").desc("Starts the guided creation of a bot emulation project").build());
        options.addOption(Option.builder("cc").argName("all required command line arguments").longOpt("createCli").hasArgs().desc("Creates a bot emulation project based on the given command line arguments").build());
        options.addOption(Option.builder("cch").longOpt("command line argument creation help").desc("Lists all options for the command line creation function with a brief explanation for each entry").build());

        options.addOption(Option.builder("e").argName("bot emulation path(s)").longOpt("emulate").hasArgs().desc("Loads the given bot emulation file(s) into m3 and starts the emulation of all bots via the scheduler").build());
        return options;
    }

    /**
     * Generates the command-line options for the creation of a bot via the
     * command-line interface
     *
     * @return the command-line options for the creation of a bot
     */
    public Options generateConfigCreationCli() {
        Options options = new Options();

        options.addOption(Option.builder("bf").argName("the bot's family name").longOpt("botFamily").hasArg().desc("One of the supported families within m3").build());
        options.addOption(Option.builder("bn").argName("the bot's name (local use only)").longOpt("botName").hasArg().desc("The bot's name in the logging, used locally only").build());
        options.addOption(Option.builder("t").argName("the bot's tag").longOpt("tag").hasArg().desc("The bot's tag").build());
        options.addOption(Option.builder("lfs").argName("the local file system path in full").longOpt("localFileSystem").hasArg().desc("The full path to the local file system, which is used by this bot to read and write files").build());

        options.addOption(Option.builder("i").argName("the bot's fake imei").longOpt("imei").hasArg().desc("The bot's fake IMEI").build());
        options.addOption(Option.builder("n").argName("the bot's fake number").longOpt("number").hasArg().desc("The bot's fake number").build());
        options.addOption(Option.builder("op").argName("the fake phone's network operator name").longOpt("networkOperator").hasArg().desc("The name of the network operator of the fake phone").build());
        options.addOption(Option.builder("lo").argName("the fake phone's locale").longOpt("locale").hasArg().desc("The fake phone's locale as a ISO-3166-1 alpha-2 country code").build());
        options.addOption(Option.builder("v").argName("the fake phone's Android version").longOpt("version").hasArg().desc("The fake phone's Android version, as seen in Build.VERSION.RELEASE").build());
        options.addOption(Option.builder("m").argName("the fake phone's model").longOpt("model").hasArg().desc("The model of the fake phone, as seen in Build.MODEL").build());
        options.addOption(Option.builder("p").argName("the fake phone's product").longOpt("product").hasArg().desc("The product of the fake phone, as seen in Build.PRODUCT").build());

        //SMS
        options.addOption(Option.builder("smsI").argName("recipient||text").longOpt("smsInbox").hasArgs().desc("The fake text message(s) in the inbox of the fake phone, one entry per argument, split with two pipes (being \"||\") between the recipient and the text body").build());

        options.addOption(Option.builder("smsD").argName("recipient||text").longOpt("smsDraft").hasArgs().desc("The fake text message(s) in the drafts folder of the fake phone, one entry per argument, split with two pipes (being \"||\") between the recipient and the text body").build());

        options.addOption(Option.builder("smsO").argName("recipient||text").longOpt("smsOutbox").hasArgs().desc("The fake text message(s) in the outbox of the fake phone, one entry per argument, split with two pipes (being \"||\") between the recipient and the text body").build());

        //Contacts
        options.addOption(Option.builder("c").argName("name||number").longOpt("contacts").hasArgs().desc("The fake contact(s) in the fake phone, one per argument, split with two pipes (being \"||\") between the name and number of the contact").build());

        //SP is skipped
        //Permissions
        options.addOption(Option.builder("perm").argName("the permission(s) the bot has one the fake phone").longOpt("permissions").hasArgs().desc("The permission(s) of the bot on the fake phone, use \"permissions_all\" to add all permissions to the bot").build());

        //Installed applications
        options.addOption(Option.builder("apps").argName("package name").longOpt("applications").hasArgs().desc("The installed applications on the fake phone").build());

        //C2
        options.addOption(Option.builder("c2").argName("the C2 server of the bot").longOpt("c2Server").hasArg().desc("The C2 server that the bot should connect to. Only include the IP or domain (including the protocol, excluding a trailing slash, i.e. https://127.0.0.1)").build());

        //Old servers is skipped
        //registered is skipped
        //user agent
        options.addOption(Option.builder("ua").argName("the user agent to use").longOpt("userAgent").hasArg().desc("The user agent that should be used when making HTTP connections").build());

        //rooted
        options.addOption(Option.builder("r").argName("if the fake phone is rooted").longOpt("rooted").hasArg().desc("True/t/yes/y if the fake phone is rooted, false/f/no/n if not").build());
        //default sms manager
        options.addOption(Option.builder("man").argName("if the bot is the default SMS manager on the fake phone").longOpt("defaultSms").hasArg().desc("True/t/yes/y if the bot is the default SMS manager on the fake phone, false/f/no/n if not").build());

        //default interval
        options.addOption(Option.builder("int").argName("the interval in seconds as a whole number").longOpt("interval").hasArg().desc("The interval between polling in seconds. If the C2 returns a specific polling value later on, this value is overwritten").build());

        //is device admin
        options.addOption(Option.builder("da").argName("if the bot is device admin on the fake phone").longOpt("deviceAdmin").hasArg().desc("True/t/yes/y if the bot is device admin on the fake phone, false/f/no/n if not").build());

        //is screen locked
        options.addOption(Option.builder("scrn").argName("if the fake phone's screen is locked").longOpt("lockedScreen").hasArg().desc("True/t/yes/y if the fake phone's screen is locked, false/f/no/n if not").build());

        //Battery ercentage
        options.addOption(Option.builder("bp").argName("the fake phone's battery percentage").longOpt("batteryPercentage").hasArg().desc("The battery percentage of the fake phone, between 1 and 100. Going over or under the limit will result in the maximum or minimum value respectively.").build());

        //active is skipped (= true)
        //rc4key (anubis and cerberus only)
        options.addOption(Option.builder("rc4").argName("the rc4 key").longOpt("rc4Key").hasArg().desc("The RC4 key that is used when communicating with the C2 server. Note that this is only used in Anubis and Cerberus").required(false).build());
        //id is cerb only, but auto generated

        //proxy server
        options.addOption(Option.builder("proxyA").argName("the proxy address").longOpt("proxyAddress").hasArg().desc("The proxy address, if one wishes to use a proxy server. This argument is optional for any bot!").required(false).build());
        options.addOption(Option.builder("proxyP").argName("the proxy port").longOpt("proxyPort").hasArg().desc("The proxy port, if one wishes to use a proxy server. This argument is optional for any bot!").required(false).build());

        options.addOption(Option.builder("sf").argName("the folder on the C2 server").longOpt("serverFolder").hasArg().desc("The folder where the PHP files reside on the Anubis C2 folder, excluding the domain, and without any leading or trailing slashes. In this example URL: \"127.0.0.1/abc/a11.php\", the folder name is \"abc\"").required(false).build());

        return options;
    }
}
