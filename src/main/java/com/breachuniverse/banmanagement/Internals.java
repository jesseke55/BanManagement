package com.breachuniverse.banmanagement;

import com.breachuniverse.banmanagement.util.SQL;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public class Internals {

    public static boolean banPlayer(ProxiedPlayer player, BanType banType, String reason, Timestamp expire, CommandSender issuer) {
        return SQL.query(SQL.fromFile("resources/query/insert_ban.sql"), new Object[]{
                player.getUniqueId().toString(),
                banType,
                reason,
                expire,
                issuer.getName()
        });
    }

    public static boolean unbanPlayer(ProxiedPlayer player) {
        return SQL.query(SQL.fromFile("resources/query/delete_ban.sql"), new Object[]{
                player.getUniqueId().toString()
        });
    }

    public static boolean createDatabase() {
        return SQL.query(SQL.fromFile("resources/query/database_create.sql"), new Object[0]);
    }

    public static boolean createTable() {
        return SQL.query(SQL.fromFile("resources/query/table_create.sql"), new Object[0]);
    }

    public static boolean isBanned(ProxiedPlayer player) {
        return SQL.query(SQL.fromFile("resources/query/has_ban.sql"), new Object[]{
                player.getUniqueId().toString()
        });
    }

    public static Set<Object> getBanData(ProxiedPlayer player) {
        Set<Object> set = new HashSet<>();
        ResultSet resultSet = SQL.resultQuery(SQL.fromFile("resources/query/get_ban.sql"), new java.lang.Object[]{
                player.getUniqueId().toString()
        });

        try {
            if (resultSet != null) {
                for (int i = 0; i < 6; i++) {
                    set.add(resultSet.getObject(i));
                }

                return set;
            }
        } catch (SQLException ignored) {
        }

        return null;
    }
}
