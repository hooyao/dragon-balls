package com.hy.distributed.dragonball.shard;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class ShardServiceTest {

    private static int TEST_DATA_SIZE = 1024*1024*10; //10MB
    private static int SHARD_COUNT = 15;
    private static int PARiTY_COUNT = 5;

    private static byte[] rawTestData = new byte[TEST_DATA_SIZE];

    {
        new Random().nextBytes(rawTestData);
    }


    @Test
    public void shard_data_should_equil_to_origin() {
        ShardService shardService = new ShardService();
        int shardSize = TEST_DATA_SIZE / (SHARD_COUNT - 1);
        byte[][] shardedData = shardService.generateShards(rawTestData, SHARD_COUNT, PARiTY_COUNT);
        for (int i = 0; i < SHARD_COUNT; i++) {
            byte[] shardBlock = shardedData[i];
            byte[] originBlock = new byte[shardSize];
            if (i == SHARD_COUNT - 1) {
                System.arraycopy(rawTestData, i * shardSize, originBlock, 0,
                        rawTestData.length - i * shardSize);
                Arrays.fill(originBlock, rawTestData.length - i * shardSize, shardSize, (byte) 0xFF);
            } else {
                originBlock = Arrays.copyOfRange(rawTestData, i * shardSize, i * shardSize + shardSize);
            }
            assertArrayEquals(originBlock, shardBlock);
        }
    }

    @Test
    public void shard_data_should_recover() {
        ShardService shardService = new ShardService();
        byte[][] shardedData = shardService.generateShards(rawTestData, SHARD_COUNT, PARiTY_COUNT);
        byte[] recoveredData = shardService.recoverFromShards(shardedData, SHARD_COUNT, rawTestData.length);
        assertArrayEquals(rawTestData, recoveredData);
    }

}
