package com.snowypeaksystems.mobactions.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldLoadEvent;

/**
 * Listener for relevant events.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface EventListener extends Listener {
  /** Handles when players interact with mobs. */
  void onMobInteract(PlayerInteractEntityEvent event);

  /** Handles when a mob is damaged, cancelling when necessary. */
  void onMobDamage(EntityDamageEvent event);

  /** Reloads the config to make sure we have all possible warps loaded. */
  void onWorldLoad(WorldLoadEvent event);

  /** Removes the player from the player list upon logout (mostly to conserve memory). */
  void onPlayerLogout(PlayerQuitEvent event);
}
