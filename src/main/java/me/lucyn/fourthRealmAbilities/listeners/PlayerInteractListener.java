package me.lucyn.fourthRealmAbilities.listeners;

import me.lucyn.fourthRealmAbilities.FourthRealmAbilities;
import me.lucyn.fourthRealmAbilities.abilities.Ability;
import me.lucyn.fourthRealmAbilities.data.AbilityManager;
import me.lucyn.fourthrealm.RealmPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

@EventHandler
public void onInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    RealmPlayer realmPlayer = FourthRealmAbilities.fourthRealmCore.getPlayerData(player);

    for (String id : realmPlayer.equippedAbilities) {
        if (id == null) continue;

        if (AbilityManager.isValidAbility(id)) continue;

        Ability ability = AbilityManager.getAbility(id);
        ability.onInteract(event);
    }

}

}
