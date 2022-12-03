package org.sentillo.gepard.jumps.specifications;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.sentillo.gepard.jumps.jump.JumpService;
import org.sentillo.gepard.utils.files.FolderFileLoader;
import org.sentillo.gepard.utils.files.LoadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JumpMapLoader {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private JumpService jumpService;

    public JumpMapLoader(JumpService jumpService){
        this.jumpService = jumpService;
    }

    public List<JumpsMap> loadJumpSpecifications(String assetsPath){
        
        List<LoadedFile> loadedFiles = FolderFileLoader.loadFilesFromFolder(assetsPath);
        List<JumpsMap> jumpsMaps = new ArrayList<>();
        JumpsMapParser parser = new JumpsMapParser(jumpService);

        for(LoadedFile file : loadedFiles){
            logger.info("Parsing file "+ file.getFilePath() + File.separator + file.getFileName() + "...");
            
            List<JumpsMap> newJumpMaps = parser.parse(file.getContent());
            logger.info("found "+newJumpMaps.size()+" jump maps: ");

            for(JumpsMap jumpMap : newJumpMaps){
                logger.info("\tloaded "+jumpMap.getName());
                jumpsMaps.add(jumpMap);
            }
        }
        
        return jumpsMaps;
    }
    
}
