package org.sentillo.gepard.bukkit;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.sentillo.gepard.bukkit.commands.GenerateCommand;

import java.io.File;

public class GepardPlugin extends JavaPlugin{
    public GepardPlugin()
    {
        super();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        registerCommands();
    }

    protected GepardPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file)
    {
        super(loader, description, dataFolder, file);
    }
    private void registerCommands(){
        this.getCommand("generate").setExecutor(new GenerateCommand());
    }
}
