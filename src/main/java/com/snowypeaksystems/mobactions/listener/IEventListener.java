package com.snowypeaksystems.mobactions.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldLoadEvent;

/**
 * Listener for relevant events.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IEventListener extends Listener {
  /** Handles when players interact with mobs. */
  @EventHandler(priority = EventPriority.HIGH)
  void onMobInteract(PlayerInteractEntityEvent event);

  /** Handles when a mob is damaged, cancelling when necessary. */
  @EventHandler(priority = EventPriority.HIGH)
  void onMobDamage(EntityDamageEvent event);

  /** Reloads the config to make sure we have all possible warps loaded. */
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  void onWorldLoad(WorldLoadEvent event);

  /** Removes the player from the player list upon logout (mostly to conserve memory). */
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  void onPlayerLogout(PlayerQuitEvent event);
}
