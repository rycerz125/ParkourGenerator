package org.sentillo.gepard.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Vector3dDouble {
    public double x,y,z;
    public double getDistance(Vector3dDouble vector){
        return Math.sqrt(getDistanceSquared(vector));
    }
    public double getDistanceSquared(Vector3dDouble vector){
        return Math.pow(x-vector.x, 2) + Math.pow(y-vector.y, 2) + Math.pow(z-vector.z, 2);
    }
}
