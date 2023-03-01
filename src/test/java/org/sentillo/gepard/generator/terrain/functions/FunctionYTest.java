package org.sentillo.gepard.generator.terrain.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FunctionYTest {
    @Test
    public void linSpaceTest(){
        List<Double> linSpace = FunctionY.generateLinSpace(2,3,3);
        Assertions.assertEquals(3, linSpace.size());
        List<Double> linSpace2 = FunctionY.generateLinSpace(1,1.4,2);
        Assertions.assertEquals(2, linSpace2.size());

        System.out.println(linSpace);
    }
    @Test
    public void initTest(){
        RectangularSpaceDouble functionSpace = new RectangularSpaceDouble(0,0,0,1,1,1);
        RectangularSpaceInt blockSpace = new RectangularSpaceInt(12,12,12,4,4,4);
        FunctionY functionY = new FunctionY(functionSpace,blockSpace, 4) {
            @Override
            public double getY(double x, double z) {
                return 0;
            }
        };
        Assertions.assertEquals(32, functionY.gridXZ.dim1.size());
        Assertions.assertEquals(32, functionY.gridXZ.dim2.size());

        System.out.println(functionY.gridXZ.dim1);
        System.out.println(functionY.gridXZ.dim2);

    }
}
