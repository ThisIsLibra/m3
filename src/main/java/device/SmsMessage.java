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
 * This class contains two fields for a text message: the recipient, and the
 * actual message body.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class SmsMessage {

    /**
     * The recipient of the text message
     */
    private String recipient;

    /**
     * The text message of the body
     */
    private String text;

    /**
     * Creates a SmsMessage object with a given recipient and a given text body
     *
     * @param recipient the recipient of this SMS message
     * @param text the text body of this SMS message
     */
    public SmsMessage(String recipient, String text) {
        this.recipient = recipient;
        this.text = text;
    }

    /**
     * Gets the recipient of this SMS message
     *
     * @return the recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Gets the text body of this SMS message
     *
     * @return the body
     */
    public String getText() {
        return text;
    }

}
