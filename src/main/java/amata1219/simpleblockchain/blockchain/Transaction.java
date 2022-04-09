package amata1219.simpleblockchain.blockchain;

public record Transaction(String sender, String recipient, int amount) {
    @Override
    public String toString() {
        return """
                {
                    "transaction": {
                        "sender": "%s",
                        "recipient": "%s",
                        "amount": %d
                    }
                }
                """.formatted(sender, recipient, amount);
    }
}
