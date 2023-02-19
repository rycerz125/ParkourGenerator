package org.sentillo.gepard.generator.terrain;

import org.sentillo.gepard.utils.AccumulationService;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;

public class TerrainGeneratorService extends AccumulationService<TerrainGenerator>{
    public TerrainGeneratorService(String assetsFolder){
        super(assetsFolder, new TerrainGeneratorLoader());
    }
    public Matrix3d<TerrainColor> generate(String terrainType, TerrainMetadata metadata, String seed, Matrix3d<Boolean> mustBeAirLayer, Matrix3d<McBlock> jumpBlocksLayer){
        return new Matrix3d<>();
    }
}
