package br.com.rabbithole.permissions.data.sql.methods;

import br.com.rabbithole.permissions.data.sql.tables.PermissionsTable;
import br.com.rabbithole.permissions.enums.Groups;

public class PermissionsMethods {
    public static boolean createAccount(String nick) {
        try (PermissionsTable account = new PermissionsTable(nick, Groups.MEMBER.name())) {
            if (!account.find()) return account.insert();
        }
        return false;
    }

    public static boolean hasAccount(String nick) {
        try (PermissionsTable account = new PermissionsTable(nick)) {
            return account.find();
        }
    }

    public static Groups getPermission(String nick) {
        try (PermissionsTable account = new PermissionsTable(nick)) {
            if (account.find()) {
                return Groups.valueOf(account.getPlayerGroup());
            } else {
                return Groups.NOT_FOUND;
            }
        }
    }

    public static boolean updateAccount(String nick, Groups group) {
        try (PermissionsTable account = new PermissionsTable(nick)) {
            if (account.find()) {
                account.setPlayerGroup(group.name());
                return account.update();
            } else {
                return false;
            }
        }
    }
}
