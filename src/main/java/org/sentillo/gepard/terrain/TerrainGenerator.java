package org.sentillo.gepard.terrain;

import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.Named;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class TerrainGenerator implements Named{
    @Getter
    private String name;

    public abstract Matrix3d<McBlock> generateTerrain();
}
