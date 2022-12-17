package org.sentillo.gepard.generator;

import org.sentillo.gepard.generator.colors.ColorMap;
import org.sentillo.gepard.generator.colors.ColorMapService;
import org.sentillo.gepard.generator.jumps.GeneratedJumpLayers;
import org.sentillo.gepard.generator.jumps.JumpsGeneratorService;
import org.sentillo.gepard.generator.terrain.TerrainColor;
import org.sentillo.gepard.generator.terrain.TerrainGeneratorService;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.files.FolderPlacing;

public class ParkourGeneratorService {
    private JumpsGeneratorService jumpsGeneratorService;
    private ColorMapService colorMapService;
    private TerrainGeneratorService terrainGeneratorService;

    public ParkourGeneratorService(){
        jumpsGeneratorService = new JumpsGeneratorService(FolderPlacing.JUMPS_FOLDER);
        colorMapService = new ColorMapService(FolderPlacing.COLORMAP_FOLDER);
        terrainGeneratorService = new TerrainGeneratorService(FolderPlacing.TERRAIN_FOLDER);
    }

    public Matrix3d<McBlock> generateMap(ParkourGeneratorSetup parkourGeneratorSetup, String seed, int jumpCount){
        GeneratedJumpLayers jumpLayers = jumpsGeneratorService.generateJumpLayers(parkourGeneratorSetup.getJumpMapName(), seed, jumpCount);
        ColorMap colorMap = colorMapService.get(parkourGeneratorSetup.getColorMapName()).get();

        Matrix3d<McBlock> coloredJumps = colorMap.convertJumpMapToBlocks(jumpLayers.getJumpBlocks());

        MapCanvas mapCanvas = new MapCanvas();
        mapCanvas.setJumpBlocksLayer(coloredJumps);
        mapCanvas.setMustBeAirLayer(jumpLayers.getMustBeAir());

        Matrix3d<TerrainColor> terrain = terrainGeneratorService.generate(parkourGeneratorSetup.getTerrainGeneratorName(), parkourGeneratorSetup.getTerrainMetadata(), seed, mapCanvas.getMustBeAirLayer(), mapCanvas.getJumpBlocksLayer());
        Matrix3d<McBlock> coloredTerrain = colorMap.convertTerrainColorMapToBlocks(terrain);

        mapCanvas.setTerrainLayer(coloredTerrain);

        return mapCanvas.bakeToOneMap();
    }
}
