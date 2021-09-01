package net.kunmc.lab.retranslation;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {
    @EventHandler
    public void onChatRecieved(AsyncChatEvent event) {
        System.out.println(event.originalMessage());
    }
}
