package org.sentillo.gepard.generator.colors;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.generator.colors.ColorMap;
import org.sentillo.gepard.generator.colors.ColorMapLoader;

public class ColorMapLoaderTest {
    
    @Test
    public void testLoader(){
        ColorMapLoader loader = new ColorMapLoader();
        List<ColorMap> loadedColoristics = loader.load("src" + File.separator + "test" + File.separator + "testfolder" + File.separator + "assets" + File.separator + "coloristics");
        Assertions.assertEquals(1, loadedColoristics.size());
    }
}
