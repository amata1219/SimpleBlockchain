package amata1219.simpleblockchain.blockchain;

import amata1219.simpleblockchain.crypto.Sha256;

public class Prover {
    public static boolean verifyProof(int lastProof, int proof) {
        String guess = "%d%d".formatted(lastProof, proof);
        String guessHash = Sha256.hash(guess);
        return guessHash.startsWith("0000");
    }

    public static int proofOfWork(int lastProof) {
        int proof = 0;
        while (!verifyProof(lastProof, proof)) proof += 1;
        return proof;
    }
}