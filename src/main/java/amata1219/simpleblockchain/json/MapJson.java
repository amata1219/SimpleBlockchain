package amata1219.simpleblockchain.json;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public record MapJson(Pair... pairs) {
    public String toJson() {
        Map<String, String> values = new HashMap<>();
        for (Pair pair : pairs) values.put(pair.key(), pair.value().toString());

        Gson gson = new Gson();
        return gson.toJson(values);
    }
}
