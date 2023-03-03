package org.sentillo.gepard.utils;

import org.bukkit.util.Vector;

public class Vector3d {
    int x,y,z;
    public Vector3d(int x,int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getZ(){
        return z;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setZ(int z){
        this.z = z;
    }

    @Override
    public int hashCode() {
        return x+y+z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3d vector3d = (Vector3d) o;
        return x == vector3d.x && y == vector3d.y && z == vector3d.z;
    }

    public Vector3d add(Vector3d vector3dToAdd){
        return of(x + vector3dToAdd.x,
                  y + vector3dToAdd.y,
                  z + vector3dToAdd.z);
    }

    public static Vector3d of(int x, int y, int z){
        return new Vector3d(x,y,z);
    }

    public static Vector3d zero(){
        return Vector3d.of(0, 0, 0);
    }

    public Vector3d copy(){
        return Vector3d.of(x, y, z);
    }
    public Vector toVector(){
        return new Vector(x,y,z);
    }

    public double getDistance(Vector3d vector){
        return Math.sqrt(getDistanceSquared(vector));
    }
    public double getDistanceSquared(Vector3d vector){
        return Math.pow(x-vector.x, 2) + Math.pow(y-vector.y, 2) + Math.pow(z-vector.z, 2);
    }
}
