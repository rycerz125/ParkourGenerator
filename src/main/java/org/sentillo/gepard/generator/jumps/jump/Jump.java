package org.sentillo.gepard.generator.jumps.jump;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    public double getStartStopToVectorAngle(Vector3dDouble vector){
        Vector3dDouble jumpVector = Vector3d.of(stop.getX() - start.getX(),
                stop.getY() - start.getY(),
                stop.getZ() - start.getZ()).toVector3dDouble();
        return jumpVector.getAngleTo(vector);
    }

}
