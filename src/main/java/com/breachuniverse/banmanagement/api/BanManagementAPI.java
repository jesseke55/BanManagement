package com.breachuniverse.banmanagement.api;

import com.breachuniverse.banmanagement.BanType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public interface BanManagementAPI {

    /**
     * Ban a player through the BanManagement API.
     *
     * @param player  The player to be banned.
     * @param banType The bantype of the ban (Player or IP).
     * @param reason  The reason why the player is banned.
     * @param expire  The expire when the player is unbanned.
     * @param issuer  The player/console who banned the player.
     * @return The result of the query execution.
     * @since 0.1.0
     */
    boolean banPlayer(ProxiedPlayer player, BanType banType, String reason, Timestamp expire, CommandSender issuer);

    /**
     * Unban a player through the BanManagement API.
     *
     * @param player The player to be unbanned.
     * @return The result of the query execution.
     * @since 0.1.0
     */
    boolean unbanPlayer(ProxiedPlayer player);

    /**
     * Check if the player is banned through the BanManagement API.
     *
     * @param player The player to check for.
     * @return True if the player is banned otherwise false.
     */
    boolean isBanned(ProxiedPlayer player);

    /**
     * Get the data about the player's ban.
     *
     * @param player The player to get the data for.
     * @return A set of Object's about the player's ban.
     */
    Set<Object> getBanData(ProxiedPlayer player);

    /**
     * Create database for BanManagement through the BanManagement API.
     *
     * @return The result of the query execution.
     * @apiNote Use this with caution it could mess up the plugin.
     * @since 0.1.0
     */
    boolean createDatabase();

    /**
     * Create table for BanManagement through the BanManagement API.
     *
     * @return The result of the query execution.
     * @apiNote Use this with caution it could mess up the plugin.
     * @since 0.1.0
     */
    boolean createTable();
}
