package com.breachuniverse.banmanagement.command;

import com.breachuniverse.banmanagement.Ban;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BanCommand extends Command {

    public BanCommand() {
        super("ban", "banmanagement.command.ban", new String[0]);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 4) {
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);
            Ban.Type banType = Ban.Type.valueOf(args[1]);
        } else {
            sender.sendMessage(TextComponent.fromLegacyText(
                    "§cSorry, you need to use the following usage: " +
                            "/unban <player> <bantype> <expire> <reason>"
            ));
        }
    }
}
