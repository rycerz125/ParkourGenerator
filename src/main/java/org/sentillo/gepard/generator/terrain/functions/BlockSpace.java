package org.sentillo.gepard.generator.terrain.functions;

import lombok.AllArgsConstructor;
import org.sentillo.gepard.utils.Vector3d;

import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
public class BlockSpace {
    Set<Vector3d> blockDomain;
    RectangularSpaceInt boundaries;
    public void generateFullBlockDomain(){
        blockDomain = new HashSet<>();
        for(int x = boundaries.minX; x<= boundaries.maxX;x++)
            for(int y = boundaries.minY; y<= boundaries.maxY;y++)
                for(int z = boundaries.minZ; z<= boundaries.maxZ;z++)
                    blockDomain.add(new Vector3d(x,y,z));
    }

}
