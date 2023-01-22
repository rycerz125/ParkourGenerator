package org.sentillo.gepard.bukkit.mapplacer;

import java.io.File;

import org.junit.jupiter.api.Test;

class ConversionMapLoaderTest {
    
    @Test
    void conversionTest(){
        ConversionMapLoader.loadConversionMap("src" + File.separator + "test" + File.separator +"testfolder" + File.separator + "assets" + File.separator + "bukkit");
        

    }
}
