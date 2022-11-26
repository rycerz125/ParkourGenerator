package org.sentillo.gepard.utils;

import org.sentillo.gepard.jumps.BlockType;

public class BlockMatrix3d extends Matrix3d<BlockType>{
    @Override
    public void turn90Left(){
        super.turn90Left();
        for(Vector3d vector3d: objects.keySet()){
            objects.put(vector3d, turnBlock90Left(objects.get(vector3d)));
        }
    }
    private BlockType turnBlock90Left(BlockType blockType){

    }
}
