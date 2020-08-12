package com.snowypeaksystems.mobportals;

import com.snowypeaksystems.mobportals.exceptions.PermissionException;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * Handles all events relevant to the plugin, excluding command handlers.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventListener implements Listener {
  private final MobPortals mp;

  public EventListener(MobPortals parent) {
    mp = parent;
  }

  /** Handles when players interact with mobs depending on the state their in. */
  @EventHandler
  public void onMobInteract(PlayerInteractEntityEvent piee) {
    if (!(piee.getRightClicked() instanceof LivingEntity)
        || piee.getRightClicked() instanceof Player) {
      return;
    }

    LivingEntity mob = (LivingEntity) piee.getRightClicked();

    if (mob instanceof Player) {
      piee.getPlayer().sendMessage(mp.messages.portalCreateError);
      return;
    }

    if (mp.isCreating(piee.getPlayer())) {
      piee.setCancelled(true);
      String name = mp.getCreation(piee.getPlayer());

      if (mp.createPortal(mob, name)) {
        piee.getPlayer().sendMessage(
            mp.messages.portalCreateSuccess.replaceAll(Messages.warpToken, name));
      } else {
        piee.getPlayer().sendMessage(
            mp.messages.warpNotFound.replaceAll(Messages.warpToken, name));
      }

      mp.stopCreating(piee.getPlayer());
    } else if (mp.isRemoving(piee.getPlayer())) {
      piee.setCancelled(true);

      if (mp.removePortal(mob)) {
        piee.getPlayer().sendMessage(mp.messages.portalRemoveSuccess);
      } else {
        piee.getPlayer().sendMessage(mp.messages.portalRemoveError);
      }

      mp.stopRemoving(piee.getPlayer());
    } else if (mp.isPortal(mob)) {
      piee.setCancelled(true);

      try {
        mp.warpPlayer(piee.getPlayer(), mp.getPortalDestination(mob));
      } catch (PermissionException e) {
        piee.getPlayer().sendMessage(
            mp.messages.permissionError.replace(Messages.permToken, e.getMissingPermission()));
      }
    }
  }

  /** Handles when a mob is damaged, cancelling when necessary. */
  @EventHandler
  public void onMobDamage(EntityDamageEvent ede) {
    if (!(ede.getEntity() instanceof LivingEntity) || ede.getEntity() instanceof Player) {
      return;
    }

    LivingEntity mob = (LivingEntity) ede.getEntity();

    if (mp.isPortal(mob)) {
      ede.setCancelled(true);
    }
  }
}
