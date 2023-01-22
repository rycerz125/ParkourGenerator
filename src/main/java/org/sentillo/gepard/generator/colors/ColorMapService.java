package org.sentillo.gepard.generator.colors;

import org.sentillo.gepard.utils.AccumulationService;

public class ColorMapService extends AccumulationService<ColorMap>{
    public ColorMapService(String assetsPath){
        super(assetsPath, new ColorMapLoader());
    }
}