package org.sentillo.gepard.jumps;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
class JumpsSpecifications {

    @Getter
    @Builder.Default
    private ArrayList<JumpSpecification> jumpsSpecifications = new ArrayList<>();

    public List<Jump> generateJumps(int number, String seed){
        List<Jump> jumps = new ArrayList<>();
        return jumps;
    }
}
