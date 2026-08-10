package me.qq3393525847.sxitemexpansion.command;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import java.util.List;
import me.qq3393525847.sxitemexpansion.SXItemExpansion;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class MainCommand {

    private final SXItemExpansion plugin;

    public MainCommand(SXItemExpansion plugin) {
        this.plugin = plugin;
    }

    public void register(LifecycleEventManager<Plugin> manager) {
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            Commands commands = event.registrar();
            commands.register(
                Commands.literal("sxitemexpansion")
                        .then(Commands.literal("reload")
                                .requires(src -> src.getSender().hasPermission("sxitemexpansion.reload"))
                                .executes(ctx -> executeReload(ctx.getSource())))
                        .build(),
                "SXItemExpansion 主指令",
                List.of("sxie")                            
            );
        });
    }
    
    private int executeReload(CommandSourceStack source) {
        CommandSender sender = source.getSender();
        plugin.reloadConfig();
        plugin.getConfigManager().loadCache();
        sender.sendMessage("SXItemExpansion 重载成功");
        return 1;
    }
}
