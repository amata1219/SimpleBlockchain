package amata1219.simpleblockchain;

import amata1219.simpleblockchain.blockchain.Block;
import amata1219.simpleblockchain.blockchain.Blockchain;
import amata1219.simpleblockchain.blockchain.Prover;
import amata1219.simpleblockchain.blockchain.Transaction;
import amata1219.simpleblockchain.json.MapJson;
import amata1219.simpleblockchain.json.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class SimpleBlockchainAPI {

    private final Blockchain blockchain = new Blockchain();

    private final String nodeIdentifier = UUID.randomUUID().toString().replace("-", "");

    @GetMapping("/")
    public String index() {
        return "hello";
    }

    @RequestMapping(value = "/transactions/new", method = RequestMethod.POST)
    public ResponseEntity<String> newTransactions(@RequestBody Transaction transaction) {
        int index = blockchain.newTransaction(transaction);

        String response = new MapJson(new Pair("message", "トランザクションはブロック %d に追加されました".formatted(index))).toJson();
        return newResponse(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/mine", method = RequestMethod.GET)
    public ResponseEntity<String> mine() {
        Block lastBlock = blockchain.lastBlock();
        int lastProof = lastBlock.proof();
        int proof = Prover.proofOfWork(lastProof);

        blockchain.newTransaction("0", nodeIdentifier, 1);

        Block block = blockchain.newBlock(proof, null);

        String response = new MapJson(
                new Pair("message", "新しいブロックを採掘しました"),
                new Pair("index", block.index()),
                new Pair("transactions", block.transactions()),
                new Pair("proof", block.proof()),
                new Pair("previous_hash", block.previousHash())
        ).toJson();
        return newResponse(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/chain", method = RequestMethod.GET)
    public String fullChain() {
        return "chain";
    }

    private static <T> ResponseEntity<T> newResponse(T response, HttpStatus status) {
        return new ResponseEntity<>(response, status);
    }

}
