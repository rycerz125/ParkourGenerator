package org.sentillo.gepard.generator.terrain.functions;

import java.util.List;

public class Grid {
    public List<Double> dim1;
    public List<Double> dim2;
    public boolean containsIndexes(int xIndex, int zIndex){
        if(xIndex < 0 || zIndex < 0) return false;
        return xIndex < dim1.size() && zIndex < dim2.size();
    }
}
