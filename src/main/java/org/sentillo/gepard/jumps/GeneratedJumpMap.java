package org.sentillo.gepard.jumps;

import org.sentillo.gepard.utils.Matrix3d;

import lombok.Data;
import net.minecraft.world.level.block.Block;

@Data
public class GeneratedJumpMap {
    private Matrix3d<Block> jumpBlocks;
    private Matrix3d<Boolean> mustBeAir;
    private Matrix3d<Boolean> couldBeAir;
}
