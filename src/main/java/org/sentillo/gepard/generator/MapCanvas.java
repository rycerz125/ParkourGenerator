package org.sentillo.gepard.generator;

import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;

import lombok.Getter;
import lombok.Setter;
import org.sentillo.gepard.utils.Vector3d;

public class MapCanvas {

    @Getter
    @Setter
    private Matrix3d<McBlock> jumpBlocksLayer;

    @Getter
    @Setter
    private Matrix3d<Boolean> mustBeAirLayer;

    @Getter
    @Setter
    private Matrix3d<McBlock> terrainLayer;

    public Matrix3d<McBlock> bakeToOneMap(){
        //// najpierw teren, potem powietrze, potem klocki,
        Matrix3d<McBlock> entireMap = new Matrix3d<>();
        Vector3d vector0 = Vector3d.zero();
        entireMap.place(terrainLayer, vector0);
        entireMap.place(convertBooleanMatrix3dToMcBlockMatrix3d(mustBeAirLayer), vector0);
        entireMap.place(jumpBlocksLayer, vector0);
        return entireMap;
    }
    public static Matrix3d<McBlock> convertBooleanMatrix3dToMcBlockMatrix3d(Matrix3d<Boolean> mustBeAirLayer){
        Matrix3d<McBlock> airMatrix3d = new Matrix3d<>();
        for(Vector3d vector3d : mustBeAirLayer.getAllLocations())
            if(mustBeAirLayer.getObject(vector3d))
                airMatrix3d.setObject(vector3d, McBlock.AIR);
        return airMatrix3d;
    }
}
