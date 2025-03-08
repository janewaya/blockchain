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
            
            Node newAdd = new Node(0, initial, null, null);
            
            this.first = newAdd;
            this.last = newAdd;
            this.size++;
        }
        
        public Block mine(int amount){
            
        }

        /**
         *
         * Adds <code>value</code> to the end of the list
         *
         *
         *
         * @param value the value to add to the end of the list
         *
         */
        public void append(Block blk) {
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
         * @param index the index of the element to retrieve
         *
         * @return the value at the specified <code>index</code>
         *
         */
        public int get(int index) {
            Node hold = this.first;
            if (index >= size) {
                return 0;
            }
            for (int i = 0; i < index; i++) {
                hold = hold.nextNode;
            }
            return hold.val;
        }

        /**
         *
         * Removes the value at <code>index</code> from the list
         *
         *
         *
         * @param index the index of the element to remove
         *
         * @return the element at <code>index</code>
         *
         */
        public int remove(int index) {
            Node current = this.first;
            int valRemoved = 0;
            if (index < size) {
                for (int n = 0; n < index - 1; n++) {
                    if (current.nextNode != null) {
                        current = current.nextNode;
                    }
                    valRemoved = current.nextNode.val;
                }
                current.nextNode = current.nextNode.nextNode;
            }
            return valRemoved;
        }
    }
}
