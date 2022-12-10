package org.sentillo.gepard.coloristic;

import java.util.Map;

import org.sentillo.gepard.jumps.BlockType;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.MatrixConverter;
import org.sentillo.gepard.utils.MatrixMapConvertionProgram;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.Named;

import lombok.Getter;

public class Coloristic implements Named{

    @Getter
    private String name;

    private MatrixConverter<BlockType, McBlock> jumpMapConverter;

    public Coloristic(String name, Map<BlockType, McBlock> jumpBlockMap){
        this.name = name;
        jumpMapConverter = new MatrixConverter<>(
            new MatrixMapConvertionProgram<>(jumpBlockMap)
        );
    }
    
    public Matrix3d<McBlock> convertJumpMapToBlocks(Matrix3d<BlockType> matrix) {
        return jumpMapConverter.convert(matrix);
    }
}
