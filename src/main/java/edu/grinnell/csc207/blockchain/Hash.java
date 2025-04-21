package edu.grinnell.csc207.blockchain;

import java.util.Arrays;

/**
 * A wrapper class over a hash value (a byte array).
 */
public class Hash {

    byte[] data;

    /**
     * Creates a Hash object
     *
     * @param data The data for the Hash to be based on
     */
    public Hash(byte[] data) {
        this.data = data;
    }

    /**
     * Returns the data stored within the Hash
     *
     * @return byte[] - The data stored within the Hash
     */
    public byte[] getData() {
        return this.data;
    }

    /**
     * Tells if the Hash isValid (first three digits are 0)
     *
     * @return boolean - If the first 3 digits are 0
     */
    public boolean isValid() {
        if (this.data.length < 3) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            if (Byte.toUnsignedInt(this.data[i]) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the data stored within the Hash as a string
     *
     * @return String - the String version of the data stored within the Hash
     *
     */
    @Override
    public String toString() {
        int num = 0;
        String indiInt = "";
        for (int i = 0; i < this.data.length; i++) {
            num = Byte.toUnsignedInt(this.data[i]);
            String result = "%x";
            indiInt = indiInt.concat(String.format(result, num));
        }
        return indiInt;
    }

    /**
     * Checks if an object is a Hash and if so if it is the same as another Hash
     *
     * @param other The object we are checking for Hash equality
     * @return boolean - returns true if they contain the same data
     */
    public boolean equals(Object other) {
        if (other instanceof Hash) {
            Hash o = (Hash) other;
            return Arrays.equals(this.data, o.data);
        }
        return false;
    }
}
