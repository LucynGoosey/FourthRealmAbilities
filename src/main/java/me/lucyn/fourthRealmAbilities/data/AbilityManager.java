package me.lucyn.fourthRealmAbilities.data;

import me.lucyn.fourthRealmAbilities.abilities.Ability;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AbilityManager {
    private final Map<String, Ability> registeredAbilities = new HashMap<>();

    public void registerAbility(Ability ability) {
        registeredAbilities.put(ability.id, ability);
    }

    public Ability getAbility(String id) {
        return registeredAbilities.get(id);
    }

    public Collection<Ability> getAllAbilities() {
        return registeredAbilities.values();
    }

    public boolean isValidAbility(String id) {
        return registeredAbilities.containsKey(id);
    }

}
