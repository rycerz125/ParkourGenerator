package org.sentillo.gepard.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sentillo.gepard.generator.terrain.TerrainMetadata;

@AllArgsConstructor
public class ParkourGeneratorSetup {
    @Getter
    private String jumpMapName;

    @Getter
    private String terrainGeneratorName;

    @Getter
    private String colorMapName;

    @Getter
    private TerrainMetadata terrainMetadata;
}
