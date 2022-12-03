package org.sentillo.gepard.jumps;

import java.io.File;
import java.util.List;

import org.sentillo.gepard.jumps.jump.Jump;
import org.sentillo.gepard.jumps.jump.JumpService;
import org.sentillo.gepard.jumps.specifications.JumpSpecificationsService;
import org.sentillo.gepard.utils.Vector3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JumpsGeneratorService {

    private JumpSpecificationsService jumpSpecificationsService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    //Standard assets folder is "./data/jumps"
    public JumpsGeneratorService(String assetsFolderPath){
        logger.info("Loading JumpsFacade...");
        JumpService jumpService = new JumpService(assetsFolderPath + File.separator + "jumps");
        jumpSpecificationsService = new JumpSpecificationsService(assetsFolderPath + File.separator + "specifications", jumpService);
    }

    public GeneratedJumpLayers generateJumpLayers(String jumpMap, String seed, int jumpsCount){
        GeneratedJumpLayers generatedJumpMap = new GeneratedJumpLayers();
        List<Jump> mapJumps = jumpSpecificationsService.generateJumps(jumpMap, seed, jumpsCount);

        Vector3d pointer = Vector3d.zero();

        for(Jump j : mapJumps){
            generatedJumpMap.getJumpBlocks().place(j.getVisibleLayer(), pointer);
            generatedJumpMap.getMustBeAir().place(j.getMustEmptyLayer(), pointer);
            generatedJumpMap.getCouldBeAir().place(j.getCouldEmptyLayer(), pointer);
            pointer = pointer.add(j.getStop());
        }

        return generatedJumpMap;
    }
}
