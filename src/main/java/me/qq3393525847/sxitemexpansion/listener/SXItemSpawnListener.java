package me.qq3393525847.sxitemexpansion.listener;

import github.saukiya.sxitem.event.SXItemSpawnEvent;
import me.qq3393525847.sxitemexpansion.SXItemExpansion;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.ItemMeta;

public class SXItemSpawnListener implements Listener {

    private final SXItemExpansion plugin;

    public SXItemSpawnListener(SXItemExpansion plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSXItemSpawn(SXItemSpawnEvent event) {
        String sxitemkey = event.getIg().getKey();
        ItemMeta meta = event.getItem().getItemMeta();
        String itemname = meta != null ? meta.getDisplayName() : event.getItem().getType().name();
        String msg = plugin.getConfigManager().getMessage(sxitemkey, event.getPlayer(), itemname);
        if (msg != null) {
            Bukkit.broadcastMessage(msg);
        }
    }
}
