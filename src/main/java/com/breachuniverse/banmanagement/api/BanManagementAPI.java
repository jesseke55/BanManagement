package com.breachuniverse.banmanagement.api;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public interface BanManagementAPI {

    void banPlayer(BanInfo info);

    void banPlayer(ProxiedPlayer p, String reason, CommandSender issuer);

    void banPlayer(ProxiedPlayer p, String reason, CommandSender issuer, long time);

    void banPlayer(ProxiedPlayer p, ServerInfo server, String reason, CommandSender issuer);

    void banPlayer(ProxiedPlayer p, ServerInfo server, String reason, CommandSender issuer, long time);

    void unbanPlayer(ProxiedPlayer p);

}
