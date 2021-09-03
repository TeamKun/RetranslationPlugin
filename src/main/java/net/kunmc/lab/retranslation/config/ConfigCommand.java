package net.kunmc.lab.retranslation.config;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.kunmc.lab.retranslation.RetranslationPlugin;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.CommandListenerWrapper;
import org.bukkit.ChatColor;

public class ConfigCommand {
    public static void register(CommandDispatcher<CommandListenerWrapper> dispatcher) {
        LiteralArgumentBuilder<CommandListenerWrapper> builder = LiteralArgumentBuilder.<CommandListenerWrapper>literal("rtconfig")
                .requires(clw -> clw.getBukkitSender().hasPermission("retranslation.configcommand"));
        builder.then(net.minecraft.server.v1_16_R3.CommandDispatcher.a("reload")
                .executes(ConfigCommand::reload));
        LiteralArgumentBuilder<CommandListenerWrapper> setBuilder = net.minecraft.server.v1_16_R3.CommandDispatcher.a("set");
        for (String path : ConfigManager.getConfigPaths()) {
            setBuilder.then(net.minecraft.server.v1_16_R3.CommandDispatcher.a(path)
                    .then(net.minecraft.server.v1_16_R3.CommandDispatcher.a("value", StringArgumentType.greedyString())
                    .executes(context -> set(context, path))));
        }
        LiteralArgumentBuilder<CommandListenerWrapper> getBuilder = net.minecraft.server.v1_16_R3.CommandDispatcher.a("get");
        for (String path : ConfigManager.getConfigPaths()) {
            getBuilder.then(net.minecraft.server.v1_16_R3.CommandDispatcher.a(path)
                    .executes(context -> get(context, path)));
        }
        builder.then(setBuilder);
        builder.then(getBuilder);
        dispatcher.register(builder);
    }

    private static int get(CommandContext<CommandListenerWrapper> context, String path) {
        ConfigManager configManager = RetranslationPlugin.getInstance().getConfigManager();
        String value = configManager.get(path);
        context.getSource().sendMessage(new ChatComponentText(value), false);
        return 0;
    }

    private static int set(CommandContext<CommandListenerWrapper> context, String path) {
        ConfigManager configManager = RetranslationPlugin.getInstance().getConfigManager();
        String value = StringArgumentType.getString(context, "value");
        boolean result = configManager.setConfig(path, value);
        if (result) {
            context.getSource().sendMessage(new ChatComponentText(path + "を" + value + "にセットしました"), false);
        } else {
            context.getSource().sendMessage(new ChatComponentText(ChatColor.RED + "コンフィグの設定に失敗しました"), false);
        }
        return 0;
    }

    private static int reload(CommandContext<CommandListenerWrapper> context) {
        ConfigManager configManager = RetranslationPlugin.getInstance().getConfigManager();
        configManager.load();
        context.getSource().sendMessage(new ChatComponentText("コンフィグをリロードしました"), false);
        return 0;
    }
}
