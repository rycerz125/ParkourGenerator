package org.sentillo.gepard.jumps;

import org.sentillo.gepard.utils.BlockMatrix3d;
import org.sentillo.gepard.utils.Vector3d;

import java.util.*;

class JumpParser {

    public List<Jump> parse(String code){
        String[] codes = code.trim().split("newjump.");
        List<Jump> jumps = new ArrayList<>();

        for(String c : codes){
            c = c.trim();
            if(!c.equals(""))
            jumps.add(parseOne("newjump " + c));
        }

        return jumps;
    }

    public Jump parseOne(String code){
        Jump.JumpBuilder jump = Jump.builder();
        BlockMatrix3d blocks = new BlockMatrix3d();

        for(String codeLine : code.split("\n")){
            String[] args = codeLine.trim().split(" ");

            if(args[0].equals("newjump")){
                jump.name(args[1]);
            }
            else if(args[0].equals("start")){
                jump.start(new Vector3d(
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        Integer.parseInt(args[3])
                ));
            }
            else if(args[0].equals("stop")){
                jump.stop(new Vector3d(
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        Integer.parseInt(args[3])
                ));
            }

            else if(args[0].equals("on")){
                blocks.setObject(
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]),
                    Integer.parseInt(args[3]),
                    BlockType.valueOf(args[4].toUpperCase(Locale.ROOT))
                );
            }
        }
        jump.visibleLayer(blocks);
        Jump madeJump = jump.build();
        return madeJump;
    }
}
