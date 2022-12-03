package org.sentillo.gepard.jumps.probability;

public class ProbabilityFunctionFactory {

    public static ProbabilityFunction getConstant(int num){
        return new ConstantProbabilityFunction(1);
    }
}
