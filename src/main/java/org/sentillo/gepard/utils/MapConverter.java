package org.sentillo.gepard.utils;

import java.util.Map;

public class MapConverter<T,D> implements Converter<T,D> {
    private final Map<T,D> convertionMap;

    public MapConverter(Map<T,D> convertionMap){
        this.convertionMap = convertionMap;
    }

    public D convert(T toConvert){
        return convertionMap.get(toConvert);
    }
}
