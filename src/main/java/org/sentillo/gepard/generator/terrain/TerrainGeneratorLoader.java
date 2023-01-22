package org.sentillo.gepard.generator.terrain;

import java.util.ArrayList;
import java.util.List;

import org.sentillo.gepard.utils.files.FolderFileLoader;
import org.sentillo.gepard.utils.files.FolderLoader;
import org.sentillo.gepard.utils.files.LoadedFile;
import org.sentillo.gepard.utils.files.LoadedFiles;

public class TerrainGeneratorLoader extends FolderLoader<TerrainGenerator> {

    public TerrainGeneratorLoader(){
        super(null);
    }
    @Override
    public List<TerrainGenerator> load(String assetsFolder){
        ArrayList<TerrainGenerator> terrainGenerators = new ArrayList<>();
        LoadedFiles loadedFiles = FolderFileLoader.loadFilesFromFolder(assetsFolder);
        LoadedFiles terrainDefinitions = loadedFiles.findFilesByName("terraininfo");

        for(LoadedFile definitionFile : terrainDefinitions.getLoadedFiles()){
            terrainGenerators.add(loadOne(loadedFiles.getAllFromFolder(definitionFile.getFilePath())));
        }

        return terrainGenerators;
    }

    private TerrainGenerator loadOne(LoadedFiles terrainFolder){
        return null;
    }
}
