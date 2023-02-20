package br.com.rabbithole.permissions.events;

import br.com.rabbithole.core.enums.Warn;
import br.com.rabbithole.permissions.Permissions;
import br.com.rabbithole.permissions.data.cache.methods.CacheMethods;
import br.com.rabbithole.permissions.data.sql.methods.PermissionsMethods;
import br.com.rabbithole.permissions.enums.Groups;
import br.com.rabbithole.permissions.utils.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class LoginEvent implements Listener {
    final Permissions plugin;

    public LoginEvent(Permissions plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event) {
        if (!PermissionsMethods.hasAccount(event.getName())) {
            if (!PermissionsMethods.createAccount(event.getName())) {
                Permissions.getWarn().sendWarn(Warn.INSERT_CACHE_ERROR);
                event.kickMessage(StringUtils.format("<red><bold>ENTRE NOVAMENTE POR FAVOR!"));
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            }
            if(!CacheMethods.addPlayer(event.getName(), Groups.MEMBER)) Permissions.getWarn().sendWarn(Warn.INSERT_CACHE_ERROR);
        } else {
            if (!CacheMethods.hasPlayer(event.getName())) {
                if(!CacheMethods.addPlayer(event.getName(), Groups.MEMBER)) Permissions.getWarn().sendWarn(Warn.INSERT_CACHE_ERROR);
            }
        }
    }
}
