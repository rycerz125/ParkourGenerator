package org.sentillo.gepard.generator.terrain.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BlockSpaceTest {
    @Test
    public void generateFullSpaceTest(){
        BlockSpace blockSpace = new BlockSpace(null,new RectangularSpaceInt(3,3,3,1,1,1));
        blockSpace.generateFullBlockDomain();
        Assertions.assertEquals(27, blockSpace.blockDomain.size());

    }
}
