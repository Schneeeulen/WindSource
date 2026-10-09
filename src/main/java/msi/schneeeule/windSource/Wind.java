package msi.schneeeule.windSource;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wind extends JavaPlugin {

    private static Wind instance;

    @Override
    public void onEnable() {
        instance = this;
        registerListener(instance);
    }

    @Override
    public void onDisable() {

    }

    public static Wind getInstance() {
        return instance;
    }

    public static void registerListener(Plugin plugin) {
        PluginManager plm = plugin.getServer().getPluginManager();
    }

}
