package org.sentillo.gepard.bukkit.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sentillo.gepard.bukkit.mapplacer.GeneratedMapToMinecraftConverter;
import org.sentillo.gepard.generator.ParkourGeneratorService;
import org.sentillo.gepard.generator.ParkourGeneratorSetup;
import org.sentillo.gepard.generator.terrain.TerrainMetadata;
import org.sentillo.gepard.utils.BlockMatrix3d;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.McBlock;
import org.sentillo.gepard.utils.Vector3d;

import java.util.HashMap;

public class GenerateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        ParkourGeneratorService parkourGeneratorService = new ParkourGeneratorService();
        HashMap<String, String> me = new HashMap<>();
        me.put("circleSize", "7");
        TerrainMetadata metadata = new TerrainMetadata(me);
        ParkourGeneratorSetup parkourGeneratorSetup =
                new ParkourGeneratorSetup("only4","Tunnel","Test",metadata);
        Matrix3d<McBlock> blockMatrix3d =
                parkourGeneratorService.generateMap(parkourGeneratorSetup,"seed", 10);

        Location location = player.getLocation();
        placeMap(location, blockMatrix3d);

        return true;
    }
    private void placeMap(Location location,Matrix3d<McBlock> blockMatrix3d){
        Block block;
        McBlock mcBlock;
        for(Vector3d vector3d : blockMatrix3d.getAllLocations()) {
            block = location.clone().add(vector3d.toVector()).getBlock();
            mcBlock = blockMatrix3d.getObject(vector3d);
            if(mcBlock == McBlock.AIR)
                block.setType(Material.AIR);
            if(mcBlock == McBlock.GRASS)
                block.setType(Material.GRASS_BLOCK);
            if(mcBlock == McBlock.WATER)
                block.setType(Material.WATER);
        }
    }
}
