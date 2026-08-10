package me.qq3393525847.sxitemexpansion.config;

import me.qq3393525847.sxitemexpansion.SXItemExpansion;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {

    private final SXItemExpansion plugin;
    private final Map<String, String> messageCache = new HashMap<>();

    public ConfigManager(SXItemExpansion plugin) {
        this.plugin = plugin;
        loadCache();
    }

    /**
     * 从配置文件构建 sxitemkey -> message 的映射缓存，实现 O(1) 查找。
     * 应在插件启用和 reload 时调用。
     */
    public void loadCache() {
        messageCache.clear();
        ConfigurationSection root = plugin.getConfig().getConfigurationSection("Config");
        if (root == null) {
            return;
        }
        for (String ruleKey : root.getKeys(false)) {
            String basePath = "Config." + ruleKey;
            String message = plugin.getConfig().getString(basePath + ".Message");
            List<String> keyList = plugin.getConfig().getStringList(basePath + ".SXItemKey");
            for (String key : keyList) {
                messageCache.put(key, message);
            }
        }
    }

    public String getMessage(String sxitemkey, Player player, String itemname) {
        String message = messageCache.get(sxitemkey);
        if (message == null) {
            return null;
        }
        message = message.replace("{player}", player.getName());
        message = message.replace("{itemname}", itemname);
        return message;
    }
}
