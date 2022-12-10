package org.sentillo.gepard.jumps.jumpsmap;

import org.sentillo.gepard.jumps.jump.Jump;
import org.sentillo.gepard.jumps.probability.ProbabilityFunction;
import org.sentillo.gepard.jumps.probability.ProbabilityFunctionFactory;

import lombok.Builder;
import lombok.Getter;

@Builder
class JumpSpecification {
    @Getter
    private Jump jump;

    @Getter
    @Builder.Default
    private ProbabilityFunction probabilityFunction = ProbabilityFunctionFactory.getConstant(1);
    
    @Getter
    @Builder.Default
    private int limit = 0;
    
    @Getter
    @Builder.Default
    private int mustGenerateNumber = 0;
}
