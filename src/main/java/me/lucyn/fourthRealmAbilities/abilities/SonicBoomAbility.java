package me.lucyn.fourthRealmAbilities.abilities;

import me.lucyn.fourthRealmAbilities.FourthRealmAbilities;
import me.lucyn.fourthrealm.RealmPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SonicBoomAbility extends Ability {

    private final Map<UUID, BukkitRunnable> chargingTasks = new HashMap<>();
    private final int chargeDuration = 40; // 2 seconds (40 ticks)

    private final Map<UUID, Long> lastRightClick = new HashMap<>();

    public final double baseDamage = 5;
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 5000;


    public SonicBoomAbility(FourthRealmAbilities plugin) {
        super("sonicboom", "Sonic Boom", plugin);
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (!player.getInventory().getItemInMainHand().getType().toString().toLowerCase().contains("sword")) return;

        UUID uuid = player.getUniqueId();
        long now = System.currentTimeMillis();

        // Cooldown check
        if (cooldowns.containsKey(uuid) && now - cooldowns.get(uuid) < cooldownTime) {
            player.sendMessage("§cSonic Boom is on cooldown!");
            return;
        }

        // Update their "last seen interact" time
        lastRightClick.put(uuid, now);

        // If already charging, do nothing
        if (chargingTasks.containsKey(uuid)) return;

        // Begin charge
        player.sendMessage("§bCharging Sonic Boom...");
        player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_SONIC_CHARGE, 1, 1);

        BukkitRunnable chargeTask = new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                long lastClick = lastRightClick.getOrDefault(uuid, 0L);
                if (System.currentTimeMillis() - lastClick > 300) {
                    player.sendMessage("§cSonic Boom cancelled.");
                    chargingTasks.remove(uuid);
                    cancel();
                    return;
                }

                ticks++;
                if (ticks >= chargeDuration) {
                    fireSonicBoom(player);
                    cooldowns.put(uuid, System.currentTimeMillis());
                    chargingTasks.remove(uuid);
                    cancel();
                }
            }
        };

        chargingTasks.put(uuid, chargeTask);
        chargeTask.runTaskTimer(plugin, 0L, 1L);
    }

    public void fireSonicBoom(Player player) {

        UUID uuid = player.getUniqueId();

        Bukkit.getScheduler().runTaskLater(plugin, () -> {

            RealmPlayer realmPlayer = plugin.fourthRealmCore.getPlayerData(player);
            int range = 50;

            Location eyeLocation = player.getEyeLocation();
            Vector direction = eyeLocation.getDirection().normalize();

            BlockIterator iterator = new BlockIterator(player.getWorld(), eyeLocation.toVector(), direction, 0, range);
            final Location[] lastLocation = {eyeLocation.clone()};



            new BukkitRunnable() {


                @Override
                public void run() {
                    if (!iterator.hasNext()) {
                        cancel();
                        return;
                    }
                    Block block = iterator.next();
                    Location blockLocation = block.getLocation();

                    player.getWorld().spawnParticle(Particle.SONIC_BOOM, blockLocation.add(0.5, 0.5, 0.5), 1);

                    double hitRadius = 2.0;
                    for(Entity entity : player.getWorld().getNearbyEntities(lastLocation[0], hitRadius, hitRadius, hitRadius)) {
                        if(entity.equals(player)) continue;

                        if (entity instanceof LivingEntity target) {
                            target.damage(baseDamage + (realmPlayer.level * 0.5), DamageSource.builder(DamageType.SONIC_BOOM).withDirectEntity(player).build());
                        }
                    }

                    if(block.getType().isSolid()) {

                        cancel();
                        return;
                    }

                    lastLocation[0] = blockLocation;
                }

            }.runTaskTimer(plugin, 0L, 1L);

            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, 1, 1);

            cooldowns.put(uuid, System.currentTimeMillis());

        }, 0);
    }

}
