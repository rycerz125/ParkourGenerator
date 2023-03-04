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
    public double getLength(){
        return getDistance(new Vector3dDouble(0,0,0));
    }
    public Vector3d toVector3d(){
        return new Vector3d((int)x, (int)y,(int) z);
    }
    public double getAngleTo(Vector3dDouble vector){
        if(this.isZero() || vector.isZero()) return -1000;
        return Math.acos(
            getDotProductWith(vector)/(this.getLength()*vector.getLength())
        );
    }
    public double getDotProductWith(Vector3dDouble vector){
        return x* vector.x + y* vector.y + z* vector.z;
    }
    public boolean isZero(){
        return x==0.0 && y==0.0 && z==0.0;
    }
    public Vector3dDouble subtract(Vector3dDouble vector){
        return new Vector3dDouble(x - vector.x,y - vector.y, z - vector.z);
    }
}
