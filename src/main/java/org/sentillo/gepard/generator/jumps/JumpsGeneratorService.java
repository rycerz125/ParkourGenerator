package org.sentillo.gepard.generator.jumps;

import java.io.File;
import java.util.List;

import org.sentillo.gepard.generator.jumps.jump.Jump;
import org.sentillo.gepard.generator.jumps.jumpsmap.JumpsMapService;
import org.sentillo.gepard.generator.jumps.jump.JumpService;
import org.sentillo.gepard.utils.Vector3d;
import org.sentillo.gepard.utils.files.FolderPlacing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JumpsGeneratorService {

    private JumpsMapService jumpSpecificationsService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    //Standard assets folder is "./data/jumps"
    public JumpsGeneratorService(String assetsFolderPath){
        logger.info("Loading JumpsFacade...");
        JumpService jumpService = new JumpService(FolderPlacing.JUMPS_FOLDER);
        jumpSpecificationsService = new JumpsMapService(FolderPlacing.JUMPSMAP_FOLDER, jumpService);
    }

    public GeneratedJumpLayers generateJumpLayers(String jumpMap, String seed, int jumpsCount){
        GeneratedJumpLayers generatedJumpMap = new GeneratedJumpLayers();
        List<Jump> mapJumps = jumpSpecificationsService.get(jumpMap).orElseThrow().generateJumps(jumpsCount, seed);

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
