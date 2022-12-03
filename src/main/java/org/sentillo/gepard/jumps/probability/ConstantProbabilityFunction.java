package org.sentillo.gepard.jumps.probability;

import java.util.random.RandomGenerator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class ConstantProbabilityFunction implements ProbabilityFunction{
    private int probabilityPoints;
    
    public int giveProbabilityPoints(){
        return probabilityPoints;
    }

    public int giveProbabilityPoints(RandomGenerator randomGenerator){
        return probabilityPoints;
    }

}
