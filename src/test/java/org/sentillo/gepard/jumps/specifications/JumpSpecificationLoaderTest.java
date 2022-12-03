package org.sentillo.gepard.jumps.specifications;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.jumps.jump.JumpService;

public class JumpSpecificationLoaderTest {

    @Test
    public void loadJumpSpecificationTest(){
        JumpService jumpService = new JumpService("testfolder" + File.separator + "assets" + File.separator + "jumps" + File.separator + "jumps");
        JumpMapLoader jumpMapLoader = new JumpMapLoader(jumpService);
        List<JumpsMap> loadedJumpMaps = jumpMapLoader.loadJumpSpecifications("testfolder" + File.separator + "assets" + File.separator + "jumps" + File.separator + "specifications");
        Assertions.assertEquals(1, loadedJumpMaps.size());
    }
    
}
