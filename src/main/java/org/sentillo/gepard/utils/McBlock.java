package org.sentillo.gepard.utils;

import lombok.Getter;

public enum McBlock {
    AIR(0,0),
    GRASS(1,0);

    @Getter
    private int id;

    @Getter
    private int variant;

    McBlock(int id, int variant){
        this.id = id;
        this.variant = variant;
    }
}
