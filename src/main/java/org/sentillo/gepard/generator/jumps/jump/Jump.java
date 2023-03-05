package org.sentillo.gepard.generator.jumps.jump;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.block.Block;
import org.sentillo.gepard.generator.jumps.BlockType;
import org.sentillo.gepard.utils.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jump implements Named{
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Builder.Default
    private Vector3d start = new Vector3d(0, 0, 0);

    @Getter
    @Setter
    private Vector3d stop;

    @Getter
    @Setter
    @Builder.Default
    private BlockMatrix3d visibleLayer = new BlockMatrix3d();

    @Getter
    @Setter
    @Builder.Default
    private Matrix3d<Boolean> mustEmptyLayer = new Matrix3d<>();
    
    @Getter
    @Setter
    @Builder.Default
    private Matrix3d<Boolean> couldEmptyLayer = new Matrix3d<>();

    public BlockMatrix3d getRestrictedArea(Vector3d shift){
        BlockMatrix3d layers = new BlockMatrix3d();
        for(Vector3d  vector : mustEmptyLayer.getAllLocations()){
            if(mustEmptyLayer.getObject(vector))
            layers.setObject(vector.add(shift), BlockType.AIR);
        }
        for(Vector3d  vector : couldEmptyLayer.getAllLocations()){
            if(couldEmptyLayer.getObject(vector))
            layers.setObject(vector.add(shift), BlockType.AIR);
        }
        layers.place(visibleLayer, shift);
        return layers;
    }
    // public BlockMatrix3d getRestrictedAreaOnlyTrueAndInverted(Vector3d shift){
    //     BlockMatrix3d restricted = getRestrictedArea(shift);
    //     for(Vector3d vector : restricted.getAllLocations()){
    //         if(!restricted.getObject(vector)) {
    //             restricted.remove(vector);
    //         }else{
    //             restricted.setObject(vector, false);
    //         }
    //     }
    //     return restricted;
    // }
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
        BlockMatrix3d nextJumpRestricted = jump.getRestrictedArea(this.getStartStopVector());
        return collidesWithArea(nextJumpRestricted, Vector3d.zero());
    }
    public boolean collidesWithArea(BlockMatrix3d restrictedBlocks,Vector3d jumpStartShift){
        BlockMatrix3d jumpRestricted = getRestrictedArea(jumpStartShift);
        Set<Vector3d> commonVectors = new HashSet<>(jumpRestricted.getAllLocations());
        commonVectors.retainAll(restrictedBlocks.getAllLocations());
        if(commonVectors.size() == 0) return false;
        for(Vector3d vector : commonVectors){
            if(jumpRestricted.getObject(vector) != restrictedBlocks.getObject(vector))
                return true;
        }
        return false;
    }
    public List<Jump> getAllDirections(){
        List<Jump> jumps = new ArrayList<>();
        Jump localJump = this.clone();
        jumps.add(this);
        for (int index = 0; index < 3; index++) {
            localJump.rotateJump90Left();
            jumps.add(localJump.clone());
        }
        localJump.mirrorX();
        jumps.add(localJump.clone());
        for (int index = 0; index < 3; index++) {
            localJump.rotateJump90Left();
            jumps.add(localJump.clone());
        }
        return jumps;
    }
    public void rotateJump90Left(){
        couldEmptyLayer.turn90Left();
        mustEmptyLayer.turn90Left();
        visibleLayer.turn90Left();
        setStart(start.get90LeftTurned());
        setStop(stop.get90LeftTurned());
    }
    public void mirrorX(){
        couldEmptyLayer.mirrorXAxis();
        mustEmptyLayer.mirrorXAxis();
        visibleLayer.mirrorXAxis();
        setStart(start.getMirroredX());
        setStop(stop.getMirroredX());
    }
    public Jump clone(){
        Jump jump = new Jump();
        jump.setCouldEmptyLayer(couldEmptyLayer.copy());
        jump.setMustEmptyLayer(mustEmptyLayer.copy());
        jump.setName(name);
        jump.setStart(start.copy());
        jump.setStop(stop.copy());
        jump.setVisibleLayer(visibleLayer.copy());
        return jump;
    }

}
