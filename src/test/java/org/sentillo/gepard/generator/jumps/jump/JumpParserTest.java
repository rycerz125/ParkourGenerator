package org.sentillo.gepard.generator.jumps.jump;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.generator.jumps.BlockType;
import org.sentillo.gepard.generator.jumps.jump.Jump;
import org.sentillo.gepard.generator.jumps.jump.JumpParser;
import org.sentillo.gepard.utils.Vector3d;

import java.util.List;

class JumpParserTest {

    private String testCode = """
        newjump 3+1
        start 0 0 0
        stop 4 1 0
        onvisible 0 0 0 block
        onvisible 4 1 0 stair_north
        onempty 0 0 0 box 4 3 0 true
        onshouldempty 0 0 -1 box 4 3 1 true
        jumpend
        
        newjump 4
        start 0 0 0
        stop 5 0 0
        onvisible 0 0 0 block
        onvisible 5 0 0 block
        jumpend
    """;

    @Test
    void testParserNameSet(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Assertions.assertEquals("3+1", jumps.get(0).getName());
    }

    @Test
    void testParserStartCoords(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Assertions.assertEquals(new Vector3d(0,0,0), jumps.get(0).getStart());
    }

    @Test
    void testParserStopCoords(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Assertions.assertEquals(new Vector3d(4,1,0), jumps.get(0).getStop());
    }

    @Test
    void testOnCommand(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Assertions.assertEquals(BlockType.BLOCK, jumps.get(0).getVisibleLayer().getObject(new Vector3d(0,0,0)));
        Assertions.assertEquals(BlockType.STAIR_NORTH, jumps.get(0).getVisibleLayer().getObject(new Vector3d(4,1,0)));
    }

    @Test
    void parseMultiple(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Assertions.assertEquals("3+1", jumps.get(0).getName());
        Assertions.assertEquals("4", jumps.get(1).getName());
    }

    @Test
    void testOnEmptyCommand(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Assertions.assertEquals(true, 
            jumps.get(0).getMustEmptyLayer().getObject(new Vector3d(0, 0, 0)));
    }
}
