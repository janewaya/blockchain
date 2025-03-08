package edu.grinnell.csc207.blockchain;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A single block of a blockchain.
 */
public class Block {

    int num;
    int amount;
    Hash prevHash;
    long nonce;
    Hash hash;

    public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        long i = 0;
        Hash h = new Hash(ByteBuffer.allocate(8).putLong(i).array());
        while(h.isValid()){
            i++;
        h = new Hash(ByteBuffer.allocate(8).putLong(i).array());
        }
        this.nonce = i;
        if(num != 0){
            this.hash = new Hash(calculateHash(this.num, this.amount, this.prevHash, this.nonce));
        } else {
            this.hash = new Hash(calculateHashFirst(this.num, this.amount, this.nonce));
        }
    }

    public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
        if(num != 0){
            this.hash = new Hash(calculateHash(this.num, this.amount, this.prevHash, this.nonce));
        } else {
            this.hash = new Hash(calculateHashFirst(this.num, this.amount, this.nonce));
        }
    }
    
    public static byte[] calculateHash(Integer num, Integer amount, Hash prevHash, Long nonce) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.update(num.byteValue());
        md.update(amount.byteValue());
        md.update(prevHash.getData());
        md.update(nonce.byteValue());
        byte[] hash = md.digest();
        return hash;
    }
    
    public static byte[] calculateHashFirst(Integer num, Integer amount, Long nonce) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.update(num.byteValue());
        md.update(amount.byteValue());
        md.update(nonce.byteValue());
        byte[] hash = md.digest();
        return hash;
    }

    public int getNum() {
        return this.num;
    }

    public int getAmount() {
        return this.amount;
    }

    public long getNonce() {
        return this.nonce;
    }

    public Hash getPrevHash() {
        return this.prevHash;
    }

    public Hash getHash() {
        return this.hash;
    }

    @Override
    public String toString() {
        String result = "Block %d (Amount: %d, Nonce: %d, prevHash: %s, hash: %s";
        return String.format(result, this.num, this.amount, this.nonce, this.prevHash.toString(), this.hash.toString());
    }

}
