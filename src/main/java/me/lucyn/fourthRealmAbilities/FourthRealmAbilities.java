package me.lucyn.fourthRealmAbilities;

import me.lucyn.fourthRealmAbilities.abilities.SonicBoomAbility;
import me.lucyn.fourthrealm.FourthRealmCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class FourthRealmAbilities extends JavaPlugin implements Listener {



    public static FourthRealmCore fourthRealmCore;
    private SonicBoomAbility sonicBoom;

    @Override
    public void onEnable() {


        fourthRealmCore = (FourthRealmCore) getServer().getPluginManager().getPlugin("FourthRealmCore");

        this.sonicBoom = new SonicBoomAbility(this);

        getServer().getPluginManager().registerEvents(this, this);


        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        sonicBoom.onInteract(event);

    }
}
