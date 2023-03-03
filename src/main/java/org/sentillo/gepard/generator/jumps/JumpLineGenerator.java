package org.sentillo.gepard.generator.jumps;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sentillo.gepard.generator.jumps.jump.Jump;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.Vector3d;
import org.sentillo.gepard.utils.Vector3dDouble;

public class JumpLineGenerator {
    public List<Vector3dDouble> line;
    public Matrix3d<Boolean> restricedBlocks;
    public Set<Jump> availableJumps;

    public JumpLineGenerator(List<Vector3dDouble> line, Matrix3d<Boolean> restricedBlocks,Set<Jump> availableJumps){
        this.line = line;
        this.restricedBlocks = restricedBlocks;
        this.availableJumps = availableJumps;
    }
    private int currentTargetIndex = 0;

    public List<Jump> generate(){
        Vector3d startPoint = line.get(0).toVector3d();
        Vector3dDouble targetPoint = nextTarget();

        List<Jump> jumps = new ArrayList<>();
        double maxDistanceSquare = getMaxJumpDistanceSquared();

        while(notLastTarget()){
            if(startPoint.toVector3dDouble().getDistanceSquared(targetPoint) <= maxDistanceSquare/4){
                targetPoint = nextTarget();
            }else{
                Jump jump = findMatchingJump(startPoint, targetPoint);
                jumps.add(jump);
                restricedBlocks.place(jump.getRestrictedArea(Vector3d.zero()), startPoint);
                startPoint = jump.getStop();
            }

        }
        jumps.addAll(findFinalJumpLine(startPoint,targetPoint));
        return jumps;
    }
    private Vector3dDouble nextTarget(){
        currentTargetIndex++;
        return line.get(currentTargetIndex);
    }
    private double getMaxJumpDistanceSquared(){
        double maxDistance = 0;
        double distance;
        for(Jump jump : availableJumps){
            distance = jump.getStart().getDistanceSquared(jump.getStop());
            if(distance > maxDistance) maxDistance = distance;
        }
        return maxDistance;
    }

    private List<Jump> findFinalJumpLine(Vector3d startPoint, Vector3dDouble targetPoint) {
        return null;
    }

    private boolean notLastTarget() {
        return currentTargetIndex != line.size()-1;
    }

    private Jump findMatchingJump(Vector3d start, Vector3dDouble target) throws RuntimeException{
        Set<Jump> possibleJumps = new HashSet<>();
        for(Jump jump : availableJumps){
            if(jumpCanBePlaced(jump, start))
                possibleJumps.add(jump);
        }
        if(availableJumps.size() == 0)  throw new RuntimeException();
        for(Jump jump : possibleJumps){
            //znalezc skok ktory ma zgodny kąt lub jak wszytskie mają słaby kąt to znalezc optymalny
        }
        return null;

    }
    
    private boolean jumpCanBePlaced(Jump jump, Vector3d shift){
        Matrix3d<Boolean> jumpRestricted = jump.getRestrictedArea(shift);
        Set<Vector3d> commonVectors = new HashSet<>(jumpRestricted.getAllLocations());
        commonVectors.retainAll(restricedBlocks.getAllLocations());
        if(commonVectors.size() == 0) return true;
        for(Vector3d vector : commonVectors){
            if(jumpRestricted.getObject(vector) == true && restricedBlocks.getObject(vector) == true)
                return false;
        }
        return true;
    }
}
