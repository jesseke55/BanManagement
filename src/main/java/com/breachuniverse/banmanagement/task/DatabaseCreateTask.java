package com.breachuniverse.banmanagement.task;

import com.breachuniverse.banmanagement.Internals;
import net.md_5.bungee.api.ProxyServer;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public class DatabaseCreateTask implements Runnable {

    @Override
    public void run() {
        boolean success = Internals.createDatabase();

        if (success) {
            ProxyServer.getInstance().getLogger().info("Database created for BanManagement.");
        } else {
            ProxyServer.getInstance().getLogger().severe("Database could not be created for BanManagement.");
        }
    }
}
