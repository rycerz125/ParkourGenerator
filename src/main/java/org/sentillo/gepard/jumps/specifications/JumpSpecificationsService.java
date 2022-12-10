package org.sentillo.gepard.jumps.specifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sentillo.gepard.jumps.jump.Jump;
import org.sentillo.gepard.jumps.jump.JumpService;

public class JumpSpecificationsService {

    private Map<String, JumpsMap> jumpsSpecifications = new HashMap<>();

    public JumpSpecificationsService(String assetsPath, JumpService jumpService){
        JumpMapLoader jumpMapLoader = new JumpMapLoader(jumpService);
        List<JumpsMap> jumpMaps = jumpMapLoader.load(assetsPath);

        for(JumpsMap jm : jumpMaps){
            addSpecification(jm);
        }
    }

    public List<Jump> generateJumps(String jumpMapName, String seed, int jumpsCount){
        return jumpsSpecifications.get(jumpMapName).generateJumps(jumpsCount, seed);
    }

    private void addSpecification(JumpsMap jm){
        jumpsSpecifications.put(jm.getName(), jm);
    }
}
