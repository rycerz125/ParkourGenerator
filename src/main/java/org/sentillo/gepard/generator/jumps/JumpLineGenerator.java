package org.sentillo.gepard.generator.jumps;

import java.util.*;

import org.sentillo.gepard.generator.jumps.jump.Jump;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.Vector3d;
import org.sentillo.gepard.utils.Vector3dDouble;

public class JumpLineGenerator {
    public List<Vector3dDouble> line;
    public Matrix3d<Boolean> restrictedBlocks;
    public Set<Jump> availableJumps;
    private Random randomGenerator;

    public JumpLineGenerator(List<Vector3dDouble> line, Matrix3d<Boolean> restrictedBlocks, Set<Jump> availableJumps){
        this.line = line;
        this.restrictedBlocks = restrictedBlocks;
        this.availableJumps = availableJumps;
        randomGenerator = new Random();

    }
    public JumpLineGenerator(List<Vector3dDouble> line, Matrix3d<Boolean> restrictedBlocks, Set<Jump> availableJumps, long seed){
        this.line = line;
        this.restrictedBlocks = restrictedBlocks;
        this.availableJumps = availableJumps;
        randomGenerator = new Random(seed);
    }
    private int currentTargetIndex = 0;

    public List<Jump> generate(double angleSuggestion){
        Vector3d startPoint = line.get(0).toVector3d();
        Vector3dDouble targetPoint = nextTarget();

        List<Jump> jumps = new ArrayList<>();
        double maxDistanceSquare = getMaxJumpDistanceSquared();

        while(notLastTarget()){
            if(startPoint.toVector3dDouble().getDistanceSquared(targetPoint) <= maxDistanceSquare/4){
                targetPoint = nextTarget();
            }else{
                Jump jump = findMatchingJump(startPoint, targetPoint,angleSuggestion);
                jumps.add(jump);
                restrictedBlocks.place(jump.getRestrictedArea(Vector3d.zero()), startPoint);
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
    protected double getMaxJumpDistanceSquared(){
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

    protected Jump findMatchingJump(Vector3d start, Vector3dDouble target, double angleSuggestion) throws RuntimeException{
        Set <Jump> placeableJumps = findPlaceableJumps(start);
        if(placeableJumps.size() == 0)
            throw new RuntimeException("Cannot find next jump because all of them have a conflict with the terrain or with other placed jumps");
        //najpierw iteracja i znalezienie najmniejszego kąta razem ze stworzeniem setu skoków w malym kącie
        //potem jezeli najmniejszy kąt > angleSuggestion to zwracamy ten skok
        //gdy to nie nastapi to losujemy z setu wynik

        Set<Jump> consistentAngleJumps = new HashSet<>();

        double minAngle = Math.PI+0.01;
        Jump bestAngleJump = null;
        for(Jump jump : placeableJumps){
            double currentAngle = jump.getStartStopToVectorAngle(target.subtract(start.toVector3dDouble()));
            if(currentAngle < minAngle) {
                minAngle = currentAngle;
                bestAngleJump = jump;
            }
            if(currentAngle < angleSuggestion) consistentAngleJumps.add(jump);
        }
        if(consistentAngleJumps.size() == 0)
            return bestAngleJump;
        return drawJumpFrom(consistentAngleJumps);
    }
    protected Jump drawJumpFrom(Set<Jump> jumps){ // gets random jump from set, zrobic to z seedem
        Jump[] arrayJumps = jumps.toArray(new Jump[jumps.size()]);
        // this will generate a random number between 0 and
        // HashSet.size - 1
        int rndmNumber = randomGenerator.nextInt(jumps.size());
        return arrayJumps[rndmNumber];
    }
    private Set<Jump> findPlaceableJumps(Vector3d start){
        Set<Jump> placeableJumps = new HashSet<>(availableJumps);
        placeableJumps.removeIf((jump -> !jumpCanBePlaced(jump, start)));
        return placeableJumps;
    }
    
    protected boolean jumpCanBePlaced(Jump jump, Vector3d shift){
        Matrix3d<Boolean> jumpRestricted = jump.getRestrictedArea(shift);
        Set<Vector3d> commonVectors = new HashSet<>(jumpRestricted.getAllLocations());
        commonVectors.retainAll(restrictedBlocks.getAllLocations());
        if(commonVectors.size() == 0) return true;
        for(Vector3d vector : commonVectors){
            if(jumpRestricted.getObject(vector) && restrictedBlocks.getObject(vector))
                return false;
        }
        return true;
    }
}
