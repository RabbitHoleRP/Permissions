package br.com.rabbithole.permissions.data.cache.methods;

import br.com.rabbithole.permissions.enums.Groups;

import java.util.Optional;

import static br.com.rabbithole.permissions.utils.RedisUtils.*;

public class CacheMethods {
    private static final String KEY = "permissions";

    public static boolean addPlayer(String nick, Groups group) {
        return hashSetQuery(KEY, nick, group.name());
    }

    public static boolean removePlayer(String nick) {
        return hashDelQuery(KEY, nick);
    }

    public static boolean hasPlayer(String nick) {
        return hashExistsQuery(KEY, nick);
    }

    public static Groups getPermission(String nick) {
        Optional<String> result = hashGetQuery(KEY, nick);
        return result.map(Groups::valueOf).orElse(Groups.NOT_FOUND);
    }

    public static boolean hasPermission(String nick, Groups group) {
        return group.getHierarchy() <= getPermission(nick).getHierarchy();
    }

    public static boolean updatePlayer(String nick, Groups group) {
        return hashSetQuery(KEY, nick, group.name());
    }
}
