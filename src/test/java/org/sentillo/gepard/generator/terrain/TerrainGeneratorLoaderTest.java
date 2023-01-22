package org.sentillo.gepard.generator.terrain;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.generator.terrain.TerrainGenerator;
import org.sentillo.gepard.generator.terrain.TerrainGeneratorLoader;

public class TerrainGeneratorLoaderTest {
    
    @Test
    public void testTerrainGenerationLoader(){
        TerrainGeneratorLoader loader = new TerrainGeneratorLoader();
        List<TerrainGenerator> terrains = loader.load("src" + File.separator + "test" + File.separator +"testfolder" + File.separator + "assets" + File.separator + "terrains");
        Assertions.assertEquals("tunnel", terrains.get(0).getName());
    }
}
