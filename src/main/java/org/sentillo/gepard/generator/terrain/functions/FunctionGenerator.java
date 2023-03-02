package org.sentillo.gepard.generator.terrain.functions;

import lombok.AllArgsConstructor;
import org.sentillo.gepard.utils.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class FunctionGenerator {
    public BlockSpace blockSpace;
    public FunctionY functionY;
    protected Vector2d blockCoordsToGridIndexes(Vector3d vector3d){
        int resolutionMultiplier = functionY.resolutionPerBlock;
        return new Vector2d((vector3d.getX() - blockSpace.boundaries.minX)*resolutionMultiplier,
                (vector3d.getZ() - blockSpace.boundaries.minZ)*resolutionMultiplier);
    }
    protected double blockYToFunctionDomain(int blockY){
        return functionY.space.minY + functionY.space.getWidthY() *    (blockY -blockSpace.boundaries.minY) / blockSpace.boundaries.getWidthY();
    }
    protected Vector3dDouble blockCoordInFunctionDomain(Vector3d vector3d){
        Vector2d xz = blockCoordsToGridIndexes(vector3d);
        double X = functionY.gridXZ.dim1.get(xz.v1);
        double Z = functionY.gridXZ.dim2.get(xz.v2);
        double Y = blockYToFunctionDomain(vector3d.getY());
        return new Vector3dDouble(X,Y,Z);
    }

    protected List<Vector3dDouble> getRelatedPointsInFunctionDomain(Vector3d blockCoords, double surfaceThick){
        Vector2d x0z0GridIndexes = blockCoordsToGridIndexes(blockCoords);
        int x0 = x0z0GridIndexes.v1;
        int z0 = x0z0GridIndexes.v2;
        int radius = (int)(surfaceThick* functionY.resolutionPerBlock);
        List<Vector3dDouble> relatedPoints = new ArrayList<>();
        for(int dx = -radius; dx<=radius; dx++){
            int actualXIndex = x0+dx;
            for(int dz = -radius; dz<=radius;dz++){
                int actualZIndex = z0+dz;
                if(functionY.gridXZ.containsIndexes(actualXIndex,actualZIndex)) {
                    double xInFunctionDomain = functionY.gridXZ.dim1.get(actualXIndex);
                    double zInFunctionDomain = functionY.gridXZ.dim2.get(actualZIndex);
                    double y = functionY.getY(xInFunctionDomain,zInFunctionDomain);
                    relatedPoints.add(new Vector3dDouble(xInFunctionDomain,y, zInFunctionDomain));
                }
            }
        }
        return relatedPoints;
    }
    protected boolean blockIsCloseToFunctionSurface(Vector3d blockVector, double surfaceThick){
        Vector3dDouble block = blockCoordInFunctionDomain(blockVector);
        for(Vector3dDouble relatedPoint : getRelatedPointsInFunctionDomain(blockVector,surfaceThick)){
            if(Math.pow((relatedPoint.x - block.x)* blockSpace.boundaries.getWidthX()/functionY.space.getWidthX(), 2) +
                    Math.pow((relatedPoint.y - block.y)*blockSpace.boundaries.getWidthY()/functionY.space.getWidthY(), 2) +
                    Math.pow((relatedPoint.z - block.z)*blockSpace.boundaries.getWidthZ()/functionY.space.getWidthZ(), 2)<=
                    Math.pow(surfaceThick,2))
                return true;
        }
        return false;
    }

    protected Vector3dDouble functionToBlockDomain(Vector3dDouble functionVector){
        double X = (functionVector.x-functionY.space.minX)*blockSpace.boundaries.getWidthX()/functionY.space.getWidthX() + blockSpace.boundaries.minX;
        double Y = (functionVector.y-functionY.space.minY)*blockSpace.boundaries.getWidthY()/functionY.space.getWidthY() + blockSpace.boundaries.minY;
        double Z = (functionVector.z-functionY.space.minZ)*blockSpace.boundaries.getWidthZ()/functionY.space.getWidthZ() + blockSpace.boundaries.minZ;
        return new Vector3dDouble(X,Y,Z);
    }
    protected Set<Vector3d> getBlocksNearPoint(Vector3dDouble pointInBlockDomain,double blockThick){
        Vector3dDouble fixedByHalf = new Vector3dDouble(pointInBlockDomain.x+0.5, pointInBlockDomain.y+0.5, pointInBlockDomain.z+0.5);
        int range = ((int)blockThick) + 1;
        Set<Vector3d> blocksNear = new HashSet<>();
        for(int dx = -range; dx <= range; dx++){
            int actualX = (int)(fixedByHalf.x + dx);
            for(int dy = -range; dy <= range; dy++){
                int actualY = (int)(fixedByHalf.y + dy);
                for(int dz = -range; dz <= range; dz++){
                    int actualZ = (int)(fixedByHalf.z + dz);
                    Vector3d currentVector = new Vector3d(actualX,actualY,actualZ);
                    if(blockSpace.boundaries.contains(currentVector) && blockSpace.blockDomain.contains(currentVector))
                        blocksNear.add(currentVector);
                }
            }
        }
        return blocksNear;
    }
    protected boolean blockIsCloseToPoint(Vector3d block, Vector3dDouble pointInBlockDomain, double thick){
        return Math.pow(block.getX() - pointInBlockDomain.x,2) +
                Math.pow(block.getY() - pointInBlockDomain.y,2) +
                Math.pow(block.getZ() - pointInBlockDomain.z,2) <=
                Math.pow(thick,2);
    }

    protected Matrix3d<Boolean> generateMatrixBlockIterate(double surfaceThick){
        Matrix3d<Boolean> generatedBlocks = new Matrix3d<>();
        for(Vector3d blockVector : blockSpace.blockDomain){
            if(blockIsCloseToFunctionSurface(blockVector,surfaceThick))
                generatedBlocks.setObject(blockVector, true);
        }
        return generatedBlocks;
    }
    protected Matrix3d<Boolean> generateMatrixGridIterate(double surfaceThick){
        Matrix3d<Boolean> generatedBlocks = new Matrix3d<>();
        for(double xGrid : functionY.gridXZ.dim1){
            for(double zGrid : functionY.gridXZ.dim2){
                double y = functionY.getY(xGrid,zGrid);
                Vector3dDouble functionPoint = functionToBlockDomain(new Vector3dDouble(xGrid,y,zGrid));
                for(Vector3d block : getBlocksNearPoint(functionPoint,surfaceThick)){
                    if(!generatedBlocks.getAllLocations().contains(block))
                        if(blockIsCloseToPoint(block, functionPoint, surfaceThick))
                            generatedBlocks.setObject(block, true);
                }
            }
        }
        return generatedBlocks;
    }
    public Matrix3d<Boolean> generateMatrix(double surfaceThick){
        return generateMatrixBlockIterate(surfaceThick); // + 10x faster
    }
}
