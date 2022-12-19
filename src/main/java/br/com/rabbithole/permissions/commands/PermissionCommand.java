package br.com.rabbithole.permissions.commands;

import br.com.rabbithole.permissions.data.cache.methods.CacheMethods;
import br.com.rabbithole.permissions.data.sql.methods.PermissionsMethods;
import br.com.rabbithole.permissions.enums.Groups;
import br.com.rabbithole.permissions.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PermissionCommand implements CommandExecutor {

    public PermissionCommand() {
        PluginCommand command = Objects.requireNonNull(Bukkit.getPluginCommand("permissions"));
        command.setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtils.format("<red>Apenas jogadores podem executar este Comando!"));
            return true;
        }

        if (!CacheMethods.hasPermission(player.getName(), Groups.COORDINATOR)) {
            player.sendMessage(StringUtils.format("<red>Você não tem permissão para executar este Comando!"));
            return true;
        }

        if (args.length < 1) {
            sendHelp(player);
            return true;
        }

        switch (args[0]) {
            case "verificar" -> verifyCommand(player, args);
            case "atualizar" -> updateCommand(player, args);
        }

        return false;
    }

    private void updateCommand(Player player, String[] args) {
        if (args.length < 3) {
            player.sendMessage(StringUtils.format("<red>Utilize: /perm atualizar <Nick> <Grupo>"));
            return;
        }

        if (!CacheMethods.hasPlayer(args[1])) {
            player.sendMessage(StringUtils.format("<red>O jogador não está Online!"));
            return;
        }

        Groups group = verifyGroup(args[2]);
        if (group == Groups.NOT_FOUND) {
            player.sendMessage(StringUtils.format("<red>O Grupo inserido não Existe!"));
            return;
        }

        if (group == Groups.ADMIN) {
            if (CacheMethods.getPermission(player.getName()).equals(Groups.COORDINATOR)) {
                player.sendMessage(StringUtils.format("<red>Você não pode atualizar para um Cargo maior que o seu!"));
            return;
            }
        }

        if (!CacheMethods.updatePlayer(args[1], group)) {
            player.sendMessage(StringUtils.format("<red>Erro ao atualizar Grupo de Jogador!"));
            return;
        }

        player.sendMessage(StringUtils.format("<green>Grupo do jogador %s foi atualizado para %s".formatted(args[1], group.getTag())));
    }

    private void verifyCommand(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(StringUtils.format("<red>Utilize: /perm verificar <Nick>!"));
            return;
        }

        if (CacheMethods.hasPlayer(args[1])) {
            player.sendMessage(StringUtils.format("<green>O Grupo do jogador %s é %s.".formatted(args[1], CacheMethods.getPermission(args[1]).getTag())));
        } else {
            if (PermissionsMethods.hasAccount(args[1])) {
                player.sendMessage(StringUtils.format("<green>O Grupo do jogador %s é %s.".formatted(args[1], PermissionsMethods.getPermission(args[1]).getTag())));
            } else {
                player.sendMessage(StringUtils.format("<red>O jogador não foi Encontrado!"));
            }
        }
    }

    private void sendHelp(Player player) {
        player.sendMessage(StringUtils.format("<green>Lista de Comandos:"));
        player.sendMessage(StringUtils.format(""));
        player.sendMessage(StringUtils.format("<green>/perm verificar <Nick> -> Retorna a Permissão do Jogador!"));
        player.sendMessage(StringUtils.format("<green>/perm atualizar <Nick> <Grupo> -> Atualiza a Permissão do Jogador!"));
    }

    private Groups verifyGroup(String groupString) {
        try {
            return Groups.valueOf(groupString.toUpperCase());
        } catch (Exception exception) {
            return Groups.NOT_FOUND;
        }
    }
}
