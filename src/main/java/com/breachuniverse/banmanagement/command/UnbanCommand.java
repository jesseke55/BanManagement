package com.breachuniverse.banmanagement.command;

import com.breachuniverse.banmanagement.Internals;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class UnbanCommand extends Command {

    public UnbanCommand() {
        super("unban", "banmanagement.command.unban", new String[0]);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);

            if (player != null) {
                if (Internals.isBanned(player)) {
                    Internals.unbanPlayer(player);
                    sender.sendMessage(TextComponent.fromLegacyText("§aPlayer " + player.getName() + " is successfully unbanned."));
                } else {
                    sender.sendMessage(TextComponent.fromLegacyText("§cSorry, that player is not banned."));
                }
            } else {
                sender.sendMessage(TextComponent.fromLegacyText("§cSorry, that player does not exists."));
            }
        } else {
            sender.sendMessage(TextComponent.fromLegacyText("§cSorry, but you need to specify the player to unban."));
        }
    }
}
