package org.sentillo.gepard.generator.jumps.jumpsmap;

import org.sentillo.gepard.generator.jumps.jump.JumpService;
import org.sentillo.gepard.utils.files.FolderLoader;

public class JumpsMapLoader extends FolderLoader<JumpsMap>{

    public JumpsMapLoader(JumpService jumpService){
        super(new JumpsMapParser(jumpService));
    }
    
}
