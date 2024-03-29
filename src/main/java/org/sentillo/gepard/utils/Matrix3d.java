package org.sentillo.gepard.utils;

import java.util.HashMap;
import java.util.Set;

public class Matrix3d <T>{
    protected HashMap<Vector3d,T> objects;
    public Matrix3d() {
        objects = new HashMap<>();
    }
    public void setObject(Vector3d vector3d, T object){
        objects.put(vector3d, object);
    }
    public T getObject(Vector3d vector3d){
        return objects.get(vector3d);
    }
    public void remove(Vector3d vector3d){
        objects.remove(vector3d);
    }

    public void turn90Left(){
        HashMap<Vector3d,T> turned = new HashMap<>();
        for(Vector3d vector3d: objects.keySet()){
            Vector3d turnedVector3d = new Vector3d(vector3d.getZ(), vector3d.getY(),- vector3d.getX());
            turned.put(turnedVector3d, objects.get(vector3d));
        }
        objects = turned;
    }
    public void turn90Right(){
        HashMap<Vector3d,T> turned = new HashMap<>();
        for(Vector3d vector3d: objects.keySet()){
            Vector3d turnedVector3d = new Vector3d(-vector3d.getZ(),vector3d.getY(), vector3d.getX());
            turned.put(turnedVector3d, objects.get(vector3d));
        }
        objects = turned;
    }
    public void mirrorXAxis(){
        HashMap<Vector3d,T> turned = new HashMap<>();
        for(Vector3d vector3d: objects.keySet()){
            Vector3d turnedVector3d = new Vector3d(vector3d.getX(), vector3d.getY(),- vector3d.getZ());
            turned.put(turnedVector3d, objects.get(vector3d));
        }
        objects = turned;
    }
    public void mirrorZAxis(){
        HashMap<Vector3d,T> turned = new HashMap<>();
        for(Vector3d vector3d: objects.keySet()){
            Vector3d turnedVector3d = new Vector3d(-vector3d.getX(), vector3d.getY(), vector3d.getZ());
            turned.put(turnedVector3d, objects.get(vector3d));
        }
        objects = turned;
    }

    public void setBox(Vector3d a, Vector3d b, T obj){
        int x1 = (a.getX() < b.getX() ? a.getX() : b.getX());
        int x2 = (a.getX() < b.getX() ? b.getX() : a.getX());

        int y1 = (a.getY() < b.getY() ? a.getY() : b.getY());
        int y2 = (a.getY() < b.getY() ? b.getY() : a.getY());

        int z1 = (a.getZ() < b.getZ() ? a.getZ() : b.getZ());
        int z2 = (a.getZ() < b.getZ() ? b.getZ() : a.getZ());

        for(int x = x1;x <= x2; x++)
            for(int y= y1;y <= y2; y++)
                for(int z= z1;z <= z2; z++){
                    this.setObject(new Vector3d(x, y, z), obj);
                }
    }

    public void setEllipse(Vector3d a, int radius, T obj){
        for(int x = -radius;x <= radius; x++)
            for(int y= -radius;y <= radius; y++)
                for(int z= -radius;z <= radius; z++){
                    if(Math.sqrt(x*x+y*y+z*z) <= 1.0d* radius)
                        this.setObject(new Vector3d(x+a.getX(), y+a.getY(), z+a.getZ()), obj);
                }
    }

    public void place(Matrix3d<T> matrixToPlace, Vector3d shift){
        for(Vector3d vector3d : matrixToPlace.objects.keySet()){
            objects.put(vector3d.add(shift), matrixToPlace.getObject(vector3d));
        }
    }
    public Matrix3d<T> copy(){
        Matrix3d<T> matrix3d = new Matrix3d<>();
        matrix3d.place(this, Vector3d.zero());
        return matrix3d;
    }

    public long count(){
        return objects.size();
    }

    public Set<Vector3d> getAllLocations(){
        return objects.keySet();
    }
}
