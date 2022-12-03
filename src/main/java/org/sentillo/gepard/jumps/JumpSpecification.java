package org.sentillo.gepard.jumps;

import org.sentillo.gepard.jumps.probability.ProbabilityFunction;
import org.sentillo.gepard.jumps.probability.ProbabilityFunctionFactory;

import lombok.Builder;
import lombok.Getter;

@Builder
class JumpSpecification {
    @Getter
    public Jump jump;

    @Getter
    @Builder.Default
    public ProbabilityFunction probabilityFunction = ProbabilityFunctionFactory.getConstant(1);
    
    @Getter
    @Builder.Default
    public int limit = 0;
    
    @Getter
    @Builder.Default
    public int mustGenerateNumber = 0;
}
