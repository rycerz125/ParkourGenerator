package org.sentillo.gepard.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Matrix3dTest {
    @Test
    public void testGetSet(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(10,10 ,20, 'A');
        Assertions.assertEquals('A', matrixes.getObject(10,10,20));
    }
    @Test
    public void testRotateLeft(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(4,0 ,5, 'A');
        matrixes.turn90Left();
        Assertions.assertEquals('A', matrixes.getObject(5,0,-4));
    }
    @Test
    public void testRotateRight(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(5,0,-4, 'A');
        matrixes.turn90Right();
        Assertions.assertEquals('A', matrixes.getObject(4,0 ,5));
    }
    @Test
    public void testMirrorX(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(-5,0,2, 'A');
        matrixes.mirrorXAxis();
        Assertions.assertEquals('A', matrixes.getObject(-5,0 ,-2));
    }
    @Test
    public void testMirrorZ(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(-5,4,1, 'A');
        matrixes.mirrorZAxis();
        Assertions.assertEquals('A', matrixes.getObject(5,4,1));
    }
    @Test
    public void setBoxTest(){
        Matrix3d<Boolean> matrix = new Matrix3d<>();
        matrix.setBox(Vector3d.of(0,0,0), Vector3d.of(5,5,5), true);
        Assertions.assertEquals(true, matrix.getObject(3, 3, 3));
    }
}
