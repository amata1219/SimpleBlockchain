package amata1219.simpleblockchain.blockchain;

import java.util.List;

public record Block(int index, long timestamp, List<Transaction> transactions, int proof, String previousHash) {
    @Override
    public String toString() {
        return """
                {
                    "block": {
                        "index": %d,
                        "timestamp": %d,
                        "transactions": %s,
                        "proof": %d,
                        "previousHash": %s
                    }
                }
                """.formatted(index, timestamp, transactions, proof, previousHash);
    }
}
