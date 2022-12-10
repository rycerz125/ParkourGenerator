package org.sentillo.gepard.jumps.jump;

import org.sentillo.gepard.utils.AccumulationService;

public class JumpService extends AccumulationService<Jump>{

    public JumpService(String assetsPath){
        super(assetsPath, new JumpLoader());
    }

}
