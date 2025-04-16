package me.lucyn.fourthRealmAbilities.commands;

import me.lucyn.fourthRealmAbilities.FourthRealmAbilities;
import me.lucyn.fourthrealm.RealmPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetAbilityCommand implements CommandExecutor {

    private final FourthRealmAbilities plugin;

    public SetAbilityCommand(FourthRealmAbilities plugin) {
        this.plugin = plugin;
    }

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

        RealmPlayer realmPlayer = FourthRealmAbilities.fourthRealmCore.getPlayerData(player);
        int slot;
        try {
            slot = Integer.parseInt(args[0]) - 1;
            if (slot < 0 || slot > 2) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            player.sendMessage("§cSlot must be a number between 1 and 3.");
            return true;
        }

        String abilityId = args[1].toLowerCase();


        realmPlayer.equippedAbilities[slot] = abilityId;

        player.sendMessage("§aSet ability in slot " + (slot + 1) + " to " + abilityId);
        return true;
    }
}

