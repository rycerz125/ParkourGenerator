package org.sentillo.gepard.bukkit.mapplacer;

import java.util.HashMap;

import org.bukkit.Material;
import org.sentillo.gepard.generator.terrain.TerrainGenerator;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.files.FolderFileLoader;
import org.sentillo.gepard.utils.files.FolderLoader;
import org.sentillo.gepard.utils.files.LoadedFile;
import org.sentillo.gepard.utils.files.LoadedFiles;

public class ConversionMapLoader {
    private ConversionMapLoader(){}

    public static HashMap<McBlock, Material> loadConversionMap(String assetsPath){
        HashMap<McBlock, Material> conversionMap = new HashMap<>();

        LoadedFiles lf = FolderFileLoader.loadFilesFromFolder(assetsPath);
        LoadedFile conversionFile = lf.findFilesByName("blockConversion").getFiles().get(0);
        conversionFile.getContent().lines().forEach((
                line -> {
                    String[] map = line.split(" ");
                    conversionMap.put(McBlock.valueOf(map[0]), Material.getMaterial(map[1]));
                }
        ));

        return conversionMap;
    }
}
