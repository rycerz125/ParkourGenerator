package org.sentillo.gepard.generator.terrain;

import java.util.HashMap;

public class TerrainMetadata {

    private HashMap<String,String> values;

    public TerrainMetadata(HashMap<String, String> values){
        if(values != null)
            this.values = values;
        else
            this.values = new HashMap<>();
    }

    public String getValue(String name){
        return values.get(name);
    }
}
