package org.sentillo.gepard.generator.jumps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.generator.jumps.jump.Jump;
import org.sentillo.gepard.generator.jumps.jump.JumpParser;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.Vector3d;
import org.sentillo.gepard.utils.Vector3dDouble;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JumpLineGeneratorTest {
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
    void maxJumpDistanceSquaredTest(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Set<Jump> jumpSet = new HashSet<>(jumps);
        JumpLineGenerator jumpLineGenerator = new JumpLineGenerator(new ArrayList<>(),new Matrix3d<>(),jumpSet);
        Assertions.assertEquals(25,jumpLineGenerator.getMaxJumpDistanceSquared());
    }
    @Test
    void jumpCanBePlacedTest(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Set<Jump> jumpSet = new HashSet<>(jumps);
        Matrix3d<Boolean> terrainArea = new Matrix3d<>();
        terrainArea.setObject(Vector3d.of(0,0,0), true);
        JumpLineGenerator jumpLineGenerator = new JumpLineGenerator(new ArrayList<>(),terrainArea,jumpSet);
        Assertions.assertFalse(jumpLineGenerator.jumpCanBePlaced(jumps.get(0),Vector3d.zero()));
        Assertions.assertTrue(jumpLineGenerator.jumpCanBePlaced(jumps.get(0),Vector3d.of(1,0,0)));

        terrainArea.setObject(Vector3d.of(0,0,0), false);
        Assertions.assertTrue(jumpLineGenerator.jumpCanBePlaced(jumps.get(0),Vector3d.zero()));
        Assertions.assertTrue(jumpLineGenerator.jumpCanBePlaced(jumps.get(0),Vector3d.of(1,0,0)));
    }
    @Test
    void getRandomJumpTest(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Set<Jump> jumpSet = new HashSet<>(jumps);
        JumpLineGenerator jumpLineGenerator = new JumpLineGenerator(new ArrayList<>(),new Matrix3d<>(),jumpSet);
        for (int i = 0; i < 10; i++) {
            System.out.println(jumpLineGenerator.drawJumpFrom(jumpSet).getName());
        }

    }
    @Test
    void findMatchingJumpTest(){
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Set<Jump> jumpSet = new HashSet<>(jumps);
        JumpLineGenerator jumpLineGenerator = new JumpLineGenerator(new ArrayList<>(),new Matrix3d<>(),jumpSet);
        List<Jump> jumpList1 = new ArrayList<>();
        List<Jump> jumpList2 = new ArrayList<>();
        List<Jump> jumpList3 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            jumpList1.add(jumpLineGenerator.findMatchingJump(Vector3d.zero(), new Vector3dDouble(8,2,0),0));
            jumpList2.add(jumpLineGenerator.findMatchingJump(Vector3d.zero(), new Vector3dDouble(7,0,0),0));
            jumpList3.add(jumpLineGenerator.findMatchingJump(Vector3d.zero(), new Vector3dDouble(8,2,0),Math.PI/10));
        }
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals("3+1", jumpList1.get(i).getName());
            Assertions.assertEquals("4", jumpList2.get(i).getName());
            System.out.println(jumpList3.get(i).getName());
        }



    }
}
