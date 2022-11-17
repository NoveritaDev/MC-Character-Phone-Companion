package me.noverita.ttrpgplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class TTRPGPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Launcher.launch();
    }
}
