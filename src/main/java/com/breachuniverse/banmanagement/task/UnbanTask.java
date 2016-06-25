package com.breachuniverse.banmanagement.task;

import com.breachuniverse.banmanagement.Ban;
import com.breachuniverse.banmanagement.Internals;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class UnbanTask implements Runnable {

    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();

        for (Ban ban : Internals.getAllBans()) {
            ProxiedPlayer player = ban.getPlayer();
            long expire = ban.getExpire().getTime();

            if (currentTime >= expire) {
                Internals.unbanPlayer(player);
            }
        }
    }
}
