package amata1219.simpleblockchain.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Sha256 {
    public static String hash(Object object) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = Objects.requireNonNull(md).digest(object.toString().getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder(0xff * hash.length);
        for (byte cipherByte : hash) sb.append(String.format("%02x", cipherByte & 0xff));

        return sb.toString();
    }
}