package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;

/**
 * A linked list of hash-consistent blocks representing a ledger of monetary
 * transactions.
 */
public class BlockChain {

    /**
     *
     * A linked implementation of BlockChain.
     *
     */
    private static class Node {

        private Block block;
        private Node nextNode;

        public Node(Block block, Node next) throws NoSuchAlgorithmException {
            this.block = block;
            this.nextNode = next;
        }
    }

    private Node first;
    private Node last;
    private int size;

    /**
     *
     * Creates and initializes a BlockChain
     *
     * @param initial Takes the initial amount for the BlockChain
     */
    public BlockChain(int initial) throws NoSuchAlgorithmException {
        this.first = null;
        this.last = null;
        this.size = 0;
        Block b = mine(initial);
        Node newBlock = new Node(b, null);
        this.first = newBlock;
        this.last = newBlock;
        this.size++;
    }

    /**
     * Creates a new block
     *
     * @param mine Takes an amount for the BlockChain
     * @return Block - a new block
     */
    public Block mine(int amount) throws NoSuchAlgorithmException {
        if (size != 0) {
            Node hold = this.first;
            for (int i = 0; i < (this.size - 1); i++) {
                hold = hold.nextNode;
            }

            Block ba = new Block((this.size), amount, hold.block.hash);
            return ba;
        } else {
            Block bb = new Block(this.size, amount, null);
            return bb;
        }
    }

    /**
     *
     * Returns the size of the BlockChain
     *
     * @return int returns the BlockChain size
     */
    public int getSize() {
        return this.size;
    }

    /**
     *
     * Adds a node to the end of the BlockChain
     *
     * @param blk the block to add to the end of the BlockChain
     */
    public void append(Block blk) throws NoSuchAlgorithmException {
        Node newAdd = new Node(blk, null);
        Node hold = this.last;
        hold.nextNode = newAdd;
        this.last = newAdd;
        this.size++;
    }

    /**
     *
     * Returns the number of nodes in the BlockChain
     *
     * @return the number of elements in the BlockChain
     */
    public int size() {
        return this.size;
    }

    /**
     *
     * Removes the last Node from the BlockChain
     *
     * @return boolean - if there was a node to remove
     */
    public boolean removeLast() {
        if (size <= 1) {
            return false;
        }

        Node hold = this.first;
        while (hold.nextNode != this.last) {
            hold = hold.nextNode;
        }

        hold.nextNode = null;
        this.last = hold;
        size--;
        return true;
    }

    /**
     *
     * Returns the last Hash in the BlockChain
     *
     * @return Hash - the hash of the last block
     */
    public Hash getHash() {
        return this.last.block.hash;
    }

    /**
     *
     * Checks if the BlockChain is currently valid
     *
     * @return boolean - if the amounts within the BlockChain represent a valid
     * series of transactions.
     */
    public boolean isValidBlockChain() {
        if (size < 1) {
            return true;
        }

        Node hold = this.first;
        int sum = 0;
        for (int i = 0; i < this.size; i++) {
            sum += hold.block.amount;
            if (sum > this.first.block.amount || sum < 0) {
                return false;
            }
            hold = hold.nextNode;
        }
        return true;
    }

    /**
     *
     * Prints the balances of Anna and Bob
     *
     *
     */
    public void printBalances() {
        if (size < 1) {
            System.out.println("Anna: " + this.first.block.amount + ", Bob: 0");
        }
        int Anna = 0;
        int Bob = 0;
        Node hold = this.first;
        for (int i = 0; i < this.size; i++) {
            Anna += hold.block.amount;
            if (hold != this.first) {
                Bob += hold.block.amount * -1;
            }
            hold = hold.nextNode;
        }
        System.out.println("Anna: " + Anna + ", Bob: " + Bob);
    }

    /**
     *
     * Prints the blocks within the BlockChain as a string of values.
     *
     * @return String - the String representation of every block in the
     * BlockChain.
     */
    public String toString() {
        Node hold = this.first;
        String blockChain = "";
        for (int i = 0; i < this.size; i++) {
            blockChain = blockChain.concat(hold.block.toString() + "\n");
            hold = hold.nextNode;
        }
        return blockChain;
    }
}
