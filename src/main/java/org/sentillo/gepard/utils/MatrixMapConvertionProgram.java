package org.sentillo.gepard.utils;

import java.util.Map;

public class MatrixMapConvertionProgram <T,D> implements MatrixConvertionProgram<T,D>{
    private Map<T,D> convertionMap;

    public MatrixMapConvertionProgram(Map<T,D> convertionMap){
        this.convertionMap = convertionMap;
    }

    public D convert(T toConvert){
        return convertionMap.get(toConvert);
    }
}
