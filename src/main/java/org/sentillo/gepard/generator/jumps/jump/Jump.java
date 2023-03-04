package org.sentillo.gepard.generator.jumps.jump;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import org.sentillo.gepard.utils.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jump implements Named{
    @Getter
    private String name;

    @Getter
    @Builder.Default
    private Vector3d start = new Vector3d(0, 0, 0);

    @Getter
    private Vector3d stop;

    @Getter
    @Builder.Default
    private BlockMatrix3d visibleLayer = new BlockMatrix3d();

    @Getter
    @Builder.Default
    private Matrix3d<Boolean> mustEmptyLayer = new Matrix3d<>();
    
    @Getter
    @Builder.Default
    private Matrix3d<Boolean> couldEmptyLayer = new Matrix3d<>();

    public Matrix3d<Boolean> getRestrictedArea(Vector3d shift){
        Matrix3d<Boolean> layers = new Matrix3d<>();
        layers.place(mustEmptyLayer,shift);
        layers.place(couldEmptyLayer, shift);
        for(Vector3d vector : visibleLayer.getAllLocations()){
            layers.setObject(vector.add(shift), true);
        }
        return layers;
    }
    public Matrix3d<Boolean> getRestrictedAreaOnlyTrueAndInverted(Vector3d shift){
        Matrix3d<Boolean> restricted = getRestrictedArea(shift);
        for(Vector3d vector : restricted.getAllLocations()){
            if(!restricted.getObject(vector)) {
                restricted.remove(vector);
            }else{
                restricted.setObject(vector, false);
            }
        }
        return restricted;
    }
    public double getStartStopToVectorAngle(Vector3dDouble vector){
        Vector3dDouble jumpVector = getStartStopVector().toVector3dDouble();
        return jumpVector.getAngleTo(vector);
    }
    public Vector3d getStartStopVector(){
        return Vector3d.of(stop.getX() - start.getX(),
        stop.getY() - start.getY(),
        stop.getZ() - start.getZ());
    }
    public boolean collidesWithNext(Jump jump){
        Matrix3d<Boolean> nextJumpRestricted = jump.getRestrictedArea(this.getStartStopVector());
        return collidesWithArea(nextJumpRestricted, Vector3d.zero());
    }
    public boolean collidesWithArea(Matrix3d<Boolean> restrictedBlocks,Vector3d jumpStartShift){
        Matrix3d<Boolean> jumpRestricted = getRestrictedArea(jumpStartShift);
        Set<Vector3d> commonVectors = new HashSet<>(jumpRestricted.getAllLocations());
        commonVectors.retainAll(restrictedBlocks.getAllLocations());
        if(commonVectors.size() == 0) return false;
        for(Vector3d vector : commonVectors){
            if(jumpRestricted.getObject(vector) && restrictedBlocks.getObject(vector))
                return true;
        }
        return false;
    }

}
