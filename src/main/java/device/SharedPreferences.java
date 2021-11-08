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

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains the shared preferences, as can be used on Android phones.
 * The shared preferences are based upon a HashMap, for which several helper
 * functions are available in this class.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class SharedPreferences {

    /**
     * The mapping that stores all shared preferences
     */
    private Map<String, String> preferences;

    /**
     * This class contains the shared preferences, as can be used on Android
     * phones. The shared preferences are based upon a HashMap, for which
     * several helper functions are available in this class.
     */
    public SharedPreferences() {
        preferences = new HashMap<>();
    }

    /**
     * Get the complete mapping
     *
     * @return the complete mapping
     */
    public Map<String, String> getPreferences() {
        return preferences;
    }

    /**
     * Overwrite all existing preferences in the mapping
     *
     * @param preferences the new mapping
     */
    public void setPreferences(Map<String, String> preferences) {
        this.preferences = preferences;
    }

    /**
     * Read a specific key from the mapping. If there is no such key, null is
     * returned.
     *
     * @param key the key to read the value for
     * @return the value that is present in the mapping for the given key, or
     * null if it cannot be found
     */
    public String read(String key) {
        return preferences.get(key);
    }

    /**
     * Similar to the read function, this function reads the value from a given
     * key. If the key is not present, the default value is returned, rather
     * than null.
     *
     * @param key the key to obtain the value for
     * @param defaultValue the value to return in case the key is not present
     * @return the value of the key, or the default value if there is no such
     * key
     */
    public String readOrDefault(String key, String defaultValue) {
        return preferences.getOrDefault(key, defaultValue);
    }

    /**
     * Writes the given value at the given key, overwriting any value that might
     * have been there already
     *
     * @param key the key to write
     * @param value the value to write
     */
    public void write(String key, String value) {
        preferences.put(key, value);
    }

    /**
     * Removes the key and its associated value from the mapping, if it exists
     *
     * @param key the key to remove
     */
    public void remove(String key) {
        preferences.remove(key);
    }

    /**
     * Appends a given value to the given key. If no such key is present, it is
     * created with the given value
     *
     * @param key the key to work with
     * @param value the value to append
     */
    public void append(String key, String value) {
        String oldValue = preferences.get(key);
        if (oldValue == null) {
            oldValue = "";
        }
        String newValue = oldValue + value;
        preferences.put(key, newValue);
    }

}
