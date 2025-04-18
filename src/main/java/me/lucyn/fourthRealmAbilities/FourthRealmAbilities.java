package me.lucyn.fourthRealmAbilities;

import me.lucyn.fourthRealmAbilities.abilities.SonicBoomAbility;
import me.lucyn.fourthRealmAbilities.commands.SetAbilityCommand;
import me.lucyn.fourthRealmAbilities.data.AbilityManager;
import me.lucyn.fourthRealmAbilities.listeners.PlayerInteractListener;
import me.lucyn.fourthrealm.FourthRealmCore;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class FourthRealmAbilities extends JavaPlugin {

    public static FourthRealmCore fourthRealmCore;

    @Override
    public void onEnable() {

        Objects.requireNonNull(getCommand("setability")).setExecutor(new SetAbilityCommand());

        AbilityManager.registerAbility(new SonicBoomAbility(this));



        fourthRealmCore = (FourthRealmCore) getServer().getPluginManager().getPlugin("FourthRealmCore");

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);


        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
