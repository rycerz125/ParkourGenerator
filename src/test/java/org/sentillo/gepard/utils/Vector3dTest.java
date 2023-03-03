package org.sentillo.gepard.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector3dTest {
    @Test
    public void addTest(){
        Vector3d vector3d1 = new Vector3d(1,2,3);
        Vector3d vector3d2 = new Vector3d(1,2,3);
        Vector3d vector3d3 = vector3d1.add(vector3d2);
        Assertions.assertEquals(2, vector3d3.getX());
        Assertions.assertEquals(4, vector3d3.getY());
        Assertions.assertEquals(6, vector3d3.getZ());
    }
    @Test
    public void distanceTest(){
        Vector3d vector3d1 = new Vector3d(0,0,0);
        Vector3d vector3d2 = new Vector3d(1,2,3);
        Assertions.assertEquals(Math.sqrt(5+9), vector3d1.getDistance(vector3d2));
    }
}
