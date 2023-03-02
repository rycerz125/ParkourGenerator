package org.sentillo.gepard.generator.terrain.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.Vector3d;
import org.sentillo.gepard.utils.Vector3dDouble;

import java.util.HashSet;
import java.util.List;

public class FunctionGeneratorTest {
    @Test
    public void blockYToFunctionDomainTest(){
        BlockSpace blockSpace = new BlockSpace(new HashSet<>(), new RectangularSpaceInt(10,10,3,2,2,2));

        FunctionY functionY = new FunctionY(new RectangularSpaceDouble(2,2,2,1,1,1),
                blockSpace.boundaries, 4) {
            @Override
            public double getY(double x, double z) {
                return 0;
            }
        };
        FunctionGenerator functionGenerator = new FunctionGenerator(blockSpace,functionY);
        Assertions.assertEquals(1.75,functionGenerator.blockYToFunctionDomain(8));
    }
    @Test
    public void blockIsCloseTest(){
        BlockSpace blockSpace = new BlockSpace(new HashSet<>(), new RectangularSpaceInt(10,10,10,0,0,0));

        FunctionY functionY = new FunctionY(new RectangularSpaceDouble(1,1,1,0,0,0),
                blockSpace.boundaries, 3) {
            @Override
            public double getY(double x, double z) {
                return x+z;
            }
        };
        FunctionGenerator functionGenerator = new FunctionGenerator(blockSpace,functionY);
        Vector3d vectorClose = new Vector3d(5,10, 6);
        Vector3d vectorNotClose = new Vector3d(6, 10, 6);

        double thick = 1;
        List<Vector3dDouble> related = functionGenerator.getRelatedPointsInFunctionDomain(vectorClose, thick);
        for(Vector3dDouble vector3dDouble : related)
            System.out.println(vector3dDouble.x + "   " + vector3dDouble.y + "  " + vector3dDouble.z);
        Assertions.assertTrue(functionGenerator.blockIsCloseToFunctionSurface(vectorClose, thick));
        Assertions.assertFalse(functionGenerator.blockIsCloseToFunctionSurface(vectorNotClose, thick));
    }
    @Test
    public void getRelatedPointsTest(){
        BlockSpace blockSpace = new BlockSpace(new HashSet<>(), new RectangularSpaceInt(10,20,30,2,0,-10));

        FunctionY functionY = new FunctionY(new RectangularSpaceDouble(2,2,2,1,1,1),
                blockSpace.boundaries, 3) {
            @Override
            public double getY(double x, double z) {
                return (x+z)/2;
            }
        };
        FunctionGenerator functionGenerator = new FunctionGenerator(blockSpace,functionY);
        Vector3d vector = new Vector3d(4,10, 20);
        List<Vector3dDouble> related = functionGenerator.getRelatedPointsInFunctionDomain(vector, 1.5);
        for(Vector3dDouble vector3dDouble : related)
            System.out.println(vector3dDouble.x + "   " + vector3dDouble.y + "  " + vector3dDouble.z);
        Assertions.assertEquals(81, related.size());
    }

    @Test
    public void blockCoordInFunctionDomainTest(){
        BlockSpace blockSpace = new BlockSpace(new HashSet<>(), new RectangularSpaceInt(10,20,30,2,0,-10));

        FunctionY functionY = new FunctionY(new RectangularSpaceDouble(2,2,2,1,1,1),
                blockSpace.boundaries, 3) {
            @Override
            public double getY(double x, double z) {
                return 0;
            }
        };
        FunctionGenerator functionGenerator = new FunctionGenerator(blockSpace,functionY);
        Vector3dDouble vector = functionGenerator.blockCoordInFunctionDomain(new Vector3d(3,10, 20));
        System.out.println(vector.x +"  "+vector.y +"  "+vector.z);
        System.out.println(functionY.gridXZ.dim1.size());
        System.out.println(functionY.gridXZ.dim1);


        Assertions.assertEquals(1.1250000000000002,vector.x);
        Assertions.assertEquals(1.5,vector.y);
        Assertions.assertEquals(1.7499999999999973,vector.z);
    }
    @Test
    public void generateMatrixIterateGridTest(){
        BlockSpace blockSpace = new BlockSpace(new HashSet<>(), new RectangularSpaceInt(10,10,10,0,0,0));
        blockSpace.generateFullBlockDomain();
        FunctionY functionY = new FunctionY(new RectangularSpaceDouble(1,1,1,0,0,0),
                blockSpace.boundaries, 3) {
            @Override
            public double getY(double x, double z) {
                return x+z;
            }
        };
        FunctionGenerator functionGenerator = new FunctionGenerator(blockSpace,functionY);
        Matrix3d<Boolean> generated = functionGenerator.generateMatrixGridIterate(1.2);
        for(int x = blockSpace.boundaries.minX ; x <= blockSpace.boundaries.maxX ; x++) {
            System.out.println();
            for (int y = blockSpace.boundaries.minY; y <= blockSpace.boundaries.maxY; y++) {
                System.out.println();
                for (int z = blockSpace.boundaries.minZ; z <= blockSpace.boundaries.maxZ; z++)
                    System.out.print(generated.getObject(new Vector3d(x, y, z)) + "  ");
            }
        }
    }
    @Test
    public void checkEqualityOfGenerations(){
        BlockSpace blockSpace = new BlockSpace(new HashSet<>(), new RectangularSpaceInt(20,20,20,0,0,0));
        blockSpace.generateFullBlockDomain();
        FunctionY functionY = new FunctionY(new RectangularSpaceDouble(1,1,1,0,0,0),
                blockSpace.boundaries, 3) {
            @Override
            public double getY(double x, double z) {
                return x*x+z*z;
            }
        };
        FunctionGenerator functionGenerator = new FunctionGenerator(blockSpace,functionY);

        long tic = System.currentTimeMillis();
        Matrix3d<Boolean> generatedByGrid = functionGenerator.generateMatrixGridIterate(1.2);
        System.out.println("po siatce[ms]: " +  (System.currentTimeMillis()-tic));

        tic = System.currentTimeMillis();
        Matrix3d<Boolean> generatedByBlocks = functionGenerator.generateMatrixBlockIterate(1.2);
        System.out.println("po blokach[ms]: " +  (System.currentTimeMillis()-tic));
        for(int x = blockSpace.boundaries.minX ; x <= blockSpace.boundaries.maxX ; x++) {
            for (int y = blockSpace.boundaries.minY; y <= blockSpace.boundaries.maxY; y++) {
                for (int z = blockSpace.boundaries.minZ; z <= blockSpace.boundaries.maxZ; z++){
                    Vector3d vector3d =  new Vector3d(x,y,z);
                    Assertions.assertEquals(generatedByGrid.getObject(vector3d) ,generatedByBlocks.getObject(vector3d));
                }
            }
        }
    }

}
