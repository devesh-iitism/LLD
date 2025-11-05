package strategy;

import java.nio.charset.StandardCharsets;

public class DJB2HashStrategy implements HashStrategy {
    @Override
    public long hash(String data) {
        long hash = 5381L;
        for (byte b : data.getBytes(StandardCharsets.UTF_8)) {
            // hash = hash * 33 + c
            hash = ((hash << 5) + hash) + b;
        }
        return hash;
    }
}
