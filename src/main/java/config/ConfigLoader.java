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

import anubis.bot.AnubisBot;
import anubis.bot.AnubisConfig;
import bot.Families;
import bot.IBot;
import bot.IConfig;
import cerberus.bot.CerberusBot;
import cerberus.bot.CerberusConfig;
import com.google.gson.Gson;
import device.Phone;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 * This class is used to load configuration files from files on the disk, and to
 * convert configuration files into bot objects.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class ConfigLoader {

    /**
     * Creates a bot for a given configuration object for any of the implemented
     * families. If the family name is not found, null is returned
     *
     * @param config the configuration file to make a bot from
     * @return the bot, based on the given config file, or null if there is no
     * such family
     */
    public IBot loadBot(IConfig config) {
        String family = config.getBotFamily();
        if (family.equalsIgnoreCase(Families.ANUBIS)) {
            AnubisConfig anubisConfig = (AnubisConfig) config;
            Phone phone = new Phone(anubisConfig.getImei(), anubisConfig.getNumber(), anubisConfig.getNetworkOperatorName(), anubisConfig.getLocale(), anubisConfig.getVersion(), anubisConfig.getModel(), anubisConfig.getProduct(), anubisConfig.getSmsManager(), anubisConfig.getContacts(), anubisConfig.getSharedPreferences(), anubisConfig.getPermissions(), anubisConfig.getInstalledApplications(), anubisConfig.getUserAgent(), anubisConfig.isRooted(), anubisConfig.isDeviceAdmin(), anubisConfig.isLocked(), anubisConfig.getBatteryPercentage());
            return new AnubisBot(LocalDateTime.now(), anubisConfig.getBotName(), anubisConfig.getBotFamily(), anubisConfig.getTag(), anubisConfig.getLocalFileSystem(), phone, anubisConfig.getServer(), anubisConfig.getOldServers(), anubisConfig.isRegistered(), anubisConfig.isDefaultSmsManager(), anubisConfig.getInterval(), anubisConfig.isActive(), anubisConfig.getProxyAddress(), anubisConfig.getProxyPort(), anubisConfig.getRc4Key(), anubisConfig.getServerFolder());
        } else if (family.equalsIgnoreCase(Families.CERBERUS)) {
            CerberusConfig cerberusConfig = (CerberusConfig) config;
            Phone phone = new Phone(cerberusConfig.getImei(), cerberusConfig.getNumber(), cerberusConfig.getNetworkOperatorName(), cerberusConfig.getLocale(), cerberusConfig.getVersion(), cerberusConfig.getModel(), cerberusConfig.getProduct(), cerberusConfig.getSmsManager(), cerberusConfig.getContacts(), cerberusConfig.getSharedPreferences(), cerberusConfig.getPermissions(), cerberusConfig.getInstalledApplications(), cerberusConfig.getUserAgent(), cerberusConfig.isRooted(), cerberusConfig.isDeviceAdmin(), cerberusConfig.isLocked(), cerberusConfig.getBatteryPercentage());
            return new CerberusBot(LocalDateTime.now(), cerberusConfig.getBotName(), cerberusConfig.getBotFamily(), cerberusConfig.getTag(), cerberusConfig.getLocalFileSystem(), phone, cerberusConfig.getServer(), cerberusConfig.getOldServers(), cerberusConfig.isRegistered(), cerberusConfig.isDefaultSmsManager(), cerberusConfig.getInterval(), cerberusConfig.isActive(), cerberusConfig.getProxyAddress(), cerberusConfig.getProxyPort(), cerberusConfig.getRc4Key(), cerberusConfig.getId());
        }
        System.out.println("[+]Failed to load a configuration file, details are given below:");
        System.out.println("\tUnknown family name: " + family);
        System.out.println("\tUsing the following local file system: " + config.getLocalFileSystem());
        return null;
    }

    /**
     * Creates bots for the given configuration objects for any of the
     * implemented families. If the family name is not found, null is returned
     *
     * @param configs the list of configuration files to make bots from
     * @return the bots, based on the given config files. Bots that cannot be
     * loaded are not included in this list. A message to the standard output
     * provides clarity for any of the configuration files that could not be
     * converted into a bot.
     */
    public List<IBot> loadBots(List<IConfig> configs) {
        List<IBot> bots = new ArrayList<>();

        for (IConfig config : configs) {
            IBot bot = loadBot(config);
            if (bot != null) {
                bots.add(bot);
            } else {
                System.out.println("[+]The configuration file that uses \"" + config.getLocalFileSystem() + "\" as a local file system folder cannot be loaded, as the family name (which equals \"" + config.getBotFamily() + "\") cannot be found in the list of implemented bots");
            }
        }

        return bots;
    }

    /**
     * Loads all the given configuration files from the given paths, and creates
     * configuration files from the data in each file
     *
     * @param paths the paths to load the configuration files from
     * @return a list of configuration files that could be loaded
     */
    public List<IConfig> loadConfigs(List<String> paths) {
        List<IConfig> configs = new ArrayList<>();

        for (String path : paths) {
            try {
                File file = new File(path);
                if (file.exists() == false) {
                    System.out.println("[+]ERROR: the given path (\"" + path + "\") does not exist, meaning it is ignored");
                    continue;
                }

                if (file.isDirectory()) {
                    for (File listFile : file.listFiles()) {
                        if (listFile.getName().endsWith("config.json") && listFile.isFile()) {
                            file = listFile;
                        }
                    }
                }

                String jsonRaw = new String(Files.readAllBytes(file.toPath()));
                JSONObject json = new JSONObject(jsonRaw);

                Gson gson = new Gson();
                String family = json.optString("botFamily", "");

                if (family.equalsIgnoreCase(Families.ANUBIS)) {
                    configs.add(gson.fromJson(json.toString(), AnubisConfig.class
                    ));

                } else if (family.equalsIgnoreCase(Families.CERBERUS)) {
                    configs.add(gson.fromJson(json.toString(), CerberusConfig.class
                    ));
                } else {
                    System.out.println("[+]No family can be found for \"" + family + "\", located in \"" + path + "\"");
                }
            } catch (IOException ex) {
                System.out.println("[+]ERROR: " + ex.getMessage());
                return new ArrayList<>();
            }

        }
        return configs;
    }
}
