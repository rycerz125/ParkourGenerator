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
    @Override
    public void turn90Right(){
        super.turn90Right();
        for(Vector3d vector3d: objects.keySet()){
            objects.put(vector3d, turnBlock90Right(objects.get(vector3d)));
        }
    }
    @Override
    public void mirrorXAxis(){
        super.mirrorXAxis();
        for(Vector3d vector3d: objects.keySet()){
            objects.put(vector3d, mirrorBlockX(objects.get(vector3d)));
        }
    }
    @Override
    public void mirrorYAxis(){
        super.mirrorYAxis();
        for(Vector3d vector3d: objects.keySet()){
            objects.put(vector3d, mirrorBlockY(objects.get(vector3d)));
        }
    }
    private BlockType turnBlock90Left(BlockType blockType){
        return blockType.rotateLeft(blockType);
    }
    private BlockType turnBlock90Right(BlockType blockType){
        return blockType.rotateRight(blockType);
    }
    private BlockType mirrorBlockX(BlockType blockType){
        return blockType.mirrorX(blockType);
    }
    private BlockType mirrorBlockY(BlockType blockType){
        return blockType.mirrorY(blockType);
    }
}
