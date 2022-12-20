package br.com.rabbithole.permissions;

import br.com.rabbithole.permissions.data.cache.methods.CacheMethods;
import br.com.rabbithole.permissions.data.sql.methods.PermissionsMethods;
import br.com.rabbithole.permissions.enums.Groups;
import org.bukkit.Bukkit;

import java.util.Objects;

public class PermissionsAPI {
    public Groups getPermission(String nick) {
        if (Objects.requireNonNull(Bukkit.getPlayerExact(nick)).isOnline()) {
            return CacheMethods.getPermission(nick);
        } else {
            if (PermissionsMethods.hasAccount(nick)) {
                return PermissionsMethods.getPermission(nick);
            } else {
                return Groups.NOT_FOUND;
            }
        }
    }

    public boolean hasPermission(String nick, String group) {
        return CacheMethods.hasPermission(nick, Groups.valueOf(group.toUpperCase()));
    }
}
