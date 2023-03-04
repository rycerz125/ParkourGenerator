package org.sentillo.gepard.generator.jumps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.generator.jumps.jump.Jump;
import org.sentillo.gepard.generator.jumps.jump.JumpLoader;
import org.sentillo.gepard.generator.jumps.jump.JumpParser;
import org.sentillo.gepard.generator.jumps.jump.JumpService;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.Vector3d;
import org.sentillo.gepard.utils.Vector3dDouble;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JumpLineGeneratorTest {
    private String testCode = """
        newjump 3plus1
        stop 4 1 0
        onvisible 0 0 0 block
        onvisible 4 1 0 block
    
        onempty 0 0 0 box 4 4 0 true
        onempty 4 0 0 false
        onempty 4 4 0 false
        onempty 0 3 0 false
        onempty 0 4 0 false
        onempty 1 4 0 false
    
        onshouldempty 4 4 0 true
        onshouldempty 0 3 0 true
        jumpend
        
        newjump 4
        stop 5 0 0
        onvisible 0 0 0 block
        onvisible 5 0 0 block
    
        onempty 0 0 0 box 5 2 0 true
            onempty 1 3 0 box 4 3 0 true
                onempty 2 4 0 box 3 4 0 true
    
        onshouldempty 0 3 0 true
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
        JumpLineGenerator jumpLineGenerator = new JumpLineGenerator(new ArrayList<>(),new Matrix3d<>(),jumpSet,12);
        List<Jump> jumpList1 = new ArrayList<>();
        List<Jump> jumpList2 = new ArrayList<>();
        List<Jump> jumpList3 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            jumpList1.add(jumpLineGenerator.findMatchingJump(Vector3d.zero(), new Vector3dDouble(8,2,0),0));
            jumpList2.add(jumpLineGenerator.findMatchingJump(Vector3d.zero(), new Vector3dDouble(7,0,0),0));
            jumpList3.add(jumpLineGenerator.findMatchingJump(Vector3d.zero(), new Vector3dDouble(8,2,0),Math.PI/10));
        }
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals("3plus1", jumpList1.get(i).getName());
            Assertions.assertEquals("4", jumpList2.get(i).getName());
            System.out.println(jumpList3.get(i).getName());
        }
        List<Jump> jumpList4 = new ArrayList<>();
        jumpLineGenerator.restrictedBlocks.setObject(Vector3d.of(4,0,0),true);
        for (int i = 0; i < 10; i++) {
            jumpList4.add(jumpLineGenerator.findMatchingJump(Vector3d.zero(), new Vector3dDouble(4,0,0),0));
        }
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals("3plus1", jumpList4.get(i).getName());
        }


    }
    @Test
    void generateTest(){
        JumpLoader jumpLoader = new JumpLoader();
        List<Jump> loadedJumps = jumpLoader.load("plugins" +File.separator+"GepardGenerator"+File.separator+ "jumps" + File.separator + "jumps"+ File.separator + "2blockjumps" + File.separator + "easy");
        JumpParser jp = new JumpParser();
        List<Jump> jumps = jp.parse(testCode);
        Set<Jump> jumpSet = new HashSet<>();
        for(Jump jump : loadedJumps){
            jumpSet.addAll(jump.getAllDirections());
        }

        List<Vector3dDouble> line = new ArrayList<>();
        line.add(new Vector3dDouble(0, 0, 0));
        line.add(new Vector3dDouble(5,0,2));
        JumpLineGenerator jumpLineGenerator = new JumpLineGenerator(line,new Matrix3d<>(),jumpSet,12);
        List<Jump> generated = jumpLineGenerator.generate(0);
        for(Jump jump : generated){
            System.out.println(jump.getName());
        }
    }
}
