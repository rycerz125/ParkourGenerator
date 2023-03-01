package org.sentillo.gepard.generator.terrain.functions;

import java.util.ArrayList;
import java.util.List;

public abstract class FunctionY {
    public RectangularSpaceDouble space;
    public Grid gridXZ;
    public int resolutionPerBlock;

    public abstract double getY(double x, double z);
    public FunctionY (RectangularSpaceDouble functionSpace,RectangularSpaceInt blockDomainSpace ,int resolutionPerBlock){
        this.resolutionPerBlock = resolutionPerBlock;
        this.space = functionSpace;
        initializeGrid(blockDomainSpace);
    }

    protected void initializeGrid(RectangularSpaceInt blockDomainSpace){
        gridXZ = new Grid();
        List<Double> dimX = generateLinSpace(space.minX, space.maxX, resolutionPerBlock*blockDomainSpace.getWidthX()+1);
        List<Double> dimZ = generateLinSpace(space.minZ, space.maxZ, resolutionPerBlock*blockDomainSpace.getWidthZ()+1);
        gridXZ.dim1 = dimX;
        gridXZ.dim2 = dimZ;
    }
    protected static List<Double> generateLinSpace(double start, double end, int points){
        List<Double> linSpace = new ArrayList<>();
        if(points == 1 || points == 0) {
            linSpace.add(start);
            return linSpace;
        }
        double step = (end - start) / (points - 1);
        double currentPoint = start;
        while (linSpace.size() < points - 1){
            linSpace.add(currentPoint);
            currentPoint+= step;
        }
        linSpace.add(end);
        return linSpace;
    }

}
