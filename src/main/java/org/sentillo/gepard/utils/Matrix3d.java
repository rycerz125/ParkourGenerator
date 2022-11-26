package org.sentillo.gepard.utils;

import java.util.HashMap;

public class Matrix3d <T>{
    protected HashMap<Vector3d,T> objects;
    public Matrix3d() {
        objects = new HashMap<>();
    }
    public void setObject(int x, int y, int z, T object){
        objects.put(new Vector3d(x,y,z), object);
    }
    public T getObject(int x, int y, int z){
        return objects.get(new Vector3d(x,y,z));
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
}
