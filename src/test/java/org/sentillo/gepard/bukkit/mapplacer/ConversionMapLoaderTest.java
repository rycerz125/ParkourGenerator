package org.sentillo.gepard.bukkit.mapplacer;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Material;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.utils.McBlock;

class ConversionMapLoaderTest {
    
    @Test
    void conversionTest(){
        HashMap<McBlock, Material> convMap = ConversionMapLoader.loadConversionMap("src" + File.separator + "test" + File.separator +"testfolder" + File.separator + "assets" + File.separator + "bukkit");
        Assertions.assertEquals(2, convMap.size());

    }
}
