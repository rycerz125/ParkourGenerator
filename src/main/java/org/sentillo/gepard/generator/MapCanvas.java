package org.sentillo.gepard.generator;

import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;

import lombok.Getter;
import lombok.Setter;

public class MapCanvas {

    @Getter
    @Setter
    private Matrix3d<McBlock> jumpBlocksLayer;

    @Getter
    @Setter
    private Matrix3d<Boolean> mustBeAirLayer;

    @Getter
    @Setter
    private Matrix3d<McBlock> terrainLayer;

    @Getter
    @Setter
    private Matrix3d<McBlock> assetsLayer;

    public Matrix3d<McBlock> bakeToOneMap(){
        return null;
    }
}
