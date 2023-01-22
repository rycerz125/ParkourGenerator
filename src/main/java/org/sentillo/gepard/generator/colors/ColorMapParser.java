package org.sentillo.gepard.generator.colors;

import java.util.EnumMap;
import java.util.Map;

import org.sentillo.gepard.generator.jumps.BlockType;
import org.sentillo.gepard.generator.terrain.TerrainColor;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.TextParser;

class ColorMapParser extends TextParser<ColorMap>{

    public ColorMapParser(){
        super("newcolor");
    }

    @Override
    public ColorMap parseOne(String code){
        String name = "None";
        Map<BlockType, McBlock> jumpBlockMcBlockMap = new EnumMap<>(BlockType.class);
        Map<TerrainColor, McBlock> terrainColorMcBlockMap = new EnumMap<>(TerrainColor.class);

        for(String codeLine : code.split("\n")){
            String[] args = codeLine.trim().split(" ");

            if(args[0].equals("newcolor")){
                name = args[1];
            }
            else if(args[0].equals("blocktype")){
                jumpBlockMcBlockMap.put(BlockType.valueOf(args[1]), McBlock.valueOf(args[2]));
            }
            else if(args[0].equals("terraincolor")){
                terrainColorMcBlockMap.put(TerrainColor.valueOf(args[1]), McBlock.valueOf(args[2]));
            }
        }

        return new ColorMap(name, jumpBlockMcBlockMap, terrainColorMcBlockMap);
    }
}
