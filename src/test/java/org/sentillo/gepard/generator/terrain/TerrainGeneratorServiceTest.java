package org.sentillo.gepard.generator.terrain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.Vector3d;

import java.io.File;

public class TerrainGeneratorServiceTest {

    @Test
    public void testGenerate() {
        TerrainGeneratorService terrainGeneratorService = new TerrainGeneratorService("src" + File.separator + "test" + File.separator +"testfolder" + File.separator + "assets" + File.separator + "terrains");
        Matrix3d<McBlock> jumpBlocksLayer = new Matrix3d<>();

        jumpBlocksLayer.setObject(Vector3d.of(10,5,10), McBlock.GRASS);
        Matrix3d<TerrainColor> world = terrainGeneratorService.generate("tunnel", null, "123", new Matrix3d<>(), jumpBlocksLayer);
        System.out.println(world.getAllLocations());
        Assertions.assertEquals(TerrainColor.BLOCK1, world.getObject(Vector3d.of(10,5,10)));
    }
}
