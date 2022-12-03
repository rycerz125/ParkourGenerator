package org.sentillo.gepard.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Matrix3dTest {
    @Test
    public void testGetSet(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(Vector3d.of(10,10 ,20), 'A');
        Assertions.assertEquals('A', matrixes.getObject(Vector3d.of(10,10,20)));
    }

    @Test
    public void testRotateLeft(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(Vector3d.of(4,0 ,5), 'A');
        matrixes.turn90Left();
        Assertions.assertEquals('A', matrixes.getObject(Vector3d.of(5,0,-4)));
    }
    @Test
    public void testRotateRight(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(Vector3d.of(5,0,-4), 'A');
        matrixes.turn90Right();
        Assertions.assertEquals('A', matrixes.getObject(Vector3d.of(4,0 ,5)));
    }
    @Test
    public void testMirrorX(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(Vector3d.of(-5,0,2), 'A');
        matrixes.mirrorXAxis();
        Assertions.assertEquals('A', matrixes.getObject(Vector3d.of(-5,0 ,-2)));
    }
    @Test
    public void testMirrorZ(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(Vector3d.of(-5,4,1), 'A');
        matrixes.mirrorZAxis();
        Assertions.assertEquals('A', matrixes.getObject(Vector3d.of(5,4,1)));
    }
    @Test
    public void setBoxTest(){
        Matrix3d<Boolean> matrix = new Matrix3d<>();
        matrix.setBox(Vector3d.of(0,0,0), Vector3d.of(5,5,5), true);
        Assertions.assertEquals(true, matrix.getObject(Vector3d.of(3, 3, 3)));
    }
    @Test
    public void placeTest(){
        Matrix3d<Character> matrix3dA = new Matrix3d<>();
        matrix3dA.setObject(Vector3d.of(1,1,0), 'a');
        matrix3dA.setObject(Vector3d.of(0,1,0), 'a');

        Matrix3d<Character> matrix3dB = new Matrix3d<>();
        matrix3dB.setObject(Vector3d.of(0,0,0), 'b');
        matrix3dB.setObject(Vector3d.of(0,0,1), 'b');

        matrix3dA.place(matrix3dB, Vector3d.of(0,1,0));

        Assertions.assertEquals(3, matrix3dA.count());
        Assertions.assertEquals('a', matrix3dA.getObject(Vector3d.of(1,1,0)));
        Assertions.assertEquals('b', matrix3dA.getObject(Vector3d.of(0,1,0)));
        Assertions.assertEquals('b', matrix3dA.getObject(Vector3d.of(0,1,1)));
    }
}
