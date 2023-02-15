package br.com.rabbithole.permissions.utils;

import br.com.rabbithole.permissions.data.cache.RedisConfiguration;
import org.bukkit.Bukkit;
import redis.clients.jedis.Jedis;

import java.util.Optional;

public class RedisUtils {
    public static boolean hashSetQuery(String key, String field, String value) {
        try (Jedis jedis = RedisConfiguration.getJedis().getResource()) {
            jedis.hset(key, field, value);
            return true;
        } catch (Exception exception) {
            Bukkit.getConsoleSender().sendMessage(StringUtils.format("<red> Redis Exception!"));
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean hashDelQuery(String key, String field) {
        try (Jedis jedis = RedisConfiguration.getJedis().getResource()) {
            jedis.hdel(key, field);
            return true;
        } catch (Exception exception) {
            Bukkit.getConsoleSender().sendMessage(StringUtils.format("<red> Redis Exception!"));
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean hashExistsQuery(String key, String field) {
        try (Jedis jedis = RedisConfiguration.getJedis().getResource()) {
            return jedis.hexists(key, field);
        } catch (Exception exception) {
            Bukkit.getConsoleSender().sendMessage(StringUtils.format("<red> Redis Exception!"));
            exception.printStackTrace();
            return false;
        }
    }

    public static Optional<String> hashGetQuery(String key, String field) {
        try (Jedis jedis = RedisConfiguration.getJedis().getResource()) {
            return Optional.of(jedis.hget(key, field));
        } catch (Exception exception) {
            Bukkit.getConsoleSender().sendMessage(StringUtils.format("<red> Redis Exception!"));
            exception.printStackTrace();
            return Optional.empty();
        }
    }
}
