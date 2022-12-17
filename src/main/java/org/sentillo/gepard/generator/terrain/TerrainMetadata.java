package org.sentillo.gepard.generator.terrain;

import java.util.HashMap;

public class TerrainMetadata {

    private HashMap<String,String> values;

    public TerrainMetadata(HashMap<String, String> values){
        this.values = values;
    }

    public String getValue(String name){
        return values.get(name);
    }
}
