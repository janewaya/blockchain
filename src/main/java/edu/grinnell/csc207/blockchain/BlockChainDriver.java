package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;

import java.util.Scanner;

/**
 *
 * The main driver for the block chain program.
 *
 */
public class BlockChainDriver {

    /**
     * The main entry point for the program.
     *
     * @param args the command-line arguments
     * @throws NoSuchAlgorithmException
     * @throws NumberFormatException
     */
    public static void main(String[] args) throws NumberFormatException, NoSuchAlgorithmException {
        if (args.length != 1) {
            throw new RuntimeException("Please re-run with one integer arguement.");
        }
        BlockChain bc = new BlockChain(Integer.parseInt(args[0]));
        Scanner myObj = new Scanner(System.in);
        System.out.println(bc.toString());
        System.out.printf("Command? ");
        String command = myObj.nextLine();
        while (!command.equals("quit")) {
            if (command.equals("mine")) {
                System.out.printf("Amount transferred? ");
                int amt = myObj.nextInt();
                Block tmp = bc.mine(amt);
                System.out.println("amount = " + tmp.getAmount() + ", nonce = " + tmp.getNonce());
                myObj.nextLine();
            } else if (command.equals("append")) {
                System.out.printf("Amount transferred? ");
                int amt = myObj.nextInt();
                System.out.printf("Nonce? ");
                long nonce = myObj.nextLong();
                Block add = new Block(bc.getSize(), amt, bc.getHash(), nonce);
                bc.append(add);
                myObj.nextLine();
            } else if (command.equals("remove")) {
                if (bc.removeLast()) {
                    System.out.println("The last node was removed.");
                } else {
                    System.out.println("There are too few nodes to remove one.");
                }
            } else if (command.equals("check")) {
                if (bc.isValidBlockChain()) {
                    System.out.println("Chain is valid!");
                } else {
                    System.out.println("Chain is not valid!");
                }
            } else if (command.equals("report")) {
                bc.printBalances();
            } else if (command.equals("help")) {
                System.out.println("Valid commands: \n"
                        + "     mine: discovers the nonce for a given transaction\n"
                        + "     append: appends a new block onto the end of the chain\n"
                        + "     remove: removes the last block from the end of the chain\n"
                        + "     check: checks that the block chain is valid\n"
                        + "     report: reports the balances of Alice and Bob\n"
                        + "     help: prints this list of commands\n"
                        + "     quit: quits the program");
            } else {
                System.out.println(
                        "You did not enter a valid command. Please run this program again; if unsure, type help for a list of valid commands.");
            }
            System.out.println(bc.toString());
            System.out.printf("Command? ");
            command = myObj.nextLine();
        }
    }
}
