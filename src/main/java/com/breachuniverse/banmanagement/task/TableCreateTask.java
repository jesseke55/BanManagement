package com.breachuniverse.banmanagement.task;

import com.breachuniverse.banmanagement.BanManagement;
import net.md_5.bungee.api.ProxyServer;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public class TableCreateTask implements Runnable {

    `id`, `uuid`, `bantype`, `reason`, `time`, `issuer`

    private String query = "CREATE TABLE `banmanagement`.`bans` ( `id` INT NOT NULL AUTO_INCREMENT , `uuid` VARCHAR(36) NOT NULL , `bantype` SET('PLAYER','IP') NOT NULL DEFAULT 'PLAYER' , `reason` VARCHAR(100) NOT NULL , `time` INT NOT NULL , `issueer` VARCHAR(16) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";

    @Override
    public void run() {
        try(Connection c = BanManagement.getDataSource().getConnection()) {
            c.createStatement().execute(query);

            ProxyServer.getInstance().getLogger().info("Table 'bans' created for BanManagement.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
