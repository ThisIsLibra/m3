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
package bot.cryptography;

/**
 * This class contains the RC-4 algorithm to encrypt and decrypt data.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class Rc4 {

    /**
     * The sbox
     */
    private int[] sbox;

    /**
     * The RC-4 key to encrypt and decrypt data
     */
    private byte[] key;

    /**
     * The length of the sbox
     */
    private final int SBOX_LENGTH = 256;

    /**
     * This class contains the RC-4 algorithm to encrypt and decrypt data.
     *
     * @param key the RC-4 key to encrypt and decrypt data
     */
    public Rc4(byte[] key) {
        this.key = key;
    }

    /**
     * Decrypts the given value with the key that was given when this object was
     * created
     *
     * @param input the input to decrypt
     * @return the decrypted output
     */
    public byte[] decrypt(final byte[] input) {
        return encrypt(input);
    }

    /**
     * Encrypts the given value with the key that was given when this object was
     * created
     *
     * @param input the input to encrypt
     * @return the encrypted output
     */
    public byte[] encrypt(final byte[] input) {
        sbox = initialiseSbox();
        byte[] code = new byte[input.length];
        int i = 0;
        int j = 0;
        for (int n = 0; n < input.length; n++) {
            i = (i + 1) % SBOX_LENGTH;
            j = (j + sbox[i]) % SBOX_LENGTH;
            swap(i, j, sbox);
            int rand = sbox[(sbox[i] + sbox[j]) % SBOX_LENGTH];
            code[n] = (byte) (rand ^ (int) input[n]);
        }
        return code;
    }

    /**
     * Initialises the sbox
     *
     * @return the initiliased sbox
     */
    private int[] initialiseSbox() {
        int[] sbox = new int[SBOX_LENGTH];
        int j = 0;
        for (int i = 0; i < SBOX_LENGTH; i++) {
            sbox[i] = i;
        }
        for (int i = 0; i < SBOX_LENGTH; i++) {
            j = (j + sbox[i] + key[i % key.length] + SBOX_LENGTH) % SBOX_LENGTH;
            swap(i, j, sbox);
        }
        return sbox;
    }

    /**
     * Swaps the values at the index of i and j within the given array
     *
     * @param i index i
     * @param j index j
     * @param array the array to swap the values at the given indices in
     */
    private void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
