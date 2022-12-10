package org.sentillo.gepard.jumps;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.utils.Vector3d;

public class JumpsGeneratorServiceTest {
    @Test
    public void generateSimpleMapTest(){
        JumpsGeneratorService jumpsGeneratorService = new JumpsGeneratorService("src" + File.separator + "test" + File.separator +"testfolder" + File.separator + "assets" + File.separator + "jumps");
        GeneratedJumpLayers map = jumpsGeneratorService.generateJumpLayers("only4", "test", 5);

        Assertions.assertEquals(6, map.getJumpBlocks().count());
        Assertions.assertEquals(BlockType.BLOCK, map.getJumpBlocks().getObject(Vector3d.of(0, 0, 0)));
        Assertions.assertEquals(BlockType.BLOCK, map.getJumpBlocks().getObject(Vector3d.of(5, 0, 0)));
        Assertions.assertEquals(BlockType.BLOCK, map.getJumpBlocks().getObject(Vector3d.of(10, 0, 0)));
        Assertions.assertEquals(BlockType.BLOCK, map.getJumpBlocks().getObject(Vector3d.of(15, 0, 0)));
        Assertions.assertEquals(BlockType.BLOCK, map.getJumpBlocks().getObject(Vector3d.of(20, 0, 0)));
        Assertions.assertEquals(BlockType.BLOCK, map.getJumpBlocks().getObject(Vector3d.of(25, 0, 0)));
    }
}
