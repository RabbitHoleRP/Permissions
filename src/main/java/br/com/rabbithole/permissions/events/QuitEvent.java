package br.com.rabbithole.permissions.events;

import br.com.rabbithole.core.enums.Warn;
import br.com.rabbithole.permissions.Permissions;
import br.com.rabbithole.permissions.data.cache.methods.CacheMethods;
import br.com.rabbithole.permissions.data.sql.methods.PermissionsMethods;
import br.com.rabbithole.permissions.enums.Groups;
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
        Groups playerGroup = CacheMethods.getPermission(player.getName());

        if (!PermissionsMethods.updateAccount(player.getName(), playerGroup.name())) {
            Permissions.getWarn().sendWarn(Warn.UPDATE_DATABASE_ERROR);
        }

        if (!CacheMethods.removePlayer(player.getName())) {
            Permissions.getWarn().sendWarn(Warn.DELETE_CACHE_ERROR);
        }
    }
}
