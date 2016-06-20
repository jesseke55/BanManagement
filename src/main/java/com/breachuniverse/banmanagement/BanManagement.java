package com.breachuniverse.banmanagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BanManagement extends Plugin {

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

        Configuration databaseSection = config.getSection("database");

        if(databaseSection == null || config.get("database") == null) {
            databaseSection = new Configuration();
            databaseSection.set("host", "");
            databaseSection.set("port", 0);
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
        hikariConfig.addDataSourceProperty("databaseName", databaseSection.getString("database"));
        hikariConfig.addDataSourceProperty("user", databaseSection.getString("username"));
        hikariConfig.addDataSourceProperty("password", databaseSection.getString("password"));

        dataSource = new HikariDataSource(hikariConfig);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public static Configuration getConfig() {
        return config;
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

    /*
     * API Methods starting here.
     *
     * TODO: Add API methods implementation.
     */
}
