import com.breachuniverse.banmanagement.Ban;
import com.breachuniverse.banmanagement.BanManagement;
import com.breachuniverse.banmanagement.api.BanManagementAPI;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public class BanManagementAPI_Test extends Plugin {

    private static final AtomicReference<BanManagementAPI> banManagementAPI = new AtomicReference<>();

    @Override
    public void onEnable() {
        Plugin plugin = getProxy().getPluginManager().getPlugin("BanManagement");

        if (plugin != null && (plugin instanceof BanManagement)) {
            banManagementAPI.set((BanManagement) plugin);
        } else {
            // 'Plugin Not Exists' Error handling.
            throw new IllegalStateException("BanManagement is not installed!");
        }

        //--------------------------------------------------------------------------\\
        ProxiedPlayer target = getProxy().getPlayer("TestPlayer");

        /**
         Ban check for player.
         */
        if (banManagementAPI.get().isBanned(target)) {
            /**
             Unban player.
             */
            banManagementAPI.get().unbanPlayer(target);
        } else {
            /**
             Permanently Player/IP ban.
             */
            banManagementAPI.get().banPlayer(target, Ban.Type.PLAYER, null, "Just a random reason.");

            /**
             Temporarily Player/IP ban.

             Format:  yyyy-m-d hh:mm:ss
             */
            banManagementAPI.get().banPlayer(
                    target,
                    Ban.Type.PLAYER,
                    Timestamp.valueOf("2016-6-30 00:00:00"),
                    "Just a reason."
            );
        }
    }
}
