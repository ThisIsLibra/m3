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

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all families that are supported within m3
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class Families {

    /**
     * gets all supported families in a list
     *
     * @return a list of all supported families
     */
    public static List<String> getAllFamilies() {
        List<String> families = new ArrayList<>();
        families.add(ANUBIS);
        families.add(CERBERUS);
        return families;
    }

    /**
     * The name for the Anubis family
     */
    public static final String ANUBIS = "ANUBIS";

    /**
     * The name of the Cerberus family
     */
    public static final String CERBERUS = "CERBERUS";
}
