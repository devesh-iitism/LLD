package factory;

import enums.HashType;
import strategy.DJB2HashStrategy;
import strategy.FNV1aHashStrategy;
import strategy.HashStrategy;

public class HashStrategyFactory {
    public static HashStrategy create(HashType type) {
        switch (type) {
            case FNV1A:
                return new FNV1aHashStrategy();
            case DJB2:
                return new DJB2HashStrategy();
            default:
                throw new IllegalArgumentException("Unsupported hash type: " + type);
        }
    }
}
