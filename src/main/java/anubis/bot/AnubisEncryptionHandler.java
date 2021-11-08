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
package anubis.bot;

import bot.cryptography.GenericCryptographyHandler;
import bot.cryptography.Rc4;
import java.nio.charset.Charset;

/**
 * This class provides helper functions to more easily encrypt and decrypt data
 * in the way that the Anubis bot requires
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class AnubisEncryptionHandler extends GenericCryptographyHandler {

    /**
     * The RC-4 object to handle RC-4 operations
     */
    private Rc4 rc4;

    /**
     * Creates an instance of this helper class, which contains the routines to
     * easily encrypt and decrypt data in the way that the Anubis bot requires
     *
     * @param rc4Key the RC-4 key
     */
    public AnubisEncryptionHandler(String rc4Key) {
        this.rc4 = new Rc4(rc4Key.getBytes());
    }

    /**
     * Encrypts data in the way that Anubis requires
     *
     * @param input the input to encrypt
     * @return the encrypted output
     */
    public String encrypt(String input) {
        byte[] tempByteArray = rc4.encrypt(input.getBytes());
        tempByteArray = bytesToHexString(tempByteArray).getBytes();
        return getBase64().encodeToString(tempByteArray);
    }

    /**
     * Removes the default tags from the given string
     *
     * @param text the string to remove the tags from
     * @return the tagless input
     */
    public String untag(String text) {
        String tagPrefix = "<tag>";
        String tagPostfix = "</tag>";
        return untag(text, tagPrefix, tagPostfix);
    }

    /**
     * Removes the given tags from the given text
     *
     * @param text the text to remove the tags from
     * @param tagPrefix the prefix to remove
     * @param tagPostfix the postfix to remove
     * @return the tagless input
     */
    public String untag(String text, String tagPrefix, String tagPostfix) {
        try {
            int start = text.indexOf(tagPrefix) + tagPrefix.length();
            int end = text.indexOf(tagPostfix);
            text = text.substring(start, end);
            return text;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Decrypts data in the way that Anubis requires
     *
     * @param input the input to decrypt
     * @return the decrypt output
     */
    public String decrypt(String input) {
        String response = new String(getBase64().decode(input), Charset.forName("UTF-8"));
        byte[] byteArray = hexStringToByteArray(response);
        byte[] decrypted = rc4.decrypt(byteArray);
        String output = new String(decrypted);
        return output;
    }
}
