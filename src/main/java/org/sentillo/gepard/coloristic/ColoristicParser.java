package org.sentillo.gepard.coloristic;

import java.util.HashMap;
import java.util.Map;

import org.sentillo.gepard.jumps.BlockType;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.TextParser;

class ColoristicParser extends TextParser<Coloristic>{

    public ColoristicParser(){
        super("newcolor");
    }

    @Override
    public Coloristic parseOne(String code){
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

        return new Coloristic(name, blocks);
    }
}
