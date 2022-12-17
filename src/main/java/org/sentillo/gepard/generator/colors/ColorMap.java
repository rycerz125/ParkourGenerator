package org.sentillo.gepard.generator.colors;

import java.util.Map;

import org.sentillo.gepard.generator.jumps.BlockType;
import org.sentillo.gepard.generator.terrain.TerrainColor;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.MatrixConverter;
import org.sentillo.gepard.utils.MapConverter;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.Named;

import lombok.Getter;

public class ColorMap implements Named{

    @Getter
    private String name;

    private MatrixConverter<BlockType, McBlock> jumpMapConverter;
    private MatrixConverter<TerrainColor, McBlock> terrainColorConverter;

    public ColorMap(String name, Map<BlockType, McBlock> jumpBlockMap, Map<TerrainColor, McBlock> terrainColorBlockMap){
        this.name = name;
        jumpMapConverter = new MatrixConverter<>(
            new MapConverter<>(jumpBlockMap)
        );
        terrainColorConverter = new MatrixConverter<>(
            new MapConverter<>(terrainColorBlockMap)
        );
    }
    
    public Matrix3d<McBlock> convertJumpMapToBlocks(Matrix3d<BlockType> matrix) {
        return jumpMapConverter.convert(matrix);
    }
    public Matrix3d<McBlock> convertTerrainColorMapToBlocks(Matrix3d<TerrainColor> terrainColorMatrix3d){
        return terrainColorConverter.convert(terrainColorMatrix3d);
    }
}
