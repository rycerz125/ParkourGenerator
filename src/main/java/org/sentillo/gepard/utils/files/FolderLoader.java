package org.sentillo.gepard.utils.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.sentillo.gepard.utils.TextParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FolderLoader<T> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private TextParser<T> textParser;

    public FolderLoader(TextParser<T> textParser){
        this.textParser = textParser;
    }

    public List<T> load(String assetsFolder){
        List<LoadedFile> loadedFiles = FolderFileLoader.loadFilesFromFolder(assetsFolder).getFiles();
        List<T> objects = new ArrayList<>();

        for(LoadedFile file : loadedFiles){
            logger.info("Parsing file {}...", file.getFilePath() + File.separator + file.getFileName());
            List<T> newObjects = textParser.parse(file.getContent());
            logger.info("Found {} objects.", newObjects.size());

            for(T obj : newObjects){
                objects.add(obj);
            }
        }
        
        return objects;
    }
}
