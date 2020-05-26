package com.hy.distributed.dragonball.geo;

import java.util.TreeSet;

public class Ring {
    // Due to the restriction of Java, no unsined int, the ring starts from -2^31 to 2^31
    private static final int MIN = Integer.MIN_VALUE;
    private static final int MAX = Integer.MAX_VALUE;

    private TreeSet<VirtualNode> virtualNodes = new TreeSet<>();

    public Ring(){

    }

    public synchronized void addVirualNode(int rintPos, int phy){

    }
}
