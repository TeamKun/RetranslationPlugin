package net.kunmc.lab.retranslation;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kunmc.lab.retranslation.config.ConfigManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {
    @EventHandler
    public void onChatRecieved(AsyncChatEvent event) {
        ConfigManager configManager = RetranslationPlugin.getInstance().getConfigManager();
        if (!(event.message() instanceof TextComponent) || !configManager.isEnabled()) {
            return;
        }
        String content = ((TextComponent)event.message()).content();
        String retranslated;
        try {
            retranslated = Retranslation.retranslate(content, configManager.getRelays().toArray(new String[0]));
        } catch (Exception e) {
            e.printStackTrace();
            retranslated = configManager.getErrorText();
        }
        TextComponent component = Component.text(retranslated);
        if (configManager.showOriginal()) {
            component = component.hoverEvent(HoverEvent.showText(Component.text(content)));
        }
        event.message(component);
    }
}
