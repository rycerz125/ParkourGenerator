package org.sentillo.gepard.generator.jumps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.sentillo.gepard.generator.jumps.jump.Jump;
import org.sentillo.gepard.utils.Matrix3d;
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
        Vector3dDouble startPoint = line.get(0);
        Vector3dDouble targetPoint = line.get(1);

        List<Jump> jumps = new ArrayList<>();
        double maxDistanceSquare = getMaxJumpDistanceSquared();

        while(notLastTarget()){
            if(startPoint.getDistanceSquared(targetPoint) <= maxDistanceSquare/4){
                targetPoint = nextTarget();
            }else{
                Jump jump = findMatchingJump(startPoint, targetPoint);
                jumps.add(jump);
                
            }

        }
        jumps.addAll(findFinalJumpLine(startPoint,targetPoint));
        return null;
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

    private List<Jump> findFinalJumpLine(Vector3dDouble startPoint, Vector3dDouble targetPoint) {
        return null;
    }

    private boolean notLastTarget() {
        return currentTargetIndex != line.size()-1;
    }

    private Jump findMatchingJump(Vector3dDouble start, Vector3dDouble target) {
        return null;
    }
}
