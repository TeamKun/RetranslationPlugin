package net.kunmc.lab.retranslation;

import com.mojang.brigadier.CommandDispatcher;
import net.kunmc.lab.retranslation.config.ConfigCommand;
import net.kunmc.lab.retranslation.config.ConfigManager;
import net.minecraft.server.v1_16_R3.CommandListenerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public final class RetranslationPlugin extends JavaPlugin {
    private static RetranslationPlugin instance;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager();
        configManager.load();
        CommandDispatcher<CommandListenerWrapper> dispatcher = ((CraftServer)Bukkit.getServer()).getServer().vanillaCommandDispatcher.a();
        ConfigCommand.register(dispatcher);
        RelayCommand.register(dispatcher);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
    }

    public static RetranslationPlugin getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
