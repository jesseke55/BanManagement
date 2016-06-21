package com.breachuniverse.banmanagement.task;

import com.breachuniverse.banmanagement.Internals;
import net.md_5.bungee.api.ProxyServer;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public class TableCreateTask implements Runnable {

    @Override
    public void run() {
        boolean success = Internals.createTable();

        if (success) {
            ProxyServer.getInstance().getLogger().info("Table 'bans' created for BanManagement.");
        } else {
            ProxyServer.getInstance().getLogger().severe("Table 'bans' could not be created for BanManagement.");
        }
    }
}
