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
        matrixes.setObject(4,5 ,0, 'A');
        matrixes.turn90Left();

        Assertions.assertEquals('A', matrixes.getObject(-5,4,0));
    }
    @Test
    public void testRotateRight(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(-5,4,0, 'A');
        matrixes.turn90Right();

        Assertions.assertEquals('A', matrixes.getObject(4,5 ,0));
    }
    @Test
    public void testMirrorX(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(-5,4,0, 'A');
        matrixes.mirrorXAxis();

        Assertions.assertEquals('A', matrixes.getObject(-5,-4 ,0));
    }
    @Test
    public void testMirrorY(){
        Matrix3d<Character> matrixes = new Matrix3d<>();
        matrixes.setObject(-5,4,0, 'A');
        matrixes.mirrorYAxis();

        Assertions.assertEquals('A', matrixes.getObject(5,4,0));
    }
}
