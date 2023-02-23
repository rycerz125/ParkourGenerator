package org.sentillo.gepard.generator.colors;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.generator.colors.ColorMap;
import org.sentillo.gepard.generator.colors.ColorMapParser;
import org.sentillo.gepard.generator.jumps.BlockType;
import org.sentillo.gepard.generator.terrain.TerrainColor;
import org.sentillo.gepard.utils.BlockMatrix3d;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.Vector3d;

class ColorMapParserTest {
    private String testCode = """
    newcolor Test
        blocktype BLOCK GRASS
        terraincolor LIQUID WATER
        terraincolor BLOCK1 GRASS
    colorend
    """;

    @Test
    void testParserName(){
        ColorMapParser cp = new ColorMapParser();
        List<ColorMap> coloristics = cp.parse(testCode);
        Assertions.assertEquals("Test", coloristics.get(0).getName());
    }

    @Test
    void testMatrixConversion(){
        ColorMapParser cp = new ColorMapParser();
        ColorMap coloristic = cp.parse(testCode).get(0);

        BlockMatrix3d jumps = new BlockMatrix3d();
        jumps.setObject(Vector3d.zero(), BlockType.BLOCK);
        Matrix3d<McBlock> jumpBlocks = coloristic.convertJumpMapToBlocks(jumps);

        Assertions.assertEquals(McBlock.GRASS, jumpBlocks.getObject(Vector3d.zero()));

        Matrix3d<TerrainColor> terrainColorMatrix3d = new Matrix3d<>();
        terrainColorMatrix3d.setObject(Vector3d.zero(), TerrainColor.LIQUID);
        Matrix3d<McBlock> terrainBlocks = coloristic.convertTerrainColorMapToBlocks(terrainColorMatrix3d);

        Assertions.assertEquals(McBlock.WATER, terrainBlocks.getObject(Vector3d.zero()));
    }
}
