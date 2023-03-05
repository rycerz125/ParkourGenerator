package org.sentillo.gepard.generator.jumps;

import java.util.*;

import org.sentillo.gepard.generator.jumps.jump.Jump;
import org.sentillo.gepard.utils.BlockMatrix3d;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.Vector3d;
import org.sentillo.gepard.utils.Vector3dDouble;

public class JumpLineGenerator {
    public List<Vector3dDouble> line;
    public BlockMatrix3d restrictedBlocks;
    public Set<Jump> availableJumps;
    private Random randomGenerator;
    private double maxDistanceSquared;

    public JumpLineGenerator(List<Vector3dDouble> line, BlockMatrix3d restrictedBlocks, Set<Jump> availableJumps){
        this.line = line;
        this.restrictedBlocks = restrictedBlocks;
        this.availableJumps = availableJumps;
        randomGenerator = new Random();
        maxDistanceSquared = getMaxJumpDistanceSquared();

    }
    public JumpLineGenerator(List<Vector3dDouble> line, BlockMatrix3d restrictedBlocks, Set<Jump> availableJumps, long seed){
        this.line = line;
        this.restrictedBlocks = restrictedBlocks;
        this.availableJumps = availableJumps;
        randomGenerator = new Random(seed);
        maxDistanceSquared = getMaxJumpDistanceSquared();
    }
    private int currentTargetIndex = 0;

    public List<Jump> generate(double angleSuggestion){
        Vector3d startPoint = line.get(0).toVector3d();
        Vector3dDouble targetPoint = nextTarget();

        List<Jump> jumps = new ArrayList<>();
        while(notLastTarget()){
            if(startPoint.toVector3dDouble().getDistanceSquared(targetPoint) <= maxDistanceSquared/4){
                targetPoint = nextTarget();
            }else{
                Jump jump = findMatchingJump(startPoint, targetPoint,angleSuggestion);
                jumps.add(jump);
                restrictedBlocks.place(jump.getRestrictedArea(Vector3d.zero()), startPoint);
                startPoint = startPoint.add(jump.getStartStopVector());
            }
        }
        jumps.addAll(findFinalJumpLine(startPoint,targetPoint,angleSuggestion));
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

    private List<Jump> findFinalJumpLine(Vector3d start, Vector3dDouble targetPoint, double angleSuggestion) {
        Vector3d endCasted = targetPoint.toVector3d();
        List<Jump> finalJumpList = new ArrayList<>();
        Jump fitJump = getFitJump(start, endCasted);
        if(fitJump != null) {
            finalJumpList.add(fitJump);
            return finalJumpList;
        }
        Vector3d startPoint = start;


        while(startPoint.toVector3dDouble().getDistanceSquared(targetPoint) > maxDistanceSquared*4){
            Jump jump = findMatchingJump(startPoint, targetPoint,angleSuggestion);
            finalJumpList.add(jump);
            restrictedBlocks.place(jump.getRestrictedArea(Vector3d.zero()), startPoint);
            startPoint = startPoint.add(jump.getStartStopVector());
        }

        Set<Jump> availableNotCheckedPenultimateJumps = new HashSet<>(availableJumps);
        HashMap<Jump, Vector3d> possibleLastJumpStarts = getPossibleLastJumpStarts(endCasted);

        boolean notReturnedFirstTime = true;
        while(true){
            HashMap<Jump, Vector3d> possibleFirstJumpEnds = getPossibleFirstJumpEnds(startPoint);

            HashMap<Jump, Jump> commonPointPairs = getCommonPointJumps(possibleFirstJumpEnds,possibleLastJumpStarts);
    
            if(commonPointPairs.size() != 0){
                for(Jump jumpFirst : commonPointPairs.keySet()){
                    Jump jumpSecond = commonPointPairs.get(jumpFirst);
                    if(!jumpFirst.collidesWithNext(jumpSecond)){
                        finalJumpList.add(jumpFirst);
                        finalJumpList.add(jumpSecond);
                        return finalJumpList;
                    }
                }
            }
    
            // if(!notReturnedFirstTime){
            //     Jump lastJump = finalJumpList.get(finalJumpList.size()-1);
            //     restrictedBlocks.place(lastJump.getRestrictedAreaOnlyTrueAndInverted(startPoint), Vector3d.zero());
            //     startPoint = startPoint.subtract(lastJump.getStartStopVector());
            //     finalJumpList.remove(finalJumpList.size()-1);
            // }
            Jump jump = findMatchingJump(startPoint, targetPoint,angleSuggestion);//,availableNotCheckedPenultimateJumps);
            finalJumpList.add(jump);
            restrictedBlocks.place(jump.getRestrictedArea(Vector3d.zero()), startPoint);
            startPoint = startPoint.add(jump.getStartStopVector());
            notReturnedFirstTime = false;
            availableNotCheckedPenultimateJumps.remove(jump);
        }
    }
    protected HashMap<Jump, Jump> getCommonPointJumps(HashMap<Jump, Vector3d> possibleFirstJumpEnds, HashMap<Jump, Vector3d> possibleLastJumpStarts){
        HashMap<Jump, Jump> commonPointPairs = new HashMap<>();

        for(Jump jumpFirst : possibleFirstJumpEnds.keySet()){
            Vector3d firstJumpEnd = possibleFirstJumpEnds.get(jumpFirst);
            for(Jump jumpLast : possibleLastJumpStarts.keySet()){
                Vector3d lastJumpStart = possibleLastJumpStarts.get(jumpLast);
                if(firstJumpEnd.equals(lastJumpStart))
                    commonPointPairs.put(jumpFirst, jumpLast);
            }
        }
        return commonPointPairs;
    }
    protected HashMap<Jump, Vector3d> getPossibleFirstJumpEnds(Vector3d start){
        return getPossibleFirstJumpEnds(start,restrictedBlocks);
    }
    protected HashMap<Jump, Vector3d> getPossibleFirstJumpEnds(Vector3d start, BlockMatrix3d restrictedArea){
        HashMap<Jump, Vector3d> possibleFirstJumpEnds = new HashMap<>();
        for(Jump jump : availableJumps){
            if(jumpCanBePlaced(jump,start,restrictedArea))
                possibleFirstJumpEnds.put(jump, start.add(jump.getStartStopVector()));
        }
        return possibleFirstJumpEnds;
    }

    protected HashMap<Jump, Vector3d>  getPossibleLastJumpStarts(Vector3d end){
        HashMap<Jump, Vector3d> possibleLastJumpStarts = new HashMap<>();
        for(Jump jump : availableJumps){
            Vector3d jumpStartPoint = end.subtract(jump.getStartStopVector());
            if(jumpCanBePlaced(jump, jumpStartPoint))
                possibleLastJumpStarts.put(jump, jumpStartPoint);
        }
        return possibleLastJumpStarts;
    }
    protected Jump getFitJump(Vector3d startPoint, Vector3d target){
        for(Jump jump : availableJumps){
            if(jump.getStartStopVector().equals(target.subtract(startPoint)) && jumpCanBePlaced(jump,startPoint))
                return jump;
        }
        return null;
    }

    private boolean notLastTarget() {
        return currentTargetIndex != line.size()-1;
    }

    protected Jump findMatchingJump(Vector3d start, Vector3dDouble target, double angleSuggestion) throws RuntimeException{
        return findMatchingJump(start, target, angleSuggestion, availableJumps);
    }

    protected Jump findMatchingJump(Vector3d start, Vector3dDouble target, double angleSuggestion, Set<Jump> jumpBase) throws RuntimeException{
        Set <Jump> placeableJumps = findPlaceableJumps(start, jumpBase);
        if(placeableJumps.size() == 0)
            System.out.println("nie udalo sie znalezc rozwiazania");
            //throw new RuntimeException("Cannot find next jump because all of them have a conflict with the terrain or with other placed jumps");
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
    private Set<Jump> findPlaceableJumps(Vector3d start, Set<Jump> jumpBase){
        Set<Jump> placeableJumps = new HashSet<>(jumpBase);
        placeableJumps.removeIf((jump -> !jumpCanBePlaced(jump, start)));
        return placeableJumps;
    }
    protected boolean jumpCanBePlaced(Jump jump, Vector3d shift){
        return jumpCanBePlaced(jump, shift, restrictedBlocks);
    }
    protected boolean jumpCanBePlaced(Jump jump, Vector3d shift, BlockMatrix3d restrictedArea){
        return !jump.collidesWithArea(restrictedArea, shift);
    }
}
