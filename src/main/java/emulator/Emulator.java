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
package emulator;

import config.ConfigCreator;
import scheduler.BotScheduler;
import bot.IConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import config.ConfigLoader;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * This class contains the entry point of the m3 framework. Its purpose is to
 * handle the command-line input and select the correct module. A module might
 * have an additional command-line interface, which is left up to the
 * implementation of said module.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class Emulator {

    /**
     * The main class requires some command-line interface arguments to work. If
     * no recognised options are present, the help menu will be shown
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Prints the version information
        System.out.println("[+]Mobile Malware Mimicking (m3) version 1.0-stable by Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]");
        System.out.println("\tBy using this framework you acknowledge that you are only providing details to bots that you fully own!");

        //Initialise the required Apache objects
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        //Initialise the required m3 objects
        ConfigCreator configCreator = new ConfigCreator();
        BotScheduler scheduler = new BotScheduler();
        ConfigLoader configLoader = new ConfigLoader();
        OptionsGenerator optionsGenerator = new OptionsGenerator();

        //Obtain the options from the options generator
        Options options = optionsGenerator.generateCli();
        
        try {
            CommandLine cli = parser.parse(options, args, true);

            if (cli.hasOption("e")) {
                List<String> paths = new ArrayList<>();
                paths.addAll(Arrays.asList(cli.getOptionValues("e")));
                System.out.println("[+]Received the command to start emulating " + paths.size() + " bots, loading configuration files now");
                List<IConfig> configs = configLoader.loadConfigs(paths);

                if (configs.size() > 0) {
                    System.out.println("[+]Loaded " + configs.size() + " bots");
                    System.out.println("[+]Starting the scheduler");
                    scheduler.schedule(configs);
                } else {
                    System.out.println("[+]No bots were found, check if all arguments are correct and try again");
                }
            } else if (cli.hasOption("cc")) {
                configCreator.cliCreation(Arrays.copyOfRange(args, 1, args.length));
            } else if (cli.hasOption("cch")) {
                formatter.printHelp(150, "m3 -cc [required options for the requested bot family]", "", optionsGenerator.generateConfigCreationCli(), "");
            } else if (cli.hasOption("cm")) {
                configCreator.manualCreation();
            } else {
                formatter.printHelp(150, "m3 [mode] [options if applicable]", "Note that only one of the listed options can be executed at once!", options, "");
            }
        } catch (ParseException ex) {
            formatter.printHelp(150, "m3 [mode] [options if applicable]", "Note that only one of the listed options can be executed at once!", options, "\n" + ex.getMessage());
        }
    }
}
