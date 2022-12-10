package org.sentillo.gepard.coloristic;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.jumps.BlockType;
import org.sentillo.gepard.utils.BlockMatrix3d;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.Vector3d;

public class ColoristicParserTest {
    private String testCode = """
    newcolor Test
        blocktype BLOCK GRASS
    colorend
    """;

    @Test
    public void testParserName(){
        ColoristicParser cp = new ColoristicParser();
        List<Coloristic> coloristics = cp.parse(testCode);
        Assertions.assertEquals("Test", coloristics.get(0).getName());
    }

    @Test
    public void testMatrixConversion(){
        ColoristicParser cp = new ColoristicParser();
        Coloristic coloristic = cp.parse(testCode).get(0);

        BlockMatrix3d jumps = new BlockMatrix3d();
        jumps.setObject(Vector3d.zero(), BlockType.BLOCK);
        Matrix3d<McBlock> blocks = coloristic.convertJumpMapToBlocks(jumps);

        Assertions.assertEquals(McBlock.GRASS, blocks.getObject(Vector3d.zero()));
    }
}
