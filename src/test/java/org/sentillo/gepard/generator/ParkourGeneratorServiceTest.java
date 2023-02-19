package org.sentillo.gepard.generator;

import org.junit.jupiter.api.Test;
import org.sentillo.gepard.generator.terrain.TerrainMetadata;

import java.util.HashMap;

public class ParkourGeneratorServiceTest {

    ParkourGeneratorService parkourGeneratorService = new ParkourGeneratorService();

    @Test
    public void testParkourGeneratorService(){

        var s = parkourGeneratorService.generateMap(
                new ParkourGeneratorSetup(
                        "only4",
                        "tunnel",
                        "Test",
                        new TerrainMetadata(new HashMap<>())), "0", 20);

        for(var z : s.getAllLocations()){
            System.out.println(z);
        }
    }
}
