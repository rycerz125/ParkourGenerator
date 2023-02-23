package org.sentillo.gepard.generator.terrain;

import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.Named;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TerrainGenerator implements Named{
    @Getter
    private String name;

    @Getter
    private String code;

    @Getter
    private TerrainMetadata defaultMetadata;
}
