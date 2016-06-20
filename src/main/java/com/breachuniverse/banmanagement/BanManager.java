package com.breachuniverse.banmanagement;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public class BanManager {

    private String ban = "INSERT INTO `bans` (`id`, `uuid`, `server`, `bantype`, `durationtype`, `reason`, `time`) VALUES (NULL, '?', '?', '?');";
    private String unban = "DELETE FROM `bans` WHERE `uuid`=?;";

    protected BanManager() { }

    public boolean banPlayer(ProxiedPlayer player, ServerInfo server, BanType banType, DurationType durationType, String reason) {
        return false;
    }

    public boolean unbanPlayer(ProxiedPlayer player) {
        return query(unban, new String[]{player.getUniqueId().toString()});
    }

    private boolean query(String query, Object[] objects) {
        if (query.split("\\?").length != objects.length) {
            throw new RuntimeException("Parameters not all specified.");
        }

        try (Connection c = BanManagement.getDataSource().getConnection()) {
            PreparedStatement ps = c.prepareStatement(query);

            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i, objects[i]);
            }

            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
