package br.com.rabbithole.permissions;

import br.com.rabbithole.WarnUtils;
import br.com.rabbithole.core.WarnExecutor;
import br.com.rabbithole.permissions.commands.PermissionCommand;
import br.com.rabbithole.permissions.data.cache.RedisConfiguration;
import br.com.rabbithole.permissions.data.sql.WormConfiguration;
import br.com.rabbithole.permissions.events.LoginEvent;
import br.com.rabbithole.permissions.events.QuitEvent;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Permissions extends JavaPlugin {
    private static PermissionsAPI API = new PermissionsAPI();

    @Override
    public void onEnable() {
        // Plugin startup logic
        WarnUtils.getWarn().sendWarn("<red>iniciado com Sucesso!");
        registers();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        WarnUtils.getWarn().sendWarn("<red>desativado com Sucesso!");
        HandlerList.unregisterAll(this);
    }

    void registers() {
        saveDefaultConfig();
        WormConfiguration.init(this);
        RedisConfiguration.init(this);
        commands();
        events();
        WarnUtils.warnInitializer("Permissions");
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
        return WarnUtils.getWarn();
    }

    public static Permissions getInstance() {
        return Permissions.getPlugin(Permissions.class);
    }
}
