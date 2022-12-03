package org.sentillo.gepard.jumps.probability;

import java.util.random.RandomGenerator;

public interface ProbabilityFunction {
    public float giveProbabilityPoints();
    public float giveProbabilityPoints(RandomGenerator randomGenerator);
}
