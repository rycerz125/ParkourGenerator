package org.sentillo.gepard.utils;

import org.sentillo.gepard.generator.jumps.BlockType;

public class BlockMatrix3d extends Matrix3d<BlockType>{

    @Override
    public void turn90Left(){
        super.turn90Left();
        for(var entry: objects.entrySet()){
            objects.put(entry.getKey(), BlockType.rotateLeft(entry.getValue()));
        }
    }
    @Override
    public void turn90Right(){
        super.turn90Right();
        for(var entry: objects.entrySet()){
            objects.put(entry.getKey(), BlockType.rotateRight(entry.getValue()));
        }
    }
    @Override
    public void mirrorXAxis(){
        super.mirrorXAxis();
        for(var entry: objects.entrySet()){
            objects.put(entry.getKey(), BlockType.mirrorX(entry.getValue()));
        }
    }
    @Override
    public void mirrorZAxis(){
        super.mirrorZAxis();
        for(var entry: objects.entrySet()){
            objects.put(entry.getKey(), BlockType.mirrorZ(entry.getValue()));
        }
    }
}
