package org.sentillo.gepard.jumps.jumpsmap;

import org.sentillo.gepard.jumps.jump.JumpService;
import org.sentillo.gepard.utils.files.FolderLoader;

public class JumpsMapLoader extends FolderLoader<JumpsMap>{

    public JumpsMapLoader(JumpService jumpService){
        super(new JumpsMapParser(jumpService));
    }
    
}
