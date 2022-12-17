package org.sentillo.gepard.generator.terrain;

import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.Named;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class TerrainGenerator implements Named{
    @Getter
    private String name;

    private String code;

    private TerrainMetadata defaultMetadata;

    public Matrix3d<Short> generateTerrain(TerrainMetadata terrainMetadata){
        return null;
    }
}
