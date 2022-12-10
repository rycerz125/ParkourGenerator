package org.sentillo.gepard.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.sentillo.gepard.utils.files.FolderLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AccumulationService<T extends Named> {
    protected HashMap<String, T> accumulatedObjects = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(getClass());

    public AccumulationService(String assetsPath, FolderLoader<T> folderLoader){
        logger.info("Loading objects on "+getClass()+"...");
        List<T> loadedJumps = folderLoader.load(assetsPath);

        for(T j : loadedJumps){
            add(j);
        }
    }

    public Optional<T> get(String name) {
        return Optional.ofNullable(accumulatedObjects.get(name));
    }

    private void add(T object) {
        accumulatedObjects.put(object.getName(), object);
    }
}
