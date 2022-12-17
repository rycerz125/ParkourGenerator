package org.sentillo.gepard.utils;

public interface Converter<T,D> {
    D convert(T toConvert);
}
