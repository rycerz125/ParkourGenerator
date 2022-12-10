package org.sentillo.gepard.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class GeneratorSetup {
    @Getter
    private String jumpMapName;

    @Getter
    private String terrainGeneratorName;

    @Getter
    private String colorMapName;
}
