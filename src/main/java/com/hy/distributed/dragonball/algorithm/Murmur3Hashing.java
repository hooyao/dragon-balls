package com.hy.distributed.dragonball.algorithm;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class Murmur3Hashing implements ConsistentHashingStrategy {

    @Override
    public int computeHash(byte[] key) {
        HashFunction murmur3Hf = Hashing.murmur3_128();
        return murmur3Hf.newHasher().putBytes(key).hash().asInt();
    }
}
