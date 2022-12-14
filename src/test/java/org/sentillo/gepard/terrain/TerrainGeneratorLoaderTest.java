package org.sentillo.gepard.terrain;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TerrainGeneratorLoaderTest {
    
    @Test
    public void testTerrainGenerationLoader(){
        TerrainGeneratorLoader loader = new TerrainGeneratorLoader();
        List<TerrainGenerator> terrains = loader.load("src" + File.separator + "test" + File.separator +"testfolder" + File.separator + "assets" + File.separator + "jumps" + File.separator + "jumps");
        Assertions.assertEquals("tunnel", terrains.get(0).getName());
    }
}
