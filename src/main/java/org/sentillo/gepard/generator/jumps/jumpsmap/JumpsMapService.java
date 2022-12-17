package org.sentillo.gepard.generator.jumps.jumpsmap;

import org.sentillo.gepard.generator.jumps.jump.JumpService;
import org.sentillo.gepard.utils.AccumulationService;

public class JumpsMapService extends AccumulationService<JumpsMap>{
    public JumpsMapService(String assetsFolder, JumpService jumpService){
        super(assetsFolder, new JumpsMapLoader(jumpService));
    }
}
