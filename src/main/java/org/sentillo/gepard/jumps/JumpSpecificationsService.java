package org.sentillo.gepard.jumps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JumpSpecificationsService {

    private Map<String, JumpsMap> jumpsSpecifications = new HashMap<>();

    public JumpSpecificationsService(String assetsPath, JumpService jumpService){
        
    }

    public List<Jump> generateJumps(String jumpMapName, String seed, int jumpsCount){
        return jumpsSpecifications.get(jumpMapName).generateJumps(jumpsCount, seed);
    }
}
