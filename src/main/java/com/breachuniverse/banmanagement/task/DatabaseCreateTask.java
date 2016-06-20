package com.breachuniverse.banmanagement.task;

import com.breachuniverse.banmanagement.BanManagement;
import net.md_5.bungee.api.ProxyServer;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public class DatabaseCreateTask implements Runnable {

    private String query = "CREATE DATABASE IF NOT EXISTS `banmanagement`;";

    @Override
    public void run() {
        try(Connection c = BanManagement.getDataSource().getConnection()) {
            c.createStatement().execute(query);

            ProxyServer.getInstance().getLogger().info("Database created for BanManagement.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
