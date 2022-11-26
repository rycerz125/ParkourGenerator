package org.sentillo.gepard.jumps;

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

    public BlockType rotateLeft(BlockType blockType){
        return blockType;
    }
    public BlockType rotateRight(BlockType blockType){
        return blockType;
    }
    public BlockType mirrorX(BlockType blockType){
        return blockType;
    }
    public BlockType mirrorY(BlockType blockType){
        return blockType;
    }
}
