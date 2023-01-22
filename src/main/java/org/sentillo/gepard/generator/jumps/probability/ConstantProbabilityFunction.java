package org.sentillo.gepard.generator.jumps.probability;

import java.util.random.RandomGenerator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class ConstantProbabilityFunction implements ProbabilityFunction{
    private float probabilityPoints;
    
    public float giveProbabilityPoints(){
        return probabilityPoints;
    }

    public float giveProbabilityPoints(RandomGenerator randomGenerator){
        return probabilityPoints;
    }

}
