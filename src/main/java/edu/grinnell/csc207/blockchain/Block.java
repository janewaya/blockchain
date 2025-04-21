package edu.grinnell.csc207.blockchain;

import java.nio.ByteBuffer;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

/**
 * A single block of a blockchain.
 *
 */
public class Block {

    int num;
    int amount;
    Hash prevHash;
    long nonce;
    Hash hash;
    public int nonceSet = 1;

    /**
     * Creates a new Block object
     *
     * @param num the number of the block in the list
     * @param amount the amount of the transaction stored within the block
     * @param prevHash the Hash of the previous block
     */
    public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
        do {
            this.num = num;
            this.amount = amount;
            this.prevHash = prevHash;
            nonceSet++;
            this.nonce = nonceSet;
            if (num != 0) {
                this.hash = new Hash(calculateHash(this.num, this.amount, 
                                                   this.prevHash, this.nonce));
            } else {
                this.hash = new Hash(calculateHashFirst(this.num, this.amount, this.nonce));
            }
        } while (!this.hash.isValid());
    }

    /**
     * Creates a new Block object
     *
     * @param num the number of the block in the list
     * @param amount the amount of the transaction stored within the block
     * @param prevHash the Hash of the previous block
     * @param nonce the nonce of the block
     */
    public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
        if (num != 0) {
            this.hash = new Hash(calculateHash(this.num, this.amount, this.prevHash, this.nonce));
        } else {
            this.hash = new Hash(calculateHashFirst(this.num, this.amount, this.nonce));
        }
    }

    /**
     * Finds a new Hash
     *
     * @param num the number of the block in the list
     * @param amount the amount of the transaction stored within the block
     * @param prevHash the Hash of the previous block
     * @param nonce the nonce of the block
     * @return byte[] Returns a hash for a new block
     */
    public static byte[] calculateHash(Integer num, Integer amount, Hash prevHash, 
                                       Long nonce) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");
        byte[] bytes1 = ByteBuffer.allocate(4).putInt(num).array();
        md.update(bytes1);
        byte[] bytes2 = ByteBuffer.allocate(4).putInt(amount).array();
        md.update(bytes2);
        md.update(prevHash.getData());
        byte[] bytes3 = ByteBuffer.allocate(8).putLong(nonce).array();
        md.update(bytes3);
        byte[] hash = md.digest();
        return hash;
    }

    /**
     * Finds a new Hash
     *
     * @param num the number of the block in the list
     * @param amount the amount of the transaction stored within the block
     * @param nonce the nonce of the block
     * @return byte[] Returns a hash for a new block
     */
    public static byte[] calculateHashFirst(Integer num, Integer amount, 
                                            Long nonce) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");
        byte[] bytes1 = ByteBuffer.allocate(4).putInt(num).array();
        md.update(bytes1);
        byte[] bytes2 = ByteBuffer.allocate(4).putInt(amount).array();
        md.update(bytes2);
        byte[] bytes3 = ByteBuffer.allocate(8).putLong(nonce).array();
        md.update(bytes3);
        byte[] hash = md.digest();
        return hash;
    }

    /**
     * Returns the number of the block in the blockchain
     *
     * @return int the number of the block
     */
    public int getNum() {
        return this.num;
    }

    /**
     * Returns the amount in the blockchain
     *
     * @return int the amount stored in the block
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Returns the nonce of the block in the blockchain
     *
     * @return long the nonce of the block
     */
    public long getNonce() {
        return this.nonce;
    }

    /**
     * Returns the hash of the previous block in the blockchain
     *
     * @return Hash the hash of the previous block
     *
     */
    public Hash getPrevHash() {
        return this.prevHash;
    }

    /**
     * Returns the hash of the block in the blockchain
     *
     * @return Hash the hash of the block
     */
    public Hash getHash() {
        return this.hash;
    }

    /**
     * Returns the block of the blockChain in string format
     *
     * @return String the string representation of the block.
     */
    @Override
    public String toString() {
        if (this.prevHash == null) {
            String result2 = "Block %d (Amount: %d, Nonce: %d, prevHash: null, hash: %s)";
            return String.format(result2, this.num, this.amount, this.nonce, this.hash.toString());
        }
        String result1 = "Block %d (Amount: %d, Nonce: %d, prevHash: %s, hash: %s)";
        return String.format(result1, this.num, this.amount, this.nonce, 
                             this.prevHash.toString(), this.hash.toString());
    }
}
