package org.sentillo.gepard.bukkit;

import java.io.File;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

public class GepardPlugin extends JavaPlugin{
    public GepardPlugin()
    {
        super();
    }

    protected GepardPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file)
    {
        super(loader, description, dataFolder, file);
    }
}
