package com.breachuniverse.banmanagement;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicReference;

public class Ban {

    private static volatile AtomicReference<Ban> reference = new AtomicReference<>();

    private ProxiedPlayer player;
    private Ban.Type banType;
    private Timestamp expire;
    private String reason;

    public Ban(ProxiedPlayer player, Ban.Type banType, Timestamp expire, String reason) {
        this.player = player;
        this.banType = banType;
        this.expire = expire;
        this.reason = reason;

        reference.set(this);
    }

    public Ban(ProxiedPlayer player, Ban.Type banType, String reason) {
        this(player, banType, null, reason);
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public Ban.Type getBanType() {
        return banType;
    }

    public Timestamp getExpire() {
        return expire;
    }

    public String getReason() {
        return reason;
    }

    public enum Type {

        PLAYER, IP;

        @Override
        public String toString() {
            switch (reference.get().banType) {
                case PLAYER:
                    return "Player";
                case IP:
                    return "IP";
                default:
                    throw new IllegalStateException("Invalid Ban.Type!");
            }
        }
    }
}
