package com.breachuniverse.banmanagement.api;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public class BanInfo {

    private ProxiedPlayer player;
    private ServerInfo server;
    private String reason;
    private CommandSender issuer;
    private long time;

    public BanInfo(ProxiedPlayer player, ServerInfo server, String reason, CommandSender issuer, long time) {
        this.player = player;
        this.server = server;
        this.reason = reason;
        this.issuer = issuer;
        this.time = time;
    }

    public BanInfo(ProxiedPlayer player, ServerInfo server, String reason, CommandSender issuer) {
        this(player, server, reason, issuer, -1);
    }

    @Deprecated
    @SuppressWarnings("unchecked")
    public BanInfo(ProxiedPlayer player, String reason, CommandSender issuer, long time) {
        this(player, ((List<ServerInfo>) ProxyServer.getInstance().getServers()).get(0), reason, issuer, time);
    }

    public BanInfo(ProxiedPlayer player, String reason, CommandSender issuer) {
        this.player = player;
        this.reason = reason;
        this.issuer = issuer;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public ServerInfo getServer() {
        return server;
    }

    public String getReason() {
        return reason;
    }

    public CommandSender getIssuer() {
        return issuer;
    }

    public long getTime() {
        return time;
    }
}
