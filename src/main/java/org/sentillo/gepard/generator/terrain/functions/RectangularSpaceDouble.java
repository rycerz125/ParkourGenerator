package org.sentillo.gepard.generator.terrain.functions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RectangularSpaceDouble {
    public double maxX,maxY,maxZ,minX,minY,minZ;
    public double getWidthX(){return maxX-minX;}
    public double getWidthY(){return maxY-minY;}
    public double getWidthZ(){return maxZ-minZ;}
}
