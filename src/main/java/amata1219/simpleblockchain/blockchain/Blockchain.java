package amata1219.simpleblockchain.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {

    public final List<Block> chain;
    public List<Transaction> currentTransactions;

    public Blockchain() {
        chain = new ArrayList<>();
        currentTransactions = new ArrayList<>();

        //make the genesis block
        newBlock(100, "1");
    }

    public Block newBlock(int proof, String previousHash) {
        Block block = new Block(chain.size() + 1, System.currentTimeMillis(), currentTransactions, proof, previousHash);
        currentTransactions = new ArrayList<>();
        chain.add(block);
        return block;
    }

    public Block lastBlock() {
        return chain.get(chain.size() - 1);
    }

    //return the index of the block containing this transaction
    public int newTransaction(Transaction transaction) {
        currentTransactions.add(transaction);
        return lastBlock().index() + 1;
    }

    public int newTransaction(String sender, String recipient, int amount) {
        return newTransaction(new Transaction(sender, recipient, amount));
    }

}
