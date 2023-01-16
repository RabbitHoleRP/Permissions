package br.com.rabbithole.permissions;

import br.com.rabbithole.permissions.data.cache.methods.CacheMethods;
import br.com.rabbithole.permissions.data.sql.methods.PermissionsMethods;
import br.com.rabbithole.permissions.enums.Groups;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PermissionsAPI {
    public Groups getPermission(String nick) {
        Player player = Bukkit.getPlayerExact(nick);
        if (player == null) {
            if (PermissionsMethods.hasAccount(nick)) {
                return PermissionsMethods.getPermission(nick);
            } else {
                return CacheMethods.getPermission(nick);
            }
        } else {
            return Groups.NOT_FOUND;
        }
    }

    public boolean hasPermission(String nick, Groups group) {
        Player player = Bukkit.getPlayerExact(nick);
        if (player == null) return false;
        return CacheMethods.hasPermission(player.getName(), group);
    }
}
