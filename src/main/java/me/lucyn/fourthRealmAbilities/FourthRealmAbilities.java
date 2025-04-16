package me.lucyn.fourthRealmAbilities;

import me.lucyn.fourthRealmAbilities.abilities.SonicBoomAbility;
import me.lucyn.fourthRealmAbilities.commands.SetAbilityCommand;
import me.lucyn.fourthRealmAbilities.data.AbilityManager;
import me.lucyn.fourthRealmAbilities.listeners.PlayerInteractListener;
import me.lucyn.fourthrealm.FourthRealmCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class FourthRealmAbilities extends JavaPlugin {

    public static FourthRealmCore fourthRealmCore;

    private final AbilityManager abilityManager = new AbilityManager();

    public AbilityManager getAbilityManager() {
        return abilityManager;
    }


    @Override
    public void onEnable() {

        getCommand("setability").setExecutor(new SetAbilityCommand(this));

        abilityManager.registerAbility(new SonicBoomAbility(this));



        fourthRealmCore = (FourthRealmCore) getServer().getPluginManager().getPlugin("FourthRealmCore");

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);


        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
