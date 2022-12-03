package org.sentillo.gepard.jumps.jump;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JumpService {

    private HashMap<String, Jump> jumps = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(JumpService.class);

    public JumpService(String assetsPath){
        logger.info("Loading jumps...");
        List<Jump> loadedJumps = new JumpLoader().loadJumps(assetsPath);

        for(Jump j : loadedJumps){
            addJump(j);
        }
    }

    public Optional<Jump> giveJump(String jump) {
        return Optional.ofNullable(jumps.get(jump));
    }

    private void addJump(Jump jump) {
        jumps.put(jump.getName(), jump);
    }
}
