package me.lucyn.fourthRealmAbilities.commands;

import me.lucyn.fourthRealmAbilities.data.AbilityManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetAbilityCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length != 2) {
            player.sendMessage("§cUsage: /setability <slot 1-3> <abilityID>");
            return true;
        }

        int slot;
        try {
            slot = Integer.parseInt(args[0]) - 1;
            if (slot < 0 || slot > 2) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            player.sendMessage("§cSlot must be a number between 1 and 3.");
            return true;
        }

        if(AbilityManager.assignAbilityToPlayer(player, args[1], slot)) player.sendMessage("§aSet ability in slot " + (slot + 1) + " to " + args[1] + ".");
        else player.sendMessage("§cInvalid ability ID.");
        return true;
    }
}


