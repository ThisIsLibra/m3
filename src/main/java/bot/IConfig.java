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

/**
 * The interface to use for all configuration objects. Each object should also
 * extend the abstract Config class, as that contains the core fields for each
 * configuration file.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public interface IConfig {

    /**
     * The bot family, as defined in bot.Families
     *
     * @return the bot's family
     */
    public String getBotFamily();

    /**
     * The full path to the local file system folder
     *
     * @return the full path to the local file system folder
     */
    public String getLocalFileSystem();
}
