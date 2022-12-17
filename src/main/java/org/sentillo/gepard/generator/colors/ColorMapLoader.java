package org.sentillo.gepard.generator.colors;

import org.sentillo.gepard.utils.files.FolderLoader;

class ColorMapLoader extends FolderLoader<ColorMap>{
    public ColorMapLoader(){
        super(new ColorMapParser());
    }
}
