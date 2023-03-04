package org.sentillo.gepard.generator.jumps.jump;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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

        Matrix3d<Boolean> restrictedArea = jump.getRestrictedArea(Vector3d.zero().add(Vector3d.of(5,4,3)));
        Assertions.assertTrue(restrictedArea.getObject(Vector3d.of(0,0,0).add(Vector3d.of(5,4,3))));
        Assertions.assertTrue(restrictedArea.getObject(Vector3d.of(1,1,0).add(Vector3d.of(5,4,3))));
        Assertions.assertTrue(restrictedArea.getObject(Vector3d.of(0,3,0).add(Vector3d.of(5,4,3))));
        Assertions.assertTrue(restrictedArea.getObject(Vector3d.of(3,1,0).add(Vector3d.of(5,4,3))));
        Assertions.assertTrue(restrictedArea.getObject(Vector3d.of(3,3,0).add(Vector3d.of(5,4,3))));
        Assertions.assertTrue(restrictedArea.getObject(Vector3d.of(3,2,0).add(Vector3d.of(5,4,3))));
        Assertions.assertFalse(restrictedArea.getObject(Vector3d.of(3,0,0).add(Vector3d.of(5,4,3))));
        Assertions.assertNull(restrictedArea.getObject(Vector3d.of(1,1,1).add(Vector3d.of(5,4,3))));

    }
}
