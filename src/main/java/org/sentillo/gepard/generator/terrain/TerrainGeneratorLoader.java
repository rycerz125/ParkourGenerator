package org.sentillo.gepard.generator.terrain;

import java.util.ArrayList;
import java.util.HashMap;
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

        for(LoadedFile definitionFile : terrainDefinitions.getFiles()){
            terrainGenerators.add(loadOne(loadedFiles.getAllFromFolder(definitionFile.getFilePath())));
        }

        return terrainGenerators;
    }

    private TerrainGenerator loadOne(LoadedFiles terrainFolder){
        LoadedFile terrainInfo = terrainFolder.findFileByName("terraininfo");
        String content = terrainInfo.getContent();
        String[] lines = content.split("\n");
        HashMap<String,String> metaData = constructMetaData(lines);
        String name = metaData.get("terrainname");
        String codeFileName = metaData.get("run");
        String code = terrainFolder.findFileByName(codeFileName).getContent();

        return new TerrainGenerator(name,code, new TerrainMetadata(metaData));
    }
    public HashMap<String,String> constructMetaData(String[] lines){
        HashMap<String,String> metaData = new HashMap<>();
        for(String line : lines){
            int separateIndex = line.lastIndexOf(" ");
            String key = line.substring(0,separateIndex);
            String value = line.substring(separateIndex+1);
            metaData.put(key, value);
        }
        return metaData;
    }
}
