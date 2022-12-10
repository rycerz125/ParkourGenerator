package org.sentillo.gepard.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class TextParser<T> {

    private String parseSplit;

    public TextParser(String parseSplit){
        this.parseSplit = parseSplit;
    }

    public List<T> parse(String code){
        String[] codes = code.trim().split(parseSplit+".");
        List<T> jumps = new ArrayList<>();

        for(String c : codes){
            c = c.trim();
            if(!c.equals(""))
            jumps.add(parseOne(parseSplit+" " + c));
        }

        return jumps;
    }

    public abstract T parseOne(String code);
}
