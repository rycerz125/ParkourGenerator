package org.sentillo.gepard.coloristic;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColoristicLoaderTest {
    
    @Test
    public void testLoader(){
        ColoristicLoader loader = new ColoristicLoader();
        List<Coloristic> loadedColoristics = loader.load("src" + File.separator + "test" + File.separator + "testfolder" + File.separator + "assets" + File.separator + "coloristics");
        Assertions.assertEquals(1, loadedColoristics.size());
    }
}
