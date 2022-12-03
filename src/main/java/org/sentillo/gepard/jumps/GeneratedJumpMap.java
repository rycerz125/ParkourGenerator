package org.sentillo.gepard.jumps;

import org.sentillo.gepard.utils.BlockMatrix3d;
import org.sentillo.gepard.utils.Matrix3d;

import lombok.Data;

@Data
public class GeneratedJumpMap {
    private BlockMatrix3d jumpBlocks;
    private Matrix3d<Boolean> mustBeAir;
    private Matrix3d<Boolean> couldBeAir;
}
