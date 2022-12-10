package org.sentillo.gepard.jumps.jumpsmap;

import org.sentillo.gepard.jumps.jump.JumpService;
import org.sentillo.gepard.utils.AccumulationService;

public class JumpsMapService extends AccumulationService<JumpsMap>{
    public JumpsMapService(String assetsFolder, JumpService jumpService){
        super(assetsFolder, new JumpsMapLoader(jumpService));
    }
}
