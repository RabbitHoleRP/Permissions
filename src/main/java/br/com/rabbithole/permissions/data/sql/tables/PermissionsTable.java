package br.com.rabbithole.permissions.data.sql.tables;

import dev.gump.worm.WormField;
import dev.gump.worm.WormTable;

public class PermissionsTable extends WormTable {
    @WormField(sqlType = "VARCHAR", length = 16, primaryKey = true)
    private String playerNick;

    @WormField(sqlType = "VARCHAR", length = 16)
    private String playerGroup;

    public PermissionsTable(String playerUUID) {
        this.playerNick = playerUUID;
    }

    public PermissionsTable(String playerUUID, String playerGroup) {
        this.playerNick = playerUUID;
        this.playerGroup = playerGroup;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public void setPlayerNick(String playerNick) {
        this.playerNick = playerNick;
    }

    public String getPlayerGroup() {
        return playerGroup;
    }

    public void setPlayerGroup(String playerGroup) {
        this.playerGroup = playerGroup;
    }
}
