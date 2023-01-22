package org.sentillo.gepard.bukkit.mapplacer;

import java.util.HashMap;

import org.bukkit.Material;
import org.sentillo.gepard.utils.McBlock;

public class ConversionMap {

    private HashMap<McBlock,Material> conversionMap;

    public ConversionMap(String conversionMapPath){
        conversionMap = ConversionMapLoader.loadConversionMap(conversionMapPath);
    }

    public Material convert(McBlock block){
        return conversionMap.get(block);
    }
}
