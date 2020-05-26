package com.hy.distributed.dragonball.algorithm;

public interface ConsistentHashingStrategy {
    int computeHash(byte[] key);
}
