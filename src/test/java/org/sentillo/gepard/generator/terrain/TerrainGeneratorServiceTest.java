package org.sentillo.gepard.generator.terrain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.Vector3d;
import org.sentillo.gepard.utils.files.FolderPlacing;

import java.io.File;

public class TerrainGeneratorServiceTest {

    @Test
    public void testGenerate() {
        TerrainGeneratorService terrainGeneratorService = new TerrainGeneratorService(FolderPlacing.TERRAIN_FOLDER);
        Matrix3d<McBlock> jumpBlocksLayer = new Matrix3d<>();

        jumpBlocksLayer.setObject(Vector3d.of(10,5,10), McBlock.GRASS);
        Matrix3d<TerrainColor> world = terrainGeneratorService.generate("Tunnel", null, "123", new Matrix3d<>(), jumpBlocksLayer);
        Assertions.assertEquals(TerrainColor.AIR, world.getObject(Vector3d.of(10,5,10)));
    }
}
