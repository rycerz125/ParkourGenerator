package org.sentillo.gepard.jumps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.Builder;
import lombok.Getter;

@Builder
class JumpsMap {
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
