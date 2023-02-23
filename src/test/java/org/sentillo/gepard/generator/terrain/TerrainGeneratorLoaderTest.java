package org.sentillo.gepard.generator.terrain;

import java.io.File;
import java.util.HashMap;
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
        Assertions.assertEquals("Tunnel", terrains.get(0).getName());
    }
    @Test
    public void testConstructMetaData(){
        String[] lines = {
          "klucz wartosc",
          "key value",
          "asd a asdasd asd 123"
        };
        TerrainGeneratorLoader loader = new TerrainGeneratorLoader();
        HashMap<String,String> metaData = loader.constructMetaData(lines);
        Assertions.assertTrue(metaData.containsKey("klucz"));
        Assertions.assertTrue(metaData.containsKey("key"));
        Assertions.assertTrue(metaData.containsKey("asd a asdasd asd"));
        Assertions.assertEquals("wartosc", metaData.get("klucz"));
        Assertions.assertEquals("value", metaData.get("key"));
        Assertions.assertEquals("123", metaData.get("asd a asdasd asd"));
    }
}
