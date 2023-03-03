package org.sentillo.gepard.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector3dDoubleTest {
    @Test
    public void distanceTest(){
        Vector3dDouble v1 = new Vector3dDouble(0, 0, 0);
        Vector3dDouble v2 = new Vector3dDouble(3, 4, 0);
        Assertions.assertEquals(5, v2.getDistance(v1));
    }
}
