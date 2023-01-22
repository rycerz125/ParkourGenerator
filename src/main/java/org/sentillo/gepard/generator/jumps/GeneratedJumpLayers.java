package org.sentillo.gepard.generator.jumps;

import org.sentillo.gepard.utils.BlockMatrix3d;
import org.sentillo.gepard.utils.Matrix3d;

import lombok.Data;

@Data
public class GeneratedJumpLayers {
    private BlockMatrix3d jumpBlocks = new BlockMatrix3d();
    private Matrix3d<Boolean> mustBeAir = new Matrix3d<>();
    private Matrix3d<Boolean> couldBeAir = new Matrix3d<>();
}
