package strategy;

import java.nio.charset.StandardCharsets;

public class FNV1aHashStrategy implements HashStrategy {
    // FNV-1a 64-bit constants
    private static final long FNV_PRIME = 0x100000001b3L;
    private static final long FNV_OFFSET_BASIS = 0xcbf29ce484222325L;

    @Override
    public long hash(String data) {
        long hash = FNV_OFFSET_BASIS;
        for (byte b : data.getBytes(StandardCharsets.UTF_8)) {
            hash ^= b;
            hash *= FNV_PRIME;
        }
        return hash;
    }
}
