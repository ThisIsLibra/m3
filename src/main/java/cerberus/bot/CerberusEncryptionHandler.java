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
package cerberus.bot;

import bot.cryptography.GenericCryptographyHandler;
import bot.cryptography.Rc4;
import java.nio.charset.Charset;

/**
 * This class containers helper functions to handle the encryption and
 * decryption of data in the format that Cerberus requires
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class CerberusEncryptionHandler extends GenericCryptographyHandler {

    /**
     * The RC-4 key to encrypt and decrypt data
     */
    private Rc4 rc4;

    /**
     * Creates an instance of this helper class
     *
     * @param rc4Key the RC-4 key to encrypt and decrypt data
     */
    public CerberusEncryptionHandler(String rc4Key) {
        this.rc4 = new Rc4(rc4Key.getBytes());
    }

    /**
     * Encrypts the given input as is required for Cerberus
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
     * Decrypts the given input as is required for Cerberus
     *
     * @param input the input to decrypt
     * @return the decrypted output
     */
    public String decrypt(String input) {
        String response = new String(getBase64().decode(input), Charset.forName("UTF-8"));
        byte[] byteArray = hexStringToByteArray(response);
        byte[] decrypted = rc4.decrypt(byteArray);
        String output = new String(decrypted);
        return output;
    }
}
