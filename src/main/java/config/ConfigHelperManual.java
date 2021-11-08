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

import device.Contact;
import device.SmsMessage;
import device.enums.Permissions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * This class providers helper functions when a user wants to create a
 * configuration file via the manual function
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class ConfigHelperManual {

    /**
     * The object to obtain user-input from the command-line
     */
    private Scanner scanner;

    /**
     * Initialises the helper class
     *
     * @param scanner the scanner object to get user-input from the command-line
     */
    public ConfigHelperManual(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Gets a list of strings until the user provides "m3_end" as input. Empty
     * input is not allowed.
     *
     * @param message the message that instructs the user what the input is for
     * @return a list of strings that the user provided
     */
    protected List<String> getStrings(String message) {
        List<String> strings = new ArrayList<>();
        System.out.println("[+]Provide \"m3_end\" to finalise the provided input");
        while (true) {
            String string = getString(message);
            if (string.equalsIgnoreCase("m3_end")) {
                break;
            } else {
                strings.add(string);
            }
        }
        return strings;
    }

    /**
     * Optionally gets a string, meaning that the user can skip the input. If
     * the user skips the input, an null is returned
     *
     * @param message the message that instructs the user what the input is for
     * @return the input, or null of no input is provided
     */
    protected String optString(String message) {
        System.out.println(message);
        while (true) {
            System.out.print("Your input (press enter to skip this entry): ");
            String response = scanner.nextLine();
            if (response.isEmpty()) {
                return null;
            }
            return response;
        }
    }

    /**
     * Forces the user to provide input
     *
     * @param message the message that instructs the user what the input is for
     * @return the user-provided input
     */
    protected String getString(String message) {
        System.out.println(message);
        while (true) {
            System.out.print("Your input: ");
            String response = scanner.nextLine();
            if (response.isEmpty()) {
                System.out.println("[+]An empty value is not accepted");
            } else {
                return response;
            }
        }
    }

    /**
     * Optionally gets an integer from the user. This function either returns
     * the provided integer, or null if the user skips this entry
     *
     * @param message the message that instructs the user what the input is for
     * @return the user-provided integer, or null if this entry is skipped
     */
    protected Integer optInt(String message) {
        System.out.println(message);
        while (true) {
            System.out.print("Your input (press enter to skip this entry): ");
            String response = scanner.nextLine();
            try {
                if (response.isEmpty()) {
                    return null;
                }
                Integer value = Integer.parseInt(response);
                return value;
            } catch (Exception e) {
                System.out.println("[+]Please provide an integer, or press enter to skip this entry");
            }
        }
    }

    /**
     * Forces the user to provide an integer
     *
     * @param message the message that instructs the user what the input is for
     * @return the user-provided integer
     */
    protected int getInt(String message) {
        System.out.println(message);
        while (true) {
            System.out.print("Your input: ");
            String response = scanner.nextLine();
            if (response.isEmpty()) {
                System.out.println("[+]An empty value is not accepted");
            } else {
                try {
                    Integer value = Integer.parseInt(response);
                    return value;
                } catch (Exception e) {
                    System.out.println("[+]Please provide an integer");
                }
            }
        }
    }

    /**
     * Forces the user to get a boolean from the user. To return true, the user
     * needs to provide "true", "t", "yes", or "y" as input. To return false,
     * the user needs to provide "false", "f", "no", or "n"
     *
     * @param message the message that instructs the user what the input is for
     * @return the yes or no answer from the user
     */
    protected boolean getBoolean(String message) {
        System.out.println(message);
        while (true) {
            System.out.print("Your input (true/t/yes/y for true, false/f/no/n for false): ");
            String response = scanner.nextLine();
            if (response.isEmpty()) {
                System.out.println("[+]An empty value is not accepted");
            } else {
                if (response.equalsIgnoreCase("true") || response.equalsIgnoreCase("t") || response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                    return true;
                } else if (response.equalsIgnoreCase("false") || response.equalsIgnoreCase("f") || response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
                    return false;
                } else {
                    System.out.println("[+]Please provide one of the requested values");
                }
            }
        }
    }

    /**
     * Gets a list of installed applications from the user, until "m3_end" is
     * received as input
     *
     * @param message the message that instructs the user what the input is for
     * @return the list of installed package names
     */
    protected Set<String> getInstalledApplications(String message) {
        System.out.println(message);
        System.out.println("[+]Provide \"m3_end\" as the installed application package name to finalise the provided input");
        Set<String> installedApplications = new HashSet<>();
        while (true) {
            System.out.print("Installed application's package name: ");
            String permission = scanner.nextLine();
            if (permission.equalsIgnoreCase("m3_end")) {
                break;
            } else {
                installedApplications.add(permission);
            }
        }
        return installedApplications;
    }

    /**
     * Obtains a list of permissions from the user
     *
     * @param message the message that instructs the user what the input is for
     * @return the user-provided permissions
     */
    protected Set<String> getPermissions(String message) {
        System.out.println(message);
        System.out.println("[+]Enter \"permissions_all\" to add all permissions at once");
        System.out.println("[+]Provide \"m3_end\" as the permission to finalise the provided input");
        Set<String> permissions = new HashSet<>();
        while (true) {
            System.out.print("Permission: ");
            String permission = scanner.nextLine();
            if (permission.equalsIgnoreCase("m3_end")) {
                break;
            } else if (permission.equalsIgnoreCase("permissions_all")) {
                permissions.addAll(Permissions.getAll());
                break;
            } else {
                permissions.add(permission);
            }
        }
        return permissions;
    }

    /**
     * Gets a list of contacts based on the user's input
     *
     * @param message the message that instructs the user what the input is for
     * @return a list of fake contacts by the user
     */
    protected List<Contact> getContacts(String message) {
        System.out.println(message);
        System.out.println("[+]Provide \"m3_end\" as the fake contact's name to finalise the provided input");
        List<Contact> contacts = new ArrayList<>();
        while (true) {
            System.out.print("Fake contact's name (needs to be fake): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("m3_end")) {
                break;
            } else {
                System.out.print("Fake contact's phone number that belongs to the previously given fake contact's name (needs to be under your control): ");
                String number = scanner.nextLine();
                if (number.equalsIgnoreCase("m3_end")) {
                    break;
                }
                contacts.add(new Contact(name, number));
            }
        }
        return contacts;
    }

    /**
     * Gets a list of fake SMS messages from the user, based on the user's input
     *
     * @param message the message that instructs the user what the input is for
     * @return a list of fake SMS messages
     */
    protected List<SmsMessage> getSmsMessages(String message) {
        System.out.println(message);
        System.out.println("[+]Provide \"m3_end\" as the fake recipient's phone number to finalise the provided input");
        List<SmsMessage> messages = new ArrayList<>();
        while (true) {
            System.out.print("Fake recipient's phone number (needs to be under your control): ");
            String number = scanner.nextLine();
            if (number.equalsIgnoreCase("m3_end")) {
                break;
            } else {
                System.out.print("Fake message that belongs to the previously given fake number: ");
                String text = scanner.nextLine();
                if (text.equalsIgnoreCase("m3_end")) {
                    break;
                }
                messages.add(new SmsMessage(number, text));
            }
        }
        return messages;
    }
}
