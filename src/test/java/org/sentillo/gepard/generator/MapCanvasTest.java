package org.sentillo.gepard.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.generator.colors.ColorMap;
import org.sentillo.gepard.generator.colors.ColorMapService;
import org.sentillo.gepard.generator.jumps.BlockType;
import org.sentillo.gepard.generator.jumps.GeneratedJumpLayers;
import org.sentillo.gepard.generator.jumps.JumpsGeneratorService;
import org.sentillo.gepard.utils.BlockMatrix3d;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.Vector3d;

import java.io.File;
import java.util.List;

public class MapCanvasTest {
    @Test
    public void convertToBlockTest(){
        JumpsGeneratorService jumpsGeneratorService = new JumpsGeneratorService("src" + File.separator + "test" + File.separator +"testfolder" + File.separator + "assets" + File.separator + "jumps");
        GeneratedJumpLayers map = jumpsGeneratorService.generateJumpLayers("onlyReal4", "realTest", 5);

        Matrix3d<Boolean> mustBeAir = map.getMustBeAir();
        Assertions.assertTrue(mustBeAir.getObject(new Vector3d(1,1,0)));

        Matrix3d<McBlock> airBlockMatrix = MapCanvas.convertBooleanMatrix3dToMcBlockMatrix3d(mustBeAir);
        Assertions.assertEquals(McBlock.AIR, airBlockMatrix.getObject(new Vector3d(1,1,0)));
        Assertions.assertEquals(McBlock.AIR, airBlockMatrix.getObject(new Vector3d(11,1,0)));

    }
    @Test
    public void bakeToOneMapTest(){
        Matrix3d<McBlock> terrain = new Matrix3d<>();
        terrain.setObject(new Vector3d(50 , 50 ,0), McBlock.GRASS);

        JumpsGeneratorService jumpsGeneratorService = new JumpsGeneratorService("src" + File.separator + "test" + File.separator +"testfolder" + File.separator + "assets" + File.separator + "jumps");
        GeneratedJumpLayers map = jumpsGeneratorService.generateJumpLayers("onlyReal4", "realTest", 5);
        Matrix3d<Boolean> mustBeAir = map.getMustBeAir();
        BlockMatrix3d jumpBlocks = map.getJumpBlocks();

        ColorMapService loadedColoristics = new ColorMapService("src" + File.separator + "test" + File.separator + "testfolder" + File.separator + "assets" + File.separator + "coloristics");
        loadedColoristics.get("Test");
        ColorMap colorMap = loadedColoristics.get("Test").get();
        Matrix3d<McBlock> jumpMatrixBlocks = colorMap.convertJumpMapToBlocks(jumpBlocks);

        MapCanvas mapCanvas = new MapCanvas();
        mapCanvas.setJumpBlocksLayer(jumpMatrixBlocks);
        mapCanvas.setMustBeAirLayer(mustBeAir);
        mapCanvas.setTerrainLayer(terrain);

        Matrix3d<McBlock> entireMap = mapCanvas.bakeToOneMap();

        Assertions.assertEquals(McBlock.AIR, entireMap.getObject(new Vector3d(1,1,0)));
        Assertions.assertEquals(McBlock.AIR, entireMap.getObject(new Vector3d(11,1,0)));
        Assertions.assertEquals(McBlock.GRASS, entireMap.getObject(new Vector3d(50 , 50 ,0)));
        Assertions.assertEquals(McBlock.GRASS, entireMap.getObject(new Vector3d(0 , 0 ,0)));
        Assertions.assertEquals(McBlock.GRASS, entireMap.getObject(new Vector3d(5 , 0 ,0)));

    }
}
