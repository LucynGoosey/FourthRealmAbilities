package me.lucyn.fourthRealmAbilities.listeners;

import me.lucyn.fourthRealmAbilities.FourthRealmAbilities;
import me.lucyn.fourthRealmAbilities.abilities.Ability;
import me.lucyn.fourthrealm.RealmPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

private final FourthRealmAbilities plugin;

public PlayerInteractListener(FourthRealmAbilities plugin) {
    this.plugin = plugin;
}

@EventHandler
public void onInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    RealmPlayer realmPlayer = FourthRealmAbilities.fourthRealmCore.getPlayerData(player);

    for (String id : realmPlayer.equippedAbilities) {
        if (id == null) continue;

        if (!plugin.getAbilityManager().isValidAbility(id)) continue; //TODO: add logging to record invalid abilities

        Ability ability = plugin.getAbilityManager().getAbility(id);
        ability.onInteract(event);
    }

}

}
