package org.sentillo.gepard.generator.terrain.functions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RectangularSpaceInt {
    public int maxX,maxY,maxZ,minX,minY,minZ;
    public int getWidthX(){return maxX-minX;}
    public int getWidthY(){return maxY-minY;}
    public int getWidthZ(){return maxZ-minZ;}
}
