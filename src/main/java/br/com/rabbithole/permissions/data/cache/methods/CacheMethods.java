package br.com.rabbithole.permissions.data.cache.methods;

import br.com.rabbithole.core.enums.RedisCommands;
import br.com.rabbithole.permissions.Permissions;
import br.com.rabbithole.permissions.enums.Groups;

import java.util.Optional;

public class CacheMethods {
    private static final String KEY = "permissions";

    public static boolean addPlayer(String nick, Groups group) {
        Optional<Boolean> result = Permissions.getRedis().redisQuery(RedisCommands.HASH_SET, KEY, nick, group.name());
        return result.orElse(false);
        //return hashSetQuery(KEY, nick, group.name());
    }

    public static boolean removePlayer(String nick) {
        Optional<Boolean> result = Permissions.getRedis().redisQuery(RedisCommands.HASH_DEL, KEY, nick);
        return result.orElse(false);
        //return hashDelQuery(KEY, nick);
    }

    public static boolean hasPlayer(String nick) {
        Optional<Boolean> result = Permissions.getRedis().redisQuery(RedisCommands.HASH_EXISTS, KEY, nick);
        return result.orElse(false);
        //return hashExistsQuery(KEY, nick);
    }

    public static Groups getPermission(String nick) {
        Optional<String> result = Permissions.getRedis().redisQuery(RedisCommands.HASH_GET, KEY, nick);
        return result.map(Groups::valueOf).orElse(Groups.NOT_FOUND);
        /*
        Optional<String> result = hashGetQuery(KEY, nick);
        return result.map(Groups::valueOf).orElse(Groups.NOT_FOUND);
         */
    }

    public static boolean hasPermission(String nick, Groups group) {
        return group.getHierarchy() <= getPermission(nick).getHierarchy();
    }

    public static boolean updatePlayer(String nick, Groups group) {
        Optional<Boolean> result = Permissions.getRedis().redisQuery(RedisCommands.HASH_SET, KEY, nick, group.name());
        return result.orElse(false);
        //return hashSetQuery(KEY, nick, group.name());
    }
}
