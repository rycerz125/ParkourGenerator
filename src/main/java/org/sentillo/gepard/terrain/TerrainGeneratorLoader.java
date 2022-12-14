package org.sentillo.gepard.terrain;

import java.util.ArrayList;
import java.util.List;

import org.sentillo.gepard.utils.files.FolderFileLoader;
import org.sentillo.gepard.utils.files.LoadedFile;
import org.sentillo.gepard.utils.files.LoadedFiles;

public class TerrainGeneratorLoader {
    
    public List<TerrainGenerator> load(String assetsFolder){
        ArrayList<TerrainGenerator> terrains = new ArrayList<>();
        LoadedFiles loadedFiles = FolderFileLoader.loadFilesFromFolder(assetsFolder);
        LoadedFiles terrainDefinitions = loadedFiles.findFilesByName("terraininfo");

        for(LoadedFile definitionFile : terrainDefinitions.getLoadedFiles()){
            terrains.add(loadOne(loadedFiles.getAllFromFolder(definitionFile.getFilePath())));
        }

        return terrains;
    }

    private TerrainGenerator loadOne(LoadedFiles terrainFolder){
        return null;
    }
}
