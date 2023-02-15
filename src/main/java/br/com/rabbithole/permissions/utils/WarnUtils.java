package br.com.rabbithole.permissions.utils;

import br.com.rabbithole.permissions.enums.Warns;
import org.bukkit.Bukkit;

public class WarnUtils {
    public static void sendWarn(Warns warn) {
        Bukkit.getConsoleSender().sendMessage(StringUtils.format(warn.getMessage()));
    }
}
