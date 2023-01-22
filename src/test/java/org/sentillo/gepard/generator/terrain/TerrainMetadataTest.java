package org.sentillo.gepard.generator.terrain;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.generator.terrain.TerrainMetadata;

public class TerrainMetadataTest {
    
    @Test
    public void getValueTest(){
        HashMap<String, String> me = new HashMap<>();
        me.put("circleSize", "7");
        TerrainMetadata metadata = new TerrainMetadata(me);
        Assertions.assertEquals("7", metadata.getValue("circleSize"));
    }
}
