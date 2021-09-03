package net.kunmc.lab.retranslation;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.kunmc.lab.retranslation.config.ConfigManager;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.CommandListenerWrapper;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class RelayCommand {
    public static void register(CommandDispatcher<CommandListenerWrapper> dispatcher) {
        LiteralArgumentBuilder<CommandListenerWrapper> builder = LiteralArgumentBuilder.<CommandListenerWrapper>literal("relay")
                .requires(clw -> clw.getBukkitSender().hasPermission("retranslation.relaycommand"));
        builder.then(net.minecraft.server.v1_16_R3.CommandDispatcher.a("relays", StringArgumentType.greedyString())
                .executes(RelayCommand::execute));
        builder.then(net.minecraft.server.v1_16_R3.CommandDispatcher.a("random")
                .then(net.minecraft.server.v1_16_R3.CommandDispatcher.a("n", IntegerArgumentType.integer(1))
                        .executes(RelayCommand::random)));
        dispatcher.register(builder);
    }

    private static int random(CommandContext<CommandListenerWrapper> context) {
        int n = IntegerArgumentType.getInteger(context, "n");
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        String[] relays;
        do {
            relays = new String[n];
            for (int i = 0; i < n; i++) {
                relays[i] = Language.values()[rand.nextInt(Language.values().length - 1)].getId();
            }
        } while (!canSet(context, relays, false));
        set(context, relays);
        return 0;
    }

    private static void set(CommandContext<CommandListenerWrapper> context, String[] relays) {
        ConfigManager configManager = RetranslationPlugin.getInstance().getConfigManager();
        configManager.setConfig("relays", String.join(" ", relays));
        String[] languageNames = Arrays.stream(relays)
                .map(Language::getLanguageById)
                .map(Language::getLanguageName)
                .toArray(String[]::new);
        context.getSource().sendMessage(new ChatComponentText("中継言語を " + Arrays.toString(languageNames) + " に設定しました"), false);
    }

    private static int execute(CommandContext<CommandListenerWrapper> context) {
        String relay = StringArgumentType.getString(context, "relays");
        String[] relays = relay.split(" ");
        boolean canSet = canSet(context, relays, true);
        if (canSet) {
            set(context, relays);
        }
        return 0;
    }

    private static boolean canSet(CommandContext<CommandListenerWrapper> context, String[] relays, boolean putMessage) {
        for (String language : relays) {
            if (Language.getLanguageById(language) == null) {
                if (putMessage) {
                    context.getSource().sendMessage(new ChatComponentText(language + "は存在しません"), false);
                }
                return false;
            }
        }
        try {
            Retranslation.retranslate(".", relays);
            return true;
        } catch (Exception e) {
            context.getSource().sendMessage(new ChatComponentText(ChatColor.RED + e.getMessage()), false);
            return false;
        }
    }
}
