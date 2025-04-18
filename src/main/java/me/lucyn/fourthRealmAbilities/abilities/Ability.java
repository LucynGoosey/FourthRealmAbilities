package me.lucyn.fourthRealmAbilities.abilities;

import me.lucyn.fourthRealmAbilities.FourthRealmAbilities;
import me.lucyn.fourthRealmAbilities.data.IncompatibilityTag;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.HashSet;
import java.util.Set;

public abstract class Ability {

    public final String id;
    public final String name;
    public final FourthRealmAbilities plugin;
    private final Set<IncompatibilityTag> incompatibilityTags = new HashSet<>();

    public Ability(String id, String name, FourthRealmAbilities plugin) {
        this.id = id;
        this.name = name;
        this.plugin = plugin;
    }

    public void onSneak(PlayerToggleSneakEvent event) {}
    public void onAttack(EntityDamageByEntityEvent event) {}
    public void onInteract(PlayerInteractEvent event) {}
    public void onTick(Player player) {}



    public Set<IncompatibilityTag> getIncompatibilityTags() {
        return incompatibilityTags;
    }

    void addIncompatibilityTag(IncompatibilityTag tag) {
        incompatibilityTags.add(tag);
    }

    public boolean isIncompatibleWith(Ability other) {
        for (IncompatibilityTag tag : incompatibilityTags) {
            if (other.incompatibilityTags.contains(tag)) {
                return true;
            }
        }
        return false;
    }



}
