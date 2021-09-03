package net.kunmc.lab.retranslation.config;

import net.kunmc.lab.retranslation.RetranslationPlugin;
import net.kunmc.lab.retranslation.config.parser.BooleanParser;
import net.kunmc.lab.retranslation.config.parser.Parser;
import net.kunmc.lab.retranslation.config.parser.StringListParser;
import net.kunmc.lab.retranslation.config.parser.StringParser;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {
    private static final Map<String, Parser<?>> CONFIGS = new HashMap<>() {{
        put("deployId", new StringParser());
        put("errorText", new StringParser());
        put("showOriginal", new BooleanParser());
        put("enabled", new BooleanParser());
        put("relays", new StringListParser());
    }};
    private FileConfiguration config;

    public static String[] getConfigPaths() {
        return CONFIGS.keySet().toArray(new String[0]);
    }

    public void load() {
        RetranslationPlugin plugin = RetranslationPlugin.getInstance();
        plugin.saveDefaultConfig();
        if (config != null) {
            plugin.reloadConfig();
        }
        config = plugin.getConfig();
    }

    public boolean setConfig(String path, String valueString) {
        if (!CONFIGS.containsKey(path)) {
            return false;
        }
        Parser<?> parser = CONFIGS.get(path);
        Object value = parser.parse(valueString);
        return setConfig(path, value);
    }

    private boolean setConfig(String path, Object value) {
        if (value == null) {
            return false;
        }
        RetranslationPlugin plugin = RetranslationPlugin.getInstance();
        config.set(path, value);
        plugin.saveConfig();
        return true;
    }

    public String get(String path) {
        return String.valueOf(config.get(path));
    }

    public String getDeployId() {
        return config.getString("deployId");
    }

    public String getErrorText() {
        return config.getString("errorText");
    }

    public boolean showOriginal() {
        return config.getBoolean("showOriginal");
    }

    public boolean isEnabled() {
        return config.getBoolean("enabled");
    }

    public List<String> getRelays() {
        return config.getStringList("relays");
    }
}
