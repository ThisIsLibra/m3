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
package bot.cryptography;

import org.apache.commons.codec.binary.Base64;

/**
 * This class is used to help with more common functions that are using in the
 * encryption and decryption process
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public abstract class GenericCryptographyHandler {

    /**
     * The base64 decoder
     */
    private Base64 base64;

    /**
     * This class is used to help with more common functions that are using in
     * the encryption and decryption process
     */
    public GenericCryptographyHandler() {
        base64 = new Base64();
    }

    /**
     * Gets the base64 decoder
     *
     * @return the base64 decoder
     */
    public Base64 getBase64() {
        return base64;
    }

    /**
     * Converts a string into a hexadecimal byte array
     *
     * @param input the string to convert
     * @return the converted string
     */
    public byte[] hexStringToByteArray(String input) {
        int length = input.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(input.charAt(i), 16) << 4)
                    + Character.digit(input.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Converts a given byte array into a hexadecimal string
     *
     * @param bytes the bytes to convert
     * @return the converted bytes
     */
    public String bytesToHexString(byte[] bytes) {
        StringBuffer output = new StringBuffer(bytes.length * 2);
        for (byte b : bytes) {
            String string = Integer.toString(0xFF & b, 16);
            if (string.length() < 2) {
                output.append('0');
            }
            output.append(string);
        }
        return output.toString();
    }
}
