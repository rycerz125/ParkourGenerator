package org.sentillo.gepard.jumps;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JumpsGeneratorServiceTest {
    @Test
    public void generateSimpleMapTest(){
        JumpsGeneratorService jumpsGeneratorService = new JumpsGeneratorService("testfolder" + File.separator + "assets" + File.separator + "jumps");
        GeneratedJumpLayers map = jumpsGeneratorService.generateJumpLayers("test", "test", 5);
        Assertions.assertEquals(6, map.getJumpBlocks().count());
    }
}
