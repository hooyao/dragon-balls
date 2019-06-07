package com.hy.distributed.dragonball.geo;

public class VirtualNode implements Comparable<VirtualNode> {
    protected final int position;

    public VirtualNode(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int compareTo(VirtualNode rn) {
        return this.getPosition() - rn.getPosition();
    }

}
