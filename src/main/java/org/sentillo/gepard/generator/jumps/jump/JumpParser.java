package org.sentillo.gepard.generator.jumps.jump;

import org.sentillo.gepard.generator.jumps.BlockType;
import org.sentillo.gepard.utils.BlockMatrix3d;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.TextParser;
import org.sentillo.gepard.utils.Vector3d;

import java.util.*;

public class JumpParser extends TextParser<Jump> {

    public JumpParser() {
        super("newjump");
    }

    @Override
    public Jump parseOne(String code){
        Jump.JumpBuilder jump = Jump.builder();
        BlockMatrix3d blocks = new BlockMatrix3d();
        Matrix3d<Boolean> empty = new Matrix3d<>();

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

            else if(args[0].equals("onvisible")){
                if(args[4].equals("box")){
                    blocks.setBox(
                            Vector3d.of(
                                    Integer.parseInt(args[1]),
                                    Integer.parseInt(args[2]),
                                    Integer.parseInt(args[3])),
                            Vector3d.of(
                                    Integer.parseInt(args[5]),
                                    Integer.parseInt(args[6]),
                                    Integer.parseInt(args[7])),
                            BlockType.valueOf(args[8].toUpperCase(Locale.ROOT))
                    );
                } else{
                    blocks.setObject(new Vector3d(
                                    Integer.parseInt(args[1]),
                                    Integer.parseInt(args[2]),
                                    Integer.parseInt(args[3])),
                            BlockType.valueOf(args[4].toUpperCase(Locale.ROOT))
                    );
                }

            }
            else if(args[0].equals("onempty")){
                if(args[4].equals("box")){
                    empty.setBox(
                        Vector3d.of(
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        Integer.parseInt(args[3])),
                        Vector3d.of(
                        Integer.parseInt(args[5]),
                        Integer.parseInt(args[6]),
                        Integer.parseInt(args[7])),
                        Boolean.parseBoolean(args[8])
                    );
                } else{
                    empty.setObject(new Vector3d(
                                    Integer.parseInt(args[1]),
                                    Integer.parseInt(args[2]),
                                    Integer.parseInt(args[3])),
                            Boolean.valueOf(args[4])
                    );
                }
            }
        }
        jump.visibleLayer(blocks);
        jump.mustEmptyLayer(empty);
        return jump.build();
    }
}
