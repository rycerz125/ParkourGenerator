package org.sentillo.gepard.coloristic;

import org.sentillo.gepard.utils.files.FolderLoader;

public class ColoristicLoader extends FolderLoader<Coloristic>{
    public ColoristicLoader(){
        super(new ColoristicParser());
    }
}
