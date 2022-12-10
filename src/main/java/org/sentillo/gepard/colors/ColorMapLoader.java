package org.sentillo.gepard.colors;

import org.sentillo.gepard.utils.files.FolderLoader;

public class ColorMapLoader extends FolderLoader<ColorMap>{
    public ColorMapLoader(){
        super(new ColorMapParser());
    }
}
