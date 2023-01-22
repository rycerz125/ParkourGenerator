package org.sentillo.gepard.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatrixConverterTest {
    
    @Test
    public void testConverter(){
        Matrix3d<Boolean> matrix = new Matrix3d<>();
        matrix.setObject(Vector3d.of(0, 0, 0), true);

        Converter<Boolean,Integer> mcp = (bool) -> {
            if(bool == true)
                return 5;
            else
                return null;
        };

        MatrixConverter<Boolean,Integer> converter = new MatrixConverter<>(mcp);

        Matrix3d<Integer> convertedMatrix = converter.convert(matrix);

        Assertions.assertEquals(5, convertedMatrix.getObject(Vector3d.of(0,0,0)));
    }
}
