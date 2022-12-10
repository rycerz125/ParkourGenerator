package org.sentillo.gepard.jumps.jump;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JumpLoaderTest {
    
    @Test
    public void testLoadingJumps(){
        JumpLoader jumpLoader = new JumpLoader();
        List<Jump> loadedJumps = jumpLoader.load("src" + File.separator + "test" + File.separator +"testfolder" + File.separator + "assets" + File.separator + "jumps" + File.separator + "jumps");
        Assertions.assertEquals(1, loadedJumps.size());
    }

}
