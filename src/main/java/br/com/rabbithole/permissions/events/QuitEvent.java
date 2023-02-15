package br.com.rabbithole.permissions.events;

import br.com.rabbithole.permissions.Permissions;
import br.com.rabbithole.permissions.data.cache.methods.CacheMethods;
import br.com.rabbithole.permissions.data.sql.methods.PermissionsMethods;
import br.com.rabbithole.permissions.enums.Warns;
import br.com.rabbithole.permissions.utils.WarnUtils;
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
            WarnUtils.sendWarn(Warns.UPDATING_PLAYER_ERROR);
        }
        if(!CacheMethods.removePlayer(player.getName())) {
            WarnUtils.sendWarn(Warns.REMOVE_CACHE_ERROR);
        }
    }
}
