package org.sentillo.gepard.generator.terrain.functions;

import lombok.AllArgsConstructor;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.Vector2d;
import org.sentillo.gepard.utils.Vector3d;
import org.sentillo.gepard.utils.Vector3dDouble;

import java.util.ArrayList;
import java.util.List;

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
            if(Math.pow(relatedPoint.x - block.x, 2) + //skompensowac rozciagniecia jeszcze trzeba...
                    Math.pow(relatedPoint.y - block.y, 2) + // bo gdy szerokosci przestrzeni są inne to odpowiednio grubosc w danym wymiarze też
                    Math.pow(relatedPoint.z - block.z, 2) <= // prawdopodobnie trzeba zrobic jakies dzialanie *width/width na dwoch z tych różnic
                    Math.pow(thickInBlocksToThickInFunction(surfaceThick),2))
                return true;
        }
        return false;
    }
    protected double thickInBlocksToThickInFunction(double blockThick){
        return (blockThick /(double) blockSpace.boundaries.getWidthY())   *  functionY.space.getWidthY();
    }

    public Matrix3d<Boolean> generateMatrix(double surfaceThick){
        Matrix3d<Boolean> generatedBlocks = new Matrix3d<>();
        for(Vector3d blockVector : blockSpace.blockDomain){
            generatedBlocks.setObject(blockVector, blockIsCloseToFunctionSurface(blockVector,surfaceThick));
        }
        return generatedBlocks;
    }
}
