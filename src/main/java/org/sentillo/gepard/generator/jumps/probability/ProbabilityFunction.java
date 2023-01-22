package org.sentillo.gepard.generator.jumps.probability;

import java.util.random.RandomGenerator;

public interface ProbabilityFunction {
    public float giveProbabilityPoints();
    public float giveProbabilityPoints(RandomGenerator randomGenerator);
}
