package br.com.rabbithole.permissions;

import br.com.rabbithole.permissions.commands.PermissionCommand;
import br.com.rabbithole.permissions.data.cache.RedisConfiguration;
import br.com.rabbithole.permissions.data.sql.WormConfiguration;
import br.com.rabbithole.permissions.events.LoginEvent;
import br.com.rabbithole.permissions.events.QuitEvent;
import br.com.rabbithole.permissions.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Permissions extends JavaPlugin {
    private static PermissionsAPI API = new PermissionsAPI();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(StringUtils.format("<green>Permissions iniciado com Sucesso!"));
        registers();
        Bukkit.getConsoleSender().sendMessage(StringUtils.format(API.toString()));
        Bukkit.getConsoleSender().sendMessage(API.getPermission("FelipeSz_19").toString());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(StringUtils.format("<red>Permissions desativado com Sucesso!"));
        HandlerList.unregisterAll(this);
    }

    void registers() {
        saveDefaultConfig();
        WormConfiguration.init(this);
        RedisConfiguration.init(this);
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

    public static Permissions getInstance() {
        return Permissions.getPlugin(Permissions.class);
    }
}
