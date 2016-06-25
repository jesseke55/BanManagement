package com.breachuniverse.banmanagement;

import com.breachuniverse.banmanagement.api.BanManagementAPI;
import com.breachuniverse.banmanagement.command.BanCommand;
import com.breachuniverse.banmanagement.command.UnbanCommand;
import com.breachuniverse.banmanagement.listener.PlayerLoginListener;
import com.breachuniverse.banmanagement.task.UnbanTask;
import com.breachuniverse.banmanagement.util.SQL;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class BanManagement extends Plugin implements BanManagementAPI {

    private static final ConfigurationProvider provider = ConfigurationProvider.getProvider(YamlConfiguration.class);
    private static Configuration config;
    private static File configFile;

    private static HikariDataSource dataSource;

    @Override
    public void onEnable() {
        configFile = new File(getDataFolder(), "config.yml");

        if(!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        loadConfig();

        if (config != null) {
            Configuration databaseSection = config.getSection("database");

            if (databaseSection == null || config.get("database") == null) {
                databaseSection = new Configuration();
                databaseSection.set("host", "");
                databaseSection.set("port", 3306);
                databaseSection.set("username", "");
                databaseSection.set("password", "");
                databaseSection.set("database", "");
                saveConfig();
            }

            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
            hikariConfig.setConnectionTimeout(800);
            hikariConfig.setMaximumPoolSize(10);
            hikariConfig.addDataSourceProperty("serverName", databaseSection.getString("host"));
            hikariConfig.addDataSourceProperty("port", String.valueOf(databaseSection.getInt("port")));
            hikariConfig.addDataSourceProperty("user", databaseSection.getString("username"));
            hikariConfig.addDataSourceProperty("password", databaseSection.getString("password"));

            dataSource = new HikariDataSource(hikariConfig);

            getProxy().getPluginManager().registerCommand(this, new BanCommand());
            getProxy().getPluginManager().registerCommand(this, new UnbanCommand());

            getProxy().getPluginManager().registerListener(this, new PlayerLoginListener());

            SQL.query(SQL.Query.DATABASE_CREATE);
            SQL.query(SQL.Query.TABLE_CREATE);

            getProxy().getScheduler().schedule(this, new UnbanTask(), 0, 1, TimeUnit.SECONDS);
        } else {
            throw new IllegalStateException("Config is not initialized.");
        }
    }

    @Override
    public void onDisable() {
        saveConfig();

        if (dataSource != null) {
            dataSource.close();
            dataSource = null;
        }

        config = null;
    }

    public static void loadConfig() {
        try {
            config = provider.load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
        try {
            provider.save(config, configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

    @Override
    public boolean banPlayer(ProxiedPlayer player, Ban.Type banType, Timestamp expire, String reason) {
        return Internals.banPlayer(player, banType, expire, reason);
    }

    @Override
    public boolean unbanPlayer(ProxiedPlayer player) {
        return Internals.unbanPlayer(player);
    }

    @Override
    public boolean isBanned(ProxiedPlayer player) {
        return Internals.isBanned(player);
    }

    @Override
    public Ban getBanData(ProxiedPlayer player) {
        return Internals.getBanData(player);
    }

    @Override
    public Collection<Ban> getAllBans() {
        return Internals.getAllBans();
    }
}
