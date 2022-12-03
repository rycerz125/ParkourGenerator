package org.sentillo.gepard.jumps.probability;

import java.util.random.RandomGenerator;

public interface ProbabilityFunction {
    public int giveProbabilityPoints();
    public int giveProbabilityPoints(RandomGenerator randomGenerator);
}
