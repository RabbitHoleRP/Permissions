package br.com.rabbithole.permissions.events;

import br.com.rabbithole.permissions.Permissions;
import br.com.rabbithole.permissions.data.cache.methods.CacheMethods;
import br.com.rabbithole.permissions.data.sql.methods.PermissionsMethods;
import br.com.rabbithole.permissions.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    final Permissions plugin;

    public QuitEvent(Permissions plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(!PermissionsMethods.updateAccount(player.getName(), CacheMethods.getPermission(player.getName()))) {
            Bukkit.getConsoleSender().sendMessage(StringUtils.format("<red>Error while updating Player informations in Quit Event!"));
        }
        CacheMethods.removePlayer(player.getName());
    }
}
