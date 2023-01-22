package org.sentillo.gepard.generator.jumps.jump;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.generator.jumps.jump.Jump;
import org.sentillo.gepard.generator.jumps.jump.JumpLoader;

class JumpLoaderTest {
    
    @Test
    void testLoadingJumps(){
        JumpLoader jumpLoader = new JumpLoader();
        List<Jump> loadedJumps = jumpLoader.load("src" + File.separator + "test" + File.separator +"testfolder" + File.separator + "assets" + File.separator + "jumps" + File.separator + "jumps");
        Assertions.assertEquals(2, loadedJumps.size());
    }

}
