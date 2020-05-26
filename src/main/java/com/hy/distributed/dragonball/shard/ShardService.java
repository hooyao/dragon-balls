package com.hy.distributed.dragonball.shard;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ShardService {

    public byte[][] generateShards(final byte[] data, final int shardCount, final int parityCount) {
        int shardSize = data.length / (shardCount - 1);
        byte[][] shardedData = new byte[shardCount + parityCount][shardSize];
        int dataLeftSize = data.length;
        for (int i = 0; i < shardCount; i++) {
            if (dataLeftSize >= shardSize) {
                System.arraycopy(data, i * shardSize, shardedData[i], 0, shardSize);
                dataLeftSize -= shardSize;
            } else {
                shardedData[i] = new byte[shardSize];
                System.arraycopy(data, i * shardSize, shardedData[i], 0, dataLeftSize);
                Arrays.fill(shardedData[i], dataLeftSize, shardSize, (byte) 0xFF);
            }
        }
        return shardedData;
    }

    public byte[] recoverFromShards(final byte[][] shardedData, int shardCount, final int dataLength) {
        int shardSize = shardedData[0].length;
        byte[] recoveredData = new byte[dataLength];
        int dataLeftSize = dataLength;
        for (int i = 0; i < shardCount; i++) {
            if (dataLeftSize >= shardSize) {
                System.arraycopy(shardedData[i], 0, recoveredData, i * shardSize, shardSize);
                dataLeftSize -= shardSize;
            } else {
                System.arraycopy(shardedData[i], 0, recoveredData, i * shardSize, dataLeftSize);
            }
        }
        return recoveredData;
    }
}
