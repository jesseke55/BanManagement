package com.breachuniverse.banmanagement;

import com.breachuniverse.banmanagement.util.SQL;
import com.breachuniverse.banmanagement.util.SQL.Query;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class Internals {

    public static boolean banPlayer(ProxiedPlayer player, Ban.Type banType, Timestamp expire, String reason) {
        return SQL.query(Query.INSERT_BAN, new Object[]{
                player.getUniqueId().toString(),
                banType,
                expire,
                reason
        });
    }

    public static boolean unbanPlayer(ProxiedPlayer player) {
        return SQL.query(Query.DELETE_BAN, player.getUniqueId().toString());
    }

    public static boolean createDatabase() {
        return SQL.query(Query.DATABASE_CREATE);
    }

    public static boolean createTable() {
        return SQL.query(Query.TABLE_CREATE);
    }

    public static boolean isBanned(ProxiedPlayer player) {
        return SQL.query(Query.HAS_BAN, player.getUniqueId().toString());
    }

    public static Ban getBanData(ProxiedPlayer player) {
        ResultSet resultSet = SQL.resultQuery(Query.GET_BAN, player.getUniqueId().toString());

        if (resultSet != null) {
            try {
                return new Ban(
                        player,
                        Ban.Type.valueOf(resultSet.getString("bantype")),
                        Timestamp.valueOf(resultSet.getString("expire")),
                        resultSet.getString("reason")
                );
            } catch (SQLException ignored) {
            }
        }

        return null;
    }

    public static Collection<Ban> getAllBans() {
        return Collections.unmodifiableCollection(ProxyServer.getInstance().getPlayers().stream().map(Internals::getBanData).collect(Collectors.toSet()));
    }
}
