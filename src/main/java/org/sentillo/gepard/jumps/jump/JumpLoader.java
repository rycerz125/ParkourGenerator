package org.sentillo.gepard.jumps.jump;

import org.sentillo.gepard.utils.files.FolderLoader;


class JumpLoader extends FolderLoader<Jump>{
    public JumpLoader(){
        super(new JumpParser());
    }
}
