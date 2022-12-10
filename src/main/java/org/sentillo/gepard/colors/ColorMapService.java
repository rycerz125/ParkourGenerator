package org.sentillo.gepard.colors;

import org.sentillo.gepard.utils.AccumulationService;

public class ColorMapService extends AccumulationService<ColorMap>{
    public ColorMapService(String assetsPath){
        super(assetsPath, new ColorMapLoader());
    }
}