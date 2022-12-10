package org.sentillo.gepard.colors;

import java.util.HashMap;
import java.util.Map;

import org.sentillo.gepard.jumps.BlockType;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.TextParser;

class ColorMapParser extends TextParser<ColorMap>{

    public ColorMapParser(){
        super("newcolor");
    }

    @Override
    public ColorMap parseOne(String code){
        String name = "None";
        Map<BlockType, McBlock> blocks = new HashMap<>();

        for(String codeLine : code.split("\n")){
            String[] args = codeLine.trim().split(" ");

            if(args[0].equals("newcolor")){
                name = args[1];
            }
            else if(args[0].equals("blocktype")){
                blocks.put(BlockType.valueOf(args[1]), McBlock.valueOf(args[2]));
            }
        }

        return new ColorMap(name, blocks);
    }
}
