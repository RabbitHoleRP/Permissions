package br.com.rabbithole.permissions.events;

import br.com.rabbithole.permissions.Permissions;
import br.com.rabbithole.permissions.data.cache.methods.CacheMethods;
import br.com.rabbithole.permissions.data.sql.methods.PermissionsMethods;
import br.com.rabbithole.permissions.enums.Groups;
import br.com.rabbithole.permissions.enums.Warns;
import br.com.rabbithole.permissions.utils.StringUtils;
import br.com.rabbithole.permissions.utils.WarnUtils;
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
                WarnUtils.sendWarn(Warns.REGISTER_PLAYER_ERROR);
                event.kickMessage(StringUtils.format("<red><bold>ENTRE NOVAMENTE POR FAVOR!"));
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            }
            if(!CacheMethods.addPlayer(event.getName(), Groups.MEMBER)) WarnUtils.sendWarn(Warns.INSERT_CACHE_ERROR);
        } else {
            if (!CacheMethods.hasPlayer(event.getName())) {
                if(!CacheMethods.addPlayer(event.getName(), Groups.MEMBER)) WarnUtils.sendWarn(Warns.INSERT_CACHE_ERROR);
            }
        }
    }
}
