package org.sentillo.gepard.jumps.specifications;

import org.sentillo.gepard.jumps.jump.JumpService;
import org.sentillo.gepard.utils.files.FolderLoader;

public class JumpMapLoader extends FolderLoader<JumpsMap>{

    public JumpMapLoader(JumpService jumpService){
        super(new JumpsMapParser(jumpService));
    }
    
}
