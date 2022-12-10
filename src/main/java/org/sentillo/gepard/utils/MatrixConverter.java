package org.sentillo.gepard.utils;

public class MatrixConverter<T,D> {

    private MatrixConvertionProgram<T,D> mcp;

    public MatrixConverter(MatrixConvertionProgram<T,D> mcp) {
        this.mcp = mcp;
    }
    
    public Matrix3d<D> convert(Matrix3d<T> original){
        Matrix3d<D> newOne = new Matrix3d<>();

        for(Vector3d pos : original.getAllLocations()){
            newOne.setObject(pos.copy(), mcp.convert(original.getObject(pos)));
        }

        return newOne;
    }
}
