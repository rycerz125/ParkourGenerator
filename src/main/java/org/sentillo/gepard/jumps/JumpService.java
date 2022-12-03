package org.sentillo.gepard.jumps;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.sentillo.gepard.utils.files.FolderFileLoader;
import org.sentillo.gepard.utils.files.LoadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class JumpService {

    private HashMap<String, Jump> jumps = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(JumpService.class);

    public JumpService(String assetsPath){
        logger.info("Loading jumps...");

        List<LoadedFile> loadedFiles = FolderFileLoader.loadFilesFromFolder(assetsPath);

        for(LoadedFile file : loadedFiles){
            logger.info("Parsing file "+ file.getFilePath() + File.separator + file.getFileName() + "...");
            JumpParser parser = new JumpParser();
            List<Jump> newJumps = parser.parse(file.getContent());
            logger.info("found "+newJumps.size()+" jumps: ");

            for(Jump jump : newJumps){
                logger.info("added "+jump.getName());
                addJump(jump);
            }
        }
        
    }

    public Optional<Jump> giveJump(String jump) {
        return Optional.ofNullable(jumps.get(jump));
    }

    private void addJump(Jump jump) {
        jumps.put(jump.getName(), jump);
    }

}
