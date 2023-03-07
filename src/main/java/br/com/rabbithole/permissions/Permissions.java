package br.com.rabbithole.permissions;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.WarnUtils;
import br.com.rabbithole.core.RedisExecutor;
import br.com.rabbithole.core.WarnExecutor;
import br.com.rabbithole.permissions.commands.PermissionCommand;
import br.com.rabbithole.permissions.data.sql.WormConfiguration;
import br.com.rabbithole.permissions.events.LoginEvent;
import br.com.rabbithole.permissions.events.QuitEvent;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Permissions extends JavaPlugin {
    private static final PermissionsAPI API = new PermissionsAPI();
    private static final WarnUtils warn = new WarnUtils("Permissions");
    private static final RedisLib redis = new RedisLib("Permissions", "localhost", 6379, "default", "1234");

    @Override
    public void onEnable() {
        // Plugin startup logic
        getWarn().sendWarn("<green>[Permissions] iniciado com Sucesso!");
        registers();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getWarn().sendWarn("<red>[Permissions] desativado com Sucesso!");
        HandlerList.unregisterAll(this);
    }

    void registers() {
        saveDefaultConfig();
        WormConfiguration.init(this);
        commands();
        events();
    }

    void commands() {
        new PermissionCommand();
    }

    void events() {
        new LoginEvent(this);
        new QuitEvent(this);
    }

    public static PermissionsAPI getAPI() {
        return API;
    }

    public static WarnExecutor getWarn() {
        return warn.getWarn();
    }

    public static RedisExecutor getRedis() {
        return redis.getRedis();
    }

    public static Permissions getInstance() {
        return Permissions.getPlugin(Permissions.class);
    }
}
