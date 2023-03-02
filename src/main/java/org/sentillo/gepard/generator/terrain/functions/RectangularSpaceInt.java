package org.sentillo.gepard.generator.terrain.functions;

import lombok.AllArgsConstructor;
import org.sentillo.gepard.utils.Vector3d;

@AllArgsConstructor
public class RectangularSpaceInt {
    public int maxX,maxY,maxZ,minX,minY,minZ;
    public int getWidthX(){return maxX-minX;}
    public int getWidthY(){return maxY-minY;}
    public int getWidthZ(){return maxZ-minZ;}
    public boolean contains(Vector3d vector3d){
        if(vector3d.getX() < minX || vector3d.getY() < minY || vector3d.getZ() < minZ) return false;
        return vector3d.getX() <= maxX && vector3d.getY() <= maxY && vector3d.getZ() <= maxZ;
    }
}
