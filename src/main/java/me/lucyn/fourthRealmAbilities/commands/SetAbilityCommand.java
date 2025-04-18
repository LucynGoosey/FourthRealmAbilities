package me.lucyn.fourthRealmAbilities.commands;

import me.lucyn.fourthRealmAbilities.FourthRealmAbilities;
import me.lucyn.fourthRealmAbilities.abilities.Ability;
import me.lucyn.fourthRealmAbilities.data.AbilityManager;
import me.lucyn.fourthrealm.RealmPlayer;
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
        if(!AbilityManager.isValidAbility(args[1])) {
            player.sendMessage("§cInvalid ability ID.");
            return true;
        }
        Ability ability = AbilityManager.getAbility(args[1]);
        RealmPlayer realmPlayer = FourthRealmAbilities.fourthRealmCore.getPlayerData(player);
        if(AbilityManager.assignAbilityToPlayer(realmPlayer, ability, slot)) player.sendMessage("§aSet ability in slot " + (slot + 1) + " to " + args[1] + ".");
        else player.sendMessage("§cAbility" + args[1] + "is incompatible with another ability.");
        return true;
    }
}


