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

    public BlockChain(int initial) throws NoSuchAlgorithmException {

        this.first = null;
        this.last = null;
        this.size = 0;

        Block b = mine(initial);

        this.first.block = b;
        this.last.block = b;
        this.size++;
    }

    public Block mine(int amount) throws NoSuchAlgorithmException {

        Node hold = this.first;
        for (int i = 0; i < (this.size - 1); i++) {
            hold = hold.nextNode;
        }

        Block b = new Block(this.size, amount, hold.block.hash);

        return b;
    }

    public int getSize() {
        return this.size;
    }

    /**
     *
     * Adds <code>value</code> to the end of the list
     *
     *
     *
     * @param blk the block to add to the end of the list
     *
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
     * @return the number of elements in the list
     *
     */
    public int size() {
        return this.size;
    }


    /**
     *
     * Removes the value at <code>index</code> from the list
     *
     *
     *
     * @param index the index of the element Node hold = this.first; for (int i
     * = 0; i < (this.size - 1); i++) { hold = hold.nextNode; }to remove
     *
     * @return the element at <code>index</code>
     *
     */
    public boolean removeLast() {

        if (size <= 1) {
            return false;
        }

        Node hold = this.first;
        for (int i = 0; i < (this.size - 1); i++) {
            hold = hold.nextNode;
        }

        hold.nextNode = null;
        this.last = hold;
        return true;
    }
    
    public Hash getHash(){
        return this.last.block.hash;
    }
    
    public boolean isValidBlockChain(){
        return true;
    }

}
