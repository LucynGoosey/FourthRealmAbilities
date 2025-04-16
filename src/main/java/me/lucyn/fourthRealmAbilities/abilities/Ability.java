package me.lucyn.fourthRealmAbilities.abilities;

import me.lucyn.fourthRealmAbilities.FourthRealmAbilities;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public abstract class Ability {

    public final String id;
    public final String name;
    public final FourthRealmAbilities plugin;

    public Ability(String id, String name, FourthRealmAbilities plugin) {
        this.id = id;
        this.name = name;
        this.plugin = plugin;
    }

    public void onSneak(PlayerToggleSneakEvent event) {}
    public void onAttack(EntityDamageByEntityEvent event) {}
    public void onInteract(PlayerInteractEvent event) {}
    public void onTick(Player player) {}



}
