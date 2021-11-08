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

/**
 * This class is used to store a fake contact, which is used in the fake phone.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class Contact {

    /**
     * The contact's name
     */
    private String name;

    /**
     * The contact's number
     */
    private String number;

    /**
     * This class is used to store a fake contact, which is used in the fake
     * phone.
     *
     * @param name the contact's name
     * @param number the contact's number
     */
    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    /**
     * Gets the contact's name
     *
     * @return the name of the contact
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of the contact
     *
     * @return the contact's number
     */
    public String getNumber() {
        return number;
    }
}
