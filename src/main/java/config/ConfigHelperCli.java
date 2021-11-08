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

import bot.Families;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;

/**
 * This class containers helper functions that are used when a user attempts to
 * create a config file using the command-line interface
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class ConfigHelperCli {

    /**
     * This function contains all required arguments
     *
     * @return a list of all arguments in shorthand form
     */
    private List<String> getAllArguments() {
        List<String> arguments = new ArrayList<>();
        arguments.add("bf");
        arguments.add("bn");
        arguments.add("t");
        arguments.add("lfs");

        arguments.add("i");
        arguments.add("n");
        arguments.add("op");
        arguments.add("lo");
        arguments.add("v");
        arguments.add("m");
        arguments.add("p");
        arguments.add("smsI");
        arguments.add("smsD");
        arguments.add("smsO");
        arguments.add("c");
        arguments.add("perm");
        arguments.add("apps");
        arguments.add("c2");
        arguments.add("ua");
        arguments.add("r");
        arguments.add("man");
        arguments.add("int");
        arguments.add("da");
        arguments.add("scrn");
        arguments.add("bp");
        return arguments;
    }

    /**
     * This function checks if the given Apache CommandLine object contains all
     * required arguments
     *
     * @param cli the Apache CommandLine object that contains all received
     * command-line arguments
     * @return a list of missing arguments in shorthand form
     */
    protected List<String> containsAllArguments(CommandLine cli) {
        List<String> arguments = getAllArguments();

        //Add family specific arguments
        if (cli.hasOption("bf")) {
            String botFamily = cli.getOptionValue("bf");
            if (botFamily.equalsIgnoreCase(Families.ANUBIS) || botFamily.equalsIgnoreCase(Families.CERBERUS)) {
                arguments.add("rc4");
            }
            if (botFamily.equalsIgnoreCase(Families.ANUBIS)) {
                arguments.add("sf");
            }
        }

        List<String> missingArguments = new ArrayList<>();

        for (String argument : arguments) {
            if (cli.hasOption(argument) == false) {
                missingArguments.add(argument);
            }
        }
        return missingArguments;
    }

    /**
     * This function is used to obtain a boolean based on specific user-input.
     * To return true, this function requires "true", "t", "yes", or "y" as
     * input. To return false, this function requires "false", "f", "no", or
     * "n". If none of these options can be found, an exception is thrown with
     * an error message.
     *
     * @param input the input to check
     * @return to return true, this function requires "true", "t", "yes", or "y"
     * as input. To return false, this function requires "false", "f", "no", or
     * "n"
     * @throws IOException if none of these options can be found, an exception
     * is thrown with an error message.
     */
    protected boolean getBooleanFromCli(String input) throws IOException {
        if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("t") || input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
            return true;
        } else if (input.equalsIgnoreCase("false") || input.equalsIgnoreCase("f") || input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
            return false;
        } else {
            throw new IOException("[+]ERROR: the given value (\"" + input + "\") is not any of the following: \"true\", \"t\", \"yes\", \"y\", \"false\", \"f\", \"no\", or \"n\"");
        }
    }
}
