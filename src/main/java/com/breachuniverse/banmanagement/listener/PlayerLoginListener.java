package com.breachuniverse.banmanagement.listener;

import com.breachuniverse.banmanagement.Ban;
import com.breachuniverse.banmanagement.Internals;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Arrays;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public class PlayerLoginListener implements Listener {

    @EventHandler
    public void onPlayerLogin(LoginEvent e) {
        PendingConnection c = e.getConnection();
        ProxiedPlayer p = ProxyServer.getInstance().getPlayer(c.getUniqueId());

        if (!c.isLegacy()) { // This is for security reasons.
            e.setCancelled(true);
            e.setCancelReason("You don't have a legacy client.");
        }

        if (Internals.isBanned(p)) {
            Ban ban = Internals.getBanData(p);

            if (ban != null) { // To prevent NPE, but the security gets unsure with it.
                String[] msg = new String[]{
                        "§4§lYou have been banned:",
                        "§eBan-type:§f " + ban.getBanType().toString(),
                        "§eReason:§f " + ban.getReason(),
                        "§eExpire:§f " + ban.getExpire().toString(),
                };

                e.setCancelled(true);
                e.setCancelReason(String.join("\n", Arrays.asList(msg)));
            }
        }
    }
}
