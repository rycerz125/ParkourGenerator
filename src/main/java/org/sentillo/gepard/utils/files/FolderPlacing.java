package org.sentillo.gepard.utils.files;

import java.io.File;

public class FolderPlacing {

    private FolderPlacing(){}
    public static final String SEP = File.separator;
    public static final String ASSETS_FOLDER = "data";
    public static final String JUMPS_FOLDER = ASSETS_FOLDER + SEP + "jumps" + SEP + "jump";
    public static final String COLORMAP_FOLDER = ASSETS_FOLDER + SEP + "colors";
    public static final String TERRAIN_FOLDER = ASSETS_FOLDER + SEP + "terrains";
}
