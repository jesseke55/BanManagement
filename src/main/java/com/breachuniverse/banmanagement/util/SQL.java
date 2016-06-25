package com.breachuniverse.banmanagement.util;

import com.breachuniverse.banmanagement.BanManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {

    public interface Query {

        String DATABASE_CREATE = "CREATE DATABASE IF NOT EXISTS `banmanagement`;";
        String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `bans` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`uuid` VARCHAR(36) NOT NULL," +
                "`bantype` SET('PLAYER', 'IP') NOT NULL DEFAULT 'PLAYER'," +
                "`expire` TIMESTAMP NOT NULL," +
                "`reason` VARCHAR(100) NOT NULL," +
                "PRIMARY KEY (`id`));";

        String INSERT_BAN = "INSERT INTO `bans` (`id`,`uuid`, `bantype`, `expire`, `reason`) VALUES (NULL, '?', '?', '?', '?');";
        String DELETE_BAN = "DELETE FROM `bans` WHERE uuid=?;";
        String HAS_BAN = "SELECT COUNT(*) FROM `bans` WHERE uuid=? > 0;";
        String GET_BAN = "SELECT * FROM `bans` WHERE uuid=?;";

    }

    private SQL() {
    }

    public static boolean query(String query, Object... objects) {
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
            return false;
        }
    }

    public static boolean query(String query) {
        return SQL.query(query, new Object[0]);
    }

    public static ResultSet resultQuery(String query, Object... objects) {
        if (query.split("\\?").length != objects.length) {
            throw new RuntimeException("Parameters not all specified.");
        }

        try (Connection c = BanManagement.getDataSource().getConnection()) {
            PreparedStatement ps = c.prepareStatement(query);

            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i, objects[i]);
            }

            return ps.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

    public static ResultSet resultQuery(String query) {
        return SQL.resultQuery(query, new Object[0]);
    }
}
