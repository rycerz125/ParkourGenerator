package org.sentillo.gepard.generator;

import org.sentillo.gepard.colors.ColorMap;
import org.sentillo.gepard.colors.ColorMapService;
import org.sentillo.gepard.jumps.GeneratedJumpLayers;
import org.sentillo.gepard.jumps.JumpsGeneratorService;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.files.FolderPlacing;

public class GeneratorService {
    private JumpsGeneratorService jumpsGeneratorService;
    private ColorMapService colorMapService;

    public GeneratorService(){
        jumpsGeneratorService = new JumpsGeneratorService(FolderPlacing.JUMPS_FOLDER);
        colorMapService = new ColorMapService(FolderPlacing.COLORMAP_FOLDER);
    }

    public Matrix3d<McBlock> generateMap(GeneratorSetup generatorSetup, String seed, int blockCount){
        GeneratedJumpLayers jumpLayers = jumpsGeneratorService.generateJumpLayers(generatorSetup.getJumpMapName(), seed, blockCount);
        ColorMap colorMap = colorMapService.get(generatorSetup.getColorMapName()).get();

        Matrix3d<McBlock> coloredJumps = colorMap.convertJumpMapToBlocks(jumpLayers.getJumpBlocks());

        MapCanvas mapCanvas = new MapCanvas();
        mapCanvas.setJumpBlocksLayer(coloredJumps);
        mapCanvas.setMustBeAirLayer(jumpLayers.getMustBeAir());

        return mapCanvas.bakeToOneMap();
    }
}
