package org.sentillo.gepard.generator.terrain;

import org.sentillo.gepard.generator.terrain.javascript.JavaScriptRunner;
import org.sentillo.gepard.utils.AccumulationService;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;

public class TerrainGeneratorService extends AccumulationService<TerrainGenerator>{
    public TerrainGeneratorService(String assetsFolder){
        super(assetsFolder, new TerrainGeneratorLoader());
    }
    public Matrix3d<TerrainColor> generate(String terrainType, TerrainMetadata metadata, String seed, Matrix3d<Boolean> mustBeAirLayer, Matrix3d<McBlock> jumpBlocksLayer){
        JavaScriptRunner jsRunner = new JavaScriptRunner();
        Matrix3d<TerrainColor> world = new Matrix3d<>();

        TerrainGenerator tunnel = new TerrainGenerator("tunnel", "jumpBlocks.getAllLocations().forEach(function (location) {\n" +
                "world.setEllipse(location, 10, Java.type(\"org.sentillo.gepard.generator.terrain.TerrainColor\").BLOCK1);\n" +
                "}); world", new TerrainMetadata(null));

        accumulatedObjects.put("tunnel", tunnel);

        TerrainGenerator tGenerator = accumulatedObjects.get(terrainType);
        if(tGenerator == null) throw new RuntimeException("Terrain with name "+ terrainType + " not found!");

        if(metadata != null)
            jsRunner.bind("metadata", metadata);
        else
            jsRunner.bind("metadata", tGenerator.getDefaultMetadata());

        jsRunner.bind("jumpBlocks", jumpBlocksLayer);
        jsRunner.bind("TerrainColor", TerrainColor.class);
        jsRunner.bind("world", world);
        try {
            Object r = jsRunner.run(tunnel.getCode());
            System.out.print(r);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return world;
    }
}
