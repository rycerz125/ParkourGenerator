package org.sentillo.gepard.utils;

import org.sentillo.gepard.generator.jumps.BlockType;

public class BlockMatrix3d extends Matrix3d<BlockType>{

    //TODO: Do tests
    @Override
    public void turn90Left(){
        super.turn90Left();
        for(Vector3d vector3d: objects.keySet()){
            objects.put(vector3d, BlockType.rotateLeft(objects.get(vector3d)));
        }
    }
    @Override
    public void turn90Right(){
        super.turn90Right();
        for(Vector3d vector3d: objects.keySet()){
            objects.put(vector3d, BlockType.rotateRight(objects.get(vector3d)));
        }
    }
    @Override
    public void mirrorXAxis(){
        super.mirrorXAxis();
        for(Vector3d vector3d: objects.keySet()){
            objects.put(vector3d, BlockType.mirrorX(objects.get(vector3d)));
        }
    }
    @Override
    public void mirrorZAxis(){
        super.mirrorZAxis();
        for(Vector3d vector3d: objects.keySet()){
            objects.put(vector3d, BlockType.mirrorZ(objects.get(vector3d)));
        }
    }
}
