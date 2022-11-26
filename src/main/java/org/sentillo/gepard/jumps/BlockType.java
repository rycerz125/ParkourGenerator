package org.sentillo.gepard.jumps;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public enum BlockType {
    BLOCK,

    STAIR_NORTH,
    STAIR_WEST,
    STAIR_SOUTH,
    STAIR_EAST,

    STAIR_NORTH_FLIP,
    STAIR_WEST_FLIP,
    STAIR_SOUTH_FLIP,
    STAIR_EAST_FLIP,

    STAIR_NORTH_WEST,
    STAIR_WEST_SOUTH,
    STAIR_SOUTH_EAST,
    STAIR_EAST_NORTH,

    STAIR_NORTH_WEST_FAT,
    STAIR_WEST_SOUTH_FAT,
    STAIR_SOUTH_EAST_FAT,
    STAIR_EAST_NORTH_FAT,

    STAIR_NORTH_WEST_FLIP,
    STAIR_WEST_SOUTH_FLIP,
    STAIR_SOUTH_EAST_FLIP,
    STAIR_EAST_NORTH_FLIP,

    STAIR_NORTH_WEST_FAT_FLIP,
    STAIR_WEST_SOUTH_FAT_FLIP,
    STAIR_SOUTH_EAST_FAT_FLIP,
    STAIR_EAST_NORTH_FAT_FLIP;

    public static BlockType rotateLeft(BlockType blockType){
        if(keyNormalValueLeft == null)
            initialisePairs();

        BlockType rotated;
        if(keyNormalValueLeft.containsKey(blockType))
            rotated = keyNormalValueLeft.get(blockType);
        else rotated = blockType;

        return rotated;
    }
    public static BlockType rotateRight(BlockType blockType){
        if(keyNormalValueLeft == null)
            initialisePairs();

        BlockType rotated;
        if(keyNormalValueLeft.containsValue(blockType))
            rotated = keyNormalValueLeft.inverse().get(blockType);
        else rotated = blockType;

        return rotated;
    }
    public static BlockType mirrorX(BlockType blockType){
        if(mirrorX == null)
            initialisePairs();

        BlockType mirrored;
        if(mirrorX.containsKey(blockType))
            mirrored = mirrorX.get(blockType);
        else if(mirrorX.containsValue(blockType))
            mirrored = mirrorX.inverse().get(blockType);
        else
            mirrored = blockType;
        return mirrored;
    }
    public static BlockType mirrorZ(BlockType blockType){
        if(mirrorZ == null)
            initialisePairs();

        BlockType mirrored;
        if(mirrorZ.containsKey(blockType))
            mirrored = mirrorZ.get(blockType);
        else if(mirrorZ.containsValue(blockType))
            mirrored = mirrorZ.inverse().get(blockType);
        else
            mirrored = blockType;
        return mirrored;
    }
    private static BiMap<BlockType,BlockType> keyNormalValueLeft = null;
    private static BiMap<BlockType, BlockType> mirrorX = null;
    private static BiMap<BlockType, BlockType> mirrorZ = null;
    public static void initialisePairs(){
        keyNormalValueLeft = HashBiMap.create();

        keyNormalValueLeft.put(STAIR_NORTH, STAIR_WEST);
        keyNormalValueLeft.put(STAIR_WEST, STAIR_SOUTH);
        keyNormalValueLeft.put(STAIR_SOUTH, STAIR_EAST);
        keyNormalValueLeft.put(STAIR_EAST, STAIR_NORTH);

        keyNormalValueLeft.put(STAIR_NORTH_FLIP, STAIR_WEST_FLIP);
        keyNormalValueLeft.put(STAIR_WEST_FLIP, STAIR_SOUTH_FLIP);
        keyNormalValueLeft.put(STAIR_SOUTH_FLIP, STAIR_EAST_FLIP);
        keyNormalValueLeft.put(STAIR_EAST_FLIP, STAIR_NORTH_FLIP);

        keyNormalValueLeft.put(STAIR_NORTH_WEST, STAIR_WEST_SOUTH);
        keyNormalValueLeft.put(STAIR_WEST_SOUTH, STAIR_SOUTH_EAST);
        keyNormalValueLeft.put(STAIR_SOUTH_EAST, STAIR_EAST_NORTH);
        keyNormalValueLeft.put(STAIR_EAST_NORTH, STAIR_NORTH_WEST);

        keyNormalValueLeft.put(STAIR_NORTH_WEST_FAT, STAIR_WEST_SOUTH_FAT);
        keyNormalValueLeft.put(STAIR_WEST_SOUTH_FAT, STAIR_SOUTH_EAST_FAT);
        keyNormalValueLeft.put(STAIR_SOUTH_EAST_FAT, STAIR_EAST_NORTH_FAT);
        keyNormalValueLeft.put(STAIR_EAST_NORTH_FAT, STAIR_NORTH_WEST_FAT);

        keyNormalValueLeft.put(STAIR_NORTH_WEST_FLIP, STAIR_WEST_SOUTH_FLIP);
        keyNormalValueLeft.put(STAIR_WEST_SOUTH_FLIP, STAIR_SOUTH_EAST_FLIP);
        keyNormalValueLeft.put(STAIR_SOUTH_EAST_FLIP, STAIR_EAST_NORTH_FLIP);
        keyNormalValueLeft.put(STAIR_EAST_NORTH_FLIP, STAIR_NORTH_WEST_FLIP);

        keyNormalValueLeft.put(STAIR_NORTH_WEST_FAT_FLIP, STAIR_WEST_SOUTH_FAT_FLIP);
        keyNormalValueLeft.put(STAIR_WEST_SOUTH_FAT_FLIP, STAIR_SOUTH_EAST_FAT_FLIP);
        keyNormalValueLeft.put(STAIR_SOUTH_EAST_FAT_FLIP, STAIR_EAST_NORTH_FAT_FLIP);
        keyNormalValueLeft.put(STAIR_EAST_NORTH_FAT_FLIP, STAIR_NORTH_WEST_FAT_FLIP);

        mirrorX = HashBiMap.create();

        mirrorX.put(STAIR_NORTH, STAIR_SOUTH);

        mirrorX.put(STAIR_NORTH_FLIP, STAIR_SOUTH_FLIP);

        mirrorX.put(STAIR_NORTH_WEST, STAIR_WEST_SOUTH);
        mirrorX.put(STAIR_EAST_NORTH, STAIR_SOUTH_EAST);

        mirrorX.put(STAIR_NORTH_WEST_FAT, STAIR_WEST_SOUTH_FAT);
        mirrorX.put(STAIR_EAST_NORTH_FAT, STAIR_SOUTH_EAST_FAT);

        mirrorX.put(STAIR_NORTH_WEST_FLIP, STAIR_WEST_SOUTH_FLIP);
        mirrorX.put(STAIR_EAST_NORTH_FLIP, STAIR_SOUTH_EAST_FLIP);

        mirrorX.put(STAIR_NORTH_WEST_FAT_FLIP, STAIR_WEST_SOUTH_FAT_FLIP);
        mirrorX.put(STAIR_EAST_NORTH_FAT_FLIP, STAIR_SOUTH_EAST_FAT_FLIP);

        mirrorZ = HashBiMap.create();

        mirrorZ.put(STAIR_WEST, STAIR_EAST);

        mirrorZ.put(STAIR_WEST_FLIP, STAIR_EAST_FLIP);

        mirrorZ.put(STAIR_NORTH_WEST, STAIR_EAST_NORTH);
        mirrorZ.put(STAIR_WEST_SOUTH, STAIR_SOUTH_EAST);

        mirrorZ.put(STAIR_NORTH_WEST_FAT, STAIR_EAST_NORTH_FAT);
        mirrorZ.put(STAIR_WEST_SOUTH_FAT, STAIR_SOUTH_EAST_FAT);

        mirrorZ.put(STAIR_NORTH_WEST_FLIP, STAIR_EAST_NORTH_FLIP);
        mirrorZ.put(STAIR_WEST_SOUTH_FLIP, STAIR_SOUTH_EAST_FLIP);

        mirrorZ.put(STAIR_NORTH_WEST_FAT_FLIP, STAIR_EAST_NORTH_FAT_FLIP);
        mirrorZ.put(STAIR_WEST_SOUTH_FAT_FLIP, STAIR_SOUTH_EAST_FAT_FLIP);
    }


}
