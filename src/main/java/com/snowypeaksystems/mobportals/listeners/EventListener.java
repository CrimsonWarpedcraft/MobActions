package com.snowypeaksystems.mobportals.listeners;

import static com.snowypeaksystems.mobportals.messages.Messages.gm;

import com.snowypeaksystems.mobportals.AbstractMobPortals;
import com.snowypeaksystems.mobportals.IMobPortalPlayer;
import com.snowypeaksystems.mobportals.mobs.IPortalMob;
import com.snowypeaksystems.mobportals.mobs.PortalMob;
import com.snowypeaksystems.mobportals.warps.IWarp;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.world.WorldLoadEvent;

/**
 * Handles all events relevant to the plugin, excluding command handlers.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventListener implements Listener {
  private final AbstractMobPortals mp;

  public EventListener(AbstractMobPortals parent) {
    mp = parent;
  }

  /** Handles when players interact with mobs. */
  @EventHandler(priority = EventPriority.HIGH)
  public void onMobInteract(PlayerInteractEntityEvent event) {
    if (!(event.getRightClicked() instanceof LivingEntity)
        || event.getRightClicked() instanceof Player) {
      return;
    }

    IMobPortalPlayer player = mp.getPlayer(event.getPlayer());
    IPortalMob portal = mp.getPortalMob((LivingEntity) event.getRightClicked());
    if (player.isCreating()) {
      if (player.getCreation() instanceof IWarp) {
        IWarp warp = (IWarp) player.getCreation();
        if (mp.getWarps().exists(warp.getName())) {
          if (portal == null) {
            portal = new PortalMob((LivingEntity) event.getRightClicked(), mp);
          }
          portal.create(warp);
          player.setCreation(null);
          player.getPlayer().sendMessage(gm("portal-create-success", warp.getName()));

        } else {
          player.setCreation(null);

        }
      }

    } else if (player.isDestroying()) {
      if (portal != null) {
        IWarp warp = portal.destroy();
        player.setDestroying(false);
        player.getPlayer().sendMessage(gm("portal-remove-success", warp.getName()));

      } else {
        player.setDestroying(false);
        player.getPlayer().sendMessage(gm("portal-remove-error"));
      }

    } else {
      if (portal != null) {
        IWarp warp = portal.getData();
        String allPerm = "mobportals.warp.*";
        String altPerm = "mobportals.warp." + warp.getName();

        if (player.getPlayer().hasPermission(allPerm)
            || player.getPlayer().hasPermission(altPerm)) {
          player.warp(warp);

        } else {
          player.getPlayer().sendMessage(
              gm("permission-error", allPerm + " or " + altPerm));
        }
      }
    }

    event.setCancelled(true);
  }

  /** Handles when a mob is damaged, cancelling when necessary. */
  @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
  public void onMobDamage(EntityDamageEvent event) {
    if (!(event.getEntity() instanceof LivingEntity) || event.getEntity() instanceof Player) {
      return;
    }

    IPortalMob portal = mp.getPortalMob((LivingEntity) event.getEntity());
    if (portal == null) {
      return;
    }


    if (event instanceof EntityDamageByEntityEvent) {
      EntityDamageByEntityEvent edee = (EntityDamageByEntityEvent) event;
      if (edee.getDamager() instanceof Player) {
        IMobPortalPlayer player = mp.getPlayer((Player) edee.getDamager());

        IWarp warp = portal.getData();
        String allPerm = "mobportals.warp.*";
        String altPerm = "mobportals.warp." + warp.getName();
        if (player.getPlayer().hasPermission(allPerm)
            || player.getPlayer().hasPermission(altPerm)) {
          player.warp(warp);

        } else {
          player.getPlayer().sendMessage(
              gm("permission-error", allPerm + " or " + altPerm));
        }
      }
    }

    event.setCancelled(true);
  }

  /** Reloads the config to make sure we have all possible warps loaded. */
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onWorldLoad(WorldLoadEvent event) {
    mp.getWarps().reload();
  }
}
