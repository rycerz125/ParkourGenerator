package org.sentillo.gepard.generator.jumps.jump;

import org.sentillo.gepard.utils.files.FolderLoader;


public class JumpLoader extends FolderLoader<Jump>{
    public JumpLoader(){
        super(new JumpParser());
    }
}
