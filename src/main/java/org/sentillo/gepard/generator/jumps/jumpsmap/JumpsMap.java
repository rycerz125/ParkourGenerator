package org.sentillo.gepard.generator.jumps.jumpsmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.sentillo.gepard.generator.jumps.jump.Jump;
import org.sentillo.gepard.utils.Named;

import lombok.Builder;
import lombok.Getter;

@Builder
public class JumpsMap implements Named{

    @Getter
    private String name;

    @Getter
    @Builder.Default
    private List<JumpSpecification> jumpsSpecifications = new ArrayList<>();

    public List<Jump> generateJumps(int number, String seed){
        List<Jump> jumps = new ArrayList<>();
        Random random = new Random(seed.hashCode());

        for(int i=0;i<number; i++){
            int r = random.nextInt();
            Jump j = jumpsSpecifications.get(r%jumpsSpecifications.size()).getJump();
            jumps.add(j);
        }

        return jumps;
    }
}
