package org.sentillo.gepard.generator.jumps.jump;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.generator.jumps.BlockType;
import org.sentillo.gepard.utils.BlockMatrix3d;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.Vector3d;

import java.util.List;

public class JumpTest {
    private String testCode = """
    newjump 2plus1
    stop 3 1 0
    onvisible 0 0 0 block
    onvisible 3 1 0 block

    onempty 0 0 0 box 3 3 0 true
    onempty 3 0 0 false

    onshouldempty 0 3 0 true
    jumpend
    """;
    @Test
    void getRestrictedAreaTest(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Jump jump = jumps.get(0);
        Assertions.assertEquals("2plus1", jumps.get(0).getName());

        BlockMatrix3d restrictedArea = jump.getRestrictedArea(Vector3d.zero().add(Vector3d.of(5,4,3)));
        Assertions.assertEquals(BlockType.BLOCK,restrictedArea.getObject(Vector3d.of(0,0,0).add(Vector3d.of(5,4,3))));
        Assertions.assertEquals(BlockType.AIR,restrictedArea.getObject(Vector3d.of(1,1,0).add(Vector3d.of(5,4,3))));
        Assertions.assertEquals(BlockType.AIR,restrictedArea.getObject(Vector3d.of(0,3,0).add(Vector3d.of(5,4,3))));
        Assertions.assertEquals(BlockType.BLOCK,restrictedArea.getObject(Vector3d.of(3,1,0).add(Vector3d.of(5,4,3))));
        Assertions.assertEquals(BlockType.AIR,restrictedArea.getObject(Vector3d.of(3,3,0).add(Vector3d.of(5,4,3))));
        Assertions.assertEquals(BlockType.AIR,restrictedArea.getObject(Vector3d.of(3,2,0).add(Vector3d.of(5,4,3))));
        Assertions.assertNull(restrictedArea.getObject(Vector3d.of(3,0,0).add(Vector3d.of(5,4,3))));
        Assertions.assertNull(restrictedArea.getObject(Vector3d.of(1,1,1).add(Vector3d.of(5,4,3))));

    }
    @Test
    void cloneTest(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Jump jump = jumps.get(0);
        Assertions.assertEquals("2plus1", jump.getName());
        Assertions.assertTrue(jump.getMustEmptyLayer().getObject(Vector3d.zero()));

        Jump jump2 = jump.clone();
        Assertions.assertEquals("2plus1", jump2.getName());
        Assertions.assertTrue(jump2.getMustEmptyLayer().getObject(Vector3d.zero()));
    }
    @Test
    void collidesWithNextTest(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Jump jump = jumps.get(0);
        jump.collidesWithNext(jump);
    }
}
