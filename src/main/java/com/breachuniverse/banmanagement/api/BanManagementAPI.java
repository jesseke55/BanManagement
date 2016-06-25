package com.breachuniverse.banmanagement.api;

import com.breachuniverse.banmanagement.Ban;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public interface BanManagementAPI {

    /**
     * Ban a player through the BanManagement API.
     *
     * @param player  The player to be banned.
     * @param banType The bantype of the ban (Player or IP).
     * @param expire  The expire when the player is unbanned.
     * @param reason  The reason why the player is banned.
     * @return The result of the query execution.
     * @since 0.1.0
     */
    boolean banPlayer(ProxiedPlayer player, Ban.Type banType, Timestamp expire, String reason);

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
     * @since 0.1.0
     */
    boolean isBanned(ProxiedPlayer player);

    /**
     * Get the data about the player's ban.
     *
     * @param player The player to get the data for.
     * @return A set of Object's about the player's ban.
     * @since 0.1.0
     */
    Ban getBanData(ProxiedPlayer player);

    /**
     * Get all the ban data in a collection.
     *
     * @return A collection of all the ban data.
     * @since 0.1.0
     */
    Collection<Ban> getAllBans();
}
