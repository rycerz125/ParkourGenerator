package org.sentillo.gepard.jumps.jump;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.sentillo.gepard.utils.files.FolderFileLoader;
import org.sentillo.gepard.utils.files.LoadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class JumpLoader {

    private Logger logger = LoggerFactory.getLogger(JumpLoader.class);

    public List<Jump> loadJumps(String assetsFolder){
        List<LoadedFile> loadedFiles = FolderFileLoader.loadFilesFromFolder(assetsFolder);
        List<Jump> jumps = new ArrayList<>();

        for(LoadedFile file : loadedFiles){
            logger.info("Parsing file "+ file.getFilePath() + File.separator + file.getFileName() + "...");
            JumpParser parser = new JumpParser();
            List<Jump> newJumps = parser.parse(file.getContent());
            logger.info("found "+newJumps.size()+" jumps: ");

            for(Jump jump : newJumps){
                logger.info("\tloaded "+jump.getName());
                jumps.add(jump);
            }
        }
        
        return jumps;
    }
}
