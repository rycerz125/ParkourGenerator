package org.sentillo.gepard.jumps;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JumpSpecificationsTest {

    @Test
    public void testSimpleGenerate(){
        Jump four = new JumpParser().parse(
            """
            newjump 4
            start 0 0 0
            stop 5 0 0
            onvisible 0 0 0 block
            onvisible 5 0 0 block
            jumpend
            """
        ).get(0);

        JumpsMap jumpSpecification = JumpsMap.builder()
        .jumpsSpecifications(List.of(
            JumpSpecification.builder().jump(four).build()
        )).build();

        List<Jump> jumps = jumpSpecification.generateJumps(2, "test");

        Assertions.assertEquals(2, jumps.size());
    }
    
}
