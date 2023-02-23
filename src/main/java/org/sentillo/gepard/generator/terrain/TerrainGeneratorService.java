package org.sentillo.gepard.generator.terrain;

import org.sentillo.gepard.generator.terrain.javascript.JavaScriptRunner;
import org.sentillo.gepard.utils.AccumulationService;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.Vector3d;

public class TerrainGeneratorService extends AccumulationService<TerrainGenerator>{
    public TerrainGeneratorService(String assetsFolder){
        super(assetsFolder, new TerrainGeneratorLoader());
    }
    public Matrix3d<TerrainColor> generate(String terrainType, TerrainMetadata metadata, String seed, Matrix3d<Boolean> mustBeAirLayer, Matrix3d<McBlock> jumpBlocksLayer){
        JavaScriptRunner jsRunner = new JavaScriptRunner();
        Matrix3d<Integer> world = new Matrix3d<>();

        TerrainGenerator tGenerator = accumulatedObjects.get(terrainType);
        if(tGenerator == null) throw new RuntimeException("Terrain with name "+ terrainType + " not found!");

        if(metadata != null)
            jsRunner.bind("metadata", metadata);
        else
            jsRunner.bind("metadata", tGenerator.getDefaultMetadata());

        jsRunner.bind("jumpBlocks", jumpBlocksLayer);
        jsRunner.bind("TerrainColor", TerrainColor.class);
        jsRunner.bind("Vector3d", Vector3d.class);
        jsRunner.bind("world", world);
        try {
            Object r = jsRunner.run(tGenerator.getCode());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        Matrix3d<TerrainColor> actualWorld = new Matrix3d<>();

        for(Vector3d pos : world.getAllLocations()){
            TerrainColor tcol;

            if(world.getObject(pos) == 0) tcol = TerrainColor.AIR;
            else tcol = TerrainColor.BLOCK1;

            actualWorld.setObject(pos, tcol);
        }

        return actualWorld;
    }
}
