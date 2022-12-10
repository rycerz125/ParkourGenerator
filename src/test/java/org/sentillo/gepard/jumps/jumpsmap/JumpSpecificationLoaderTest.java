package org.sentillo.gepard.jumps.jumpsmap;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.jumps.jump.JumpService;

public class JumpSpecificationLoaderTest {

    @Test
    public void loadJumpSpecificationTest(){
        JumpService jumpService = new JumpService("src" + File.separator + "test" + File.separator +"testfolder" + File.separator + "assets" + File.separator + "jumps" + File.separator + "jumps");
        JumpsMapLoader jumpMapLoader = new JumpsMapLoader(jumpService);
        List<JumpsMap> loadedJumpMaps = jumpMapLoader.load("src" + File.separator + "test" + File.separator +"testfolder" + File.separator + "assets" + File.separator + "jumps" + File.separator + "jumpsmap");
        Assertions.assertEquals(1, loadedJumpMaps.size());
    }
    
}
