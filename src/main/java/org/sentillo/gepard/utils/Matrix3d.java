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
                    this.setObject(x, y, z, obj);
                }
    }

    public long count(){
        return objects.size();
    }
}
