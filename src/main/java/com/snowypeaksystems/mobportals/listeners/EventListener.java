package com.snowypeaksystems.mobportals.listeners;

import static com.snowypeaksystems.mobportals.messages.Messages.gm;

import com.snowypeaksystems.mobportals.AbstractMobPortals;
import com.snowypeaksystems.mobportals.IMobCommand;
import com.snowypeaksystems.mobportals.IMobPortalPlayer;
import com.snowypeaksystems.mobportals.exceptions.MobAlreadyExists;
import com.snowypeaksystems.mobportals.mobs.CommandMob;
import com.snowypeaksystems.mobportals.mobs.ICommandMob;
import com.snowypeaksystems.mobportals.mobs.IMob;
import com.snowypeaksystems.mobportals.mobs.IPortalMob;
import com.snowypeaksystems.mobportals.mobs.PortalMob;
import com.snowypeaksystems.mobportals.persistence.IMobWritable;
import com.snowypeaksystems.mobportals.warps.IWarp;
import java.util.logging.Level;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.EquipmentSlot;

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
    if (event.getHand().equals(EquipmentSlot.OFF_HAND)
        || !(event.getRightClicked() instanceof LivingEntity)
        || event.getRightClicked() instanceof Player) {
      return;
    }

    event.setCancelled(true);

    IMob<? extends IMobWritable> mob;
    try {
      mob = mp.getMob((LivingEntity) event.getRightClicked());
    } catch (MobAlreadyExists e) {
      mp.getLogger().log(Level.WARNING, "Found mob with multiple configurations.", e);
      return;
    }

    IMobPortalPlayer player = mp.getPlayer(event.getPlayer());

    if (player.isCreating()) {
      if (player.getCreationType() == IMobPortalPlayer.Type.PORTAL) {
        IWarp warp = (IWarp) player.getCreation();
        if (mp.getWarps().exists(warp.getName())) {
          IPortalMob portal = (IPortalMob) mob;

          if (portal == null) {
            try {
              portal = new PortalMob((LivingEntity) event.getRightClicked(), mp);
            } catch (MobAlreadyExists e) {
              player.getPlayer().sendMessage(gm("mob-exists-error"));
              return;
            }
          }

          portal.create(warp);
          player.setCreation(IMobPortalPlayer.Type.NONE, null);
          player.getPlayer().sendMessage(gm("portal-create-success", warp.getName()));

        } else {
          player.setCreation(IMobPortalPlayer.Type.NONE, null);
          player.getPlayer().sendMessage(gm("warp-missing", warp.getName()));
        }

      } else if (player.getCreationType() == IMobPortalPlayer.Type.COMMAND) {
        IMobCommand command = (IMobCommand) player.getCreation();
        ICommandMob commandMob = (ICommandMob) mob;

        if (commandMob == null) {
          try {
            commandMob = new CommandMob((LivingEntity) event.getRightClicked(), mp);
          } catch (MobAlreadyExists e) {
            player.getPlayer().sendMessage(gm("mob-exists-error"));
            return;
          }
        }

        commandMob.create(command);
        player.setCreation(IMobPortalPlayer.Type.NONE, null);
        player.getPlayer().sendMessage(gm("portal-create-success", command.getName()));
      }

    } else if (player.isDestroying()) {
      if (mob != null) {
        mob.destroy();
        player.setDestroying(false);
        player.getPlayer().sendMessage(gm("edit-remove-success"));

      } else {
        player.setDestroying(false);
        player.getPlayer().sendMessage(gm("edit-remove-error"));
      }

    } else {
      if (mob instanceof IPortalMob) {
        IWarp warp = (IWarp) mob.getData();
        warp(player, warp);
      } else if (mob instanceof ICommandMob) {
        IMobCommand command = (IMobCommand) mob.getData();
        command(player, command);
      } else {
        event.setCancelled(false);
      }
    }
  }

  /** Handles when a mob is damaged, cancelling when necessary. */
  @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
  public void onMobDamage(EntityDamageEvent event) {
    if (!(event.getEntity() instanceof LivingEntity) || event.getEntity() instanceof Player) {
      return;
    }

    IMob<? extends IMobWritable> mob;
    try {
      mob = mp.getMob((LivingEntity) event.getEntity());
    } catch (MobAlreadyExists e) {
      mp.getLogger().log(Level.WARNING, "Found mob with multiple configurations.", e);
      return;
    }

    if (mob == null) {
      return;
    }

    if (event instanceof EntityDamageByEntityEvent) {
      EntityDamageByEntityEvent edee = (EntityDamageByEntityEvent) event;
      if (edee.getDamager() instanceof Player) {
        if (mob instanceof IPortalMob) {
          IPortalMob portal = (IPortalMob) mob;
          IWarp warp = portal.getData();
          IMobPortalPlayer player = mp.getPlayer((Player) edee.getDamager());

          warp(player, warp);
        } else if (mob instanceof ICommandMob) {
          ICommandMob portal = (ICommandMob) mob;
          IMobCommand command = portal.getData();
          IMobPortalPlayer player = mp.getPlayer((Player) edee.getDamager());

          command(player, command);
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

  private void warp(IMobPortalPlayer player, IWarp warp) {
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

  private void command(IMobPortalPlayer player, IMobCommand command) {
    String allPerm = "mobportals.command.*";
    String altPerm = "mobportals.command." + command.getName();
    if (player.getPlayer().hasPermission(allPerm)
        || player.getPlayer().hasPermission(altPerm)) {
      String rawCommand = command.getString(player.getPlayer().getDisplayName());
      player.getPlayer().performCommand(rawCommand);

    } else {
      player.getPlayer().sendMessage(
          gm("permission-error", allPerm + " or " + altPerm));
    }
  }
}
