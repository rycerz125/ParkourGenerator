package org.sentillo.gepard.jumps;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JumpsFacade {

    private JumpSpecificationsService jumpSpecificationsService;
    private Logger logger = LoggerFactory.getLogger(JumpsFacade.class);

    //Standard assets folder is "./data/jumps"
    public JumpsFacade(String assetsFolderPath){
        logger.info("Loading JumpsFacade...");
        JumpService jumpService = new JumpService(assetsFolderPath + File.separator + "jumps");
        jumpSpecificationsService = new JumpSpecificationsService(assetsFolderPath + File.separator + "specifications", jumpService);
    }

    public GeneratedJumpMap generateJumpMap(String jumpMap, String seed, int jumpsCount){
        GeneratedJumpMap generatedJumpMap = new GeneratedJumpMap();
        List<Jump> mapJumps = jumpSpecificationsService.generateJumps(jumpMap, seed, jumpsCount);

        return generatedJumpMap;
    }
}
