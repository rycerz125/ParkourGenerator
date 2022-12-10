package org.sentillo.gepard.coloristic;

import org.sentillo.gepard.utils.AccumulationService;

public class ColoristicService extends AccumulationService<Coloristic>{
    public ColoristicService(String assetsPath){
        super(assetsPath, new ColoristicLoader());
    }
}