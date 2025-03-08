package edu.grinnell.csc207.blockchain;

import java.util.Arrays;

/**
 * A wrapper class over a hash value (a byte array).
 */
public class Hash {

    byte[] data;

    public Hash(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return this.data;
    }

    public boolean isValid() {
        if (this.data.length < 3) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            if (this.data[i] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        int sum = 0;
        for (int i = 0; i < this.data.length; i++) {
            sum += Byte.toUnsignedInt(this.data[i]);
        }
        String result = "%x";
        return String.format(result, sum);
    }

    public boolean equals(Object other) {
        if (other instanceof Hash) {
            Hash o = (Hash) other;
            return Arrays.equals(this.data, o.data);
        }
        return false;
    }

}
