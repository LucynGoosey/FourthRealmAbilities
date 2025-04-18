package me.lucyn.fourthRealmAbilities.data;

import me.lucyn.fourthRealmAbilities.FourthRealmAbilities;
import me.lucyn.fourthRealmAbilities.abilities.Ability;
import me.lucyn.fourthrealm.RealmPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//TODO: add logging to record invalid abilities

public class AbilityManager {
    private static final Map<String, Ability> registeredAbilities = new HashMap<>();

    public static void registerAbility(Ability ability) {
        registeredAbilities.put(ability.id, ability);
    }

    public static Ability getAbility(String id) {
        return registeredAbilities.get(id);
    }

    public static  Collection<Ability> getAllAbilities() {
        return registeredAbilities.values();
    }

    public static boolean isValidAbility(String id) {
        return registeredAbilities.containsKey(id);
    }

    public static boolean assignAbilityToPlayer(RealmPlayer realmPlayer, Ability ability, int slot) {
        if (slot < 0 || slot > 2) return false;
        //if(isValidAbility(id)) return false;

        realmPlayer.equippedAbilities[slot] = ability.id;
        FourthRealmAbilities.fourthRealmCore.setPlayerData(realmPlayer);
        return true;
    }

}
