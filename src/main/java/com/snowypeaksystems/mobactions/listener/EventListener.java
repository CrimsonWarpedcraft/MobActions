package com.snowypeaksystems.mobactions.listener;

import com.snowypeaksystems.mobactions.AMobActions;
import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.actions.CommandAction;
import com.snowypeaksystems.mobactions.actions.CreateAction;
import com.snowypeaksystems.mobactions.actions.EventMobJoinAction;
import com.snowypeaksystems.mobactions.actions.MobAction;
import com.snowypeaksystems.mobactions.actions.RemoveAction;
import com.snowypeaksystems.mobactions.actions.WarpAction;
import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.data.IEventData;
import com.snowypeaksystems.mobactions.data.IWarpData;
import com.snowypeaksystems.mobactions.data.IncompleteDataException;
import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 * Handles all events relevant to the plugin, excluding command handlers.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventListener implements IEventListener {
  private final AMobActions ma;
  private final Map<Player, MobActionsUser> players;

  public EventListener(AMobActions ma, Map<Player, MobActionsUser> players) {
    this.ma = ma;
    this.players = players;
  }

  @Override
  @EventHandler(priority = EventPriority.HIGH)
  public void onMobInteract(PlayerInteractEntityEvent event) {
    DebugLogger.getLogger().log("Mob interaction event");
    if (event.getHand().equals(EquipmentSlot.OFF_HAND)
        || !(event.getRightClicked() instanceof LivingEntity)
        || event.getRightClicked() instanceof Player) {
      return;
    }


    MobActionsUser user = ma.getPlayer(event.getPlayer());
    try {
      IInteractiveMob mob = ma.getInteractiveMob((LivingEntity) event.getRightClicked());
      processEvent(user, mob, event);
    } catch (IncompleteDataException e) {
      Bukkit.getLogger().log(Level.WARNING, "Error creating mob object", e.getCause());
      user.sendMessage(e.getPlayerFormattedString());
    }
  }

  @Override
  @EventHandler(priority = EventPriority.HIGH)
  public void onMobDamage(EntityDamageEvent event) {
    if (!(event.getEntity() instanceof LivingEntity)) {
      return;
    }

    try {
      if (!(event.getEntity() instanceof Player) && event instanceof EntityDamageByEntityEvent
          && ((EntityDamageByEntityEvent) event).getDamager() instanceof Player) {
        MobActionsUser user = ma.getPlayer(
            (Player) ((EntityDamageByEntityEvent) event).getDamager());
        IInteractiveMob mob = ma.getInteractiveMob((LivingEntity) event.getEntity());
        processEvent(user, mob, event);

      } else if (event.getEntity() instanceof Player && event instanceof EntityDamageByEntityEvent
          && (((EntityDamageByEntityEvent) event).getDamager()) instanceof LivingEntity
          && !((((EntityDamageByEntityEvent) event).getDamager()) instanceof Player)
          && ma.getInteractiveMob(
              (LivingEntity) ((EntityDamageByEntityEvent) event).getDamager()).exists()) {
        event.setCancelled(true);

      } else if (!(event.getEntity() instanceof Player)
          && ma.getInteractiveMob((LivingEntity) event.getEntity()).exists()) {
        event.setCancelled(true);
      }
      
    } catch (IncompleteDataException e) {
      Bukkit.getLogger().log(Level.WARNING, "Error creating mob object", e);

      if (event instanceof EntityDamageByEntityEvent) {
        Entity entity = ((EntityDamageByEntityEvent) event).getDamager();
        if (entity instanceof Player) {
          ma.getPlayer((Player) entity).sendMessage(e.getPlayerFormattedString());
        }
      }
    }
  }

  @Override
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onWorldLoad(WorldLoadEvent event) {
    ma.getWarpManager().reload();
  }

  @Override
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onPlayerLogout(PlayerQuitEvent event) {
    players.remove(event.getPlayer());
    ma.getMobEventManager().removeFromAll(ma.getPlayer(event.getPlayer()));
  }

  private void processEvent(MobActionsUser player, IInteractiveMob mob, Cancellable event) {
    MobAction action = null;
    if (player.getStatus().getMode() == IStatus.Mode.CREATING) {
      action = new CreateAction(mob);
    } else if (player.getStatus().getMode() == IStatus.Mode.DESTROYING) {
      action = new RemoveAction(mob);
    } else if (mob.getData() instanceof ICommandData) {
      action = new CommandAction(mob, (ICommandData) mob.getData(), ma.getServer());
    } else if (mob.getData() instanceof IEventData) {
      action = new EventMobJoinAction(mob, (IEventData) mob.getData(), ma.getMobEventManager());
    } else if (mob.getData() instanceof IWarpData) {
      action = new WarpAction(mob, (IWarpData) mob.getData(), ma.getWarpManager());
    }

    if (action != null) {
      try {
        action.run(player);
      } catch (PlayerException e) {
        player.sendMessage(e.getPlayerFormattedString());
        DebugLogger.getLogger().log("Error: " + e.getPlayerFormattedString());
      }
      event.setCancelled(true);
      DebugLogger.getLogger().log("Cancelled event");
    } else {
      DebugLogger.getLogger().log("No applicable actions found");
    }
  }
}
