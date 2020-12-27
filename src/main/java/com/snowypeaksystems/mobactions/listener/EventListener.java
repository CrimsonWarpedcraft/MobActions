package com.snowypeaksystems.mobactions.listener;

import com.snowypeaksystems.mobactions.AMobActions;
import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.actions.CommandAction;
import com.snowypeaksystems.mobactions.actions.CreateAction;
import com.snowypeaksystems.mobactions.actions.MobAction;
import com.snowypeaksystems.mobactions.actions.RemoveAction;
import com.snowypeaksystems.mobactions.actions.WarpAction;
import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.data.IWarpData;
import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import java.util.HashMap;
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
  private final HashMap<Player, MobActionsUser> players;

  public EventListener(AMobActions ma, HashMap<Player, MobActionsUser> players) {
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

    IInteractiveMob mob = ma.getInteractiveMob((LivingEntity) event.getRightClicked());
    MobActionsUser user = ma.getPlayer(event.getPlayer());
    processEvent(user, mob, event);
  }

  @Override
  @EventHandler(priority = EventPriority.HIGH)
  public void onMobDamage(EntityDamageEvent event) {
    if (!(event.getEntity() instanceof LivingEntity) || event.getEntity() instanceof Player) {
      return;
    }



    if (event instanceof EntityDamageByEntityEvent) {
      IInteractiveMob mob = ma.getInteractiveMob((LivingEntity) event.getEntity());
      Entity damager = ((EntityDamageByEntityEvent) event).getDamager();

      if (damager instanceof Player) {
        MobActionsUser user = ma.getPlayer((Player) damager);
        processEvent(user, mob, event);
      } else if (mob.exists() || ma.getInteractiveMob((LivingEntity) damager).exists()
          && event.getEntity() instanceof Player) {
        event.setCancelled(true);
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
  }

  private void processEvent(MobActionsUser player, IInteractiveMob mob, Cancellable event) {
    MobAction action = null;
    if (player.getStatus().getMode() == IStatus.Mode.CREATING) {
      action = new CreateAction(player, mob);
    } else if (player.getStatus().getMode() == IStatus.Mode.DESTROYING) {
      action = new RemoveAction(player, mob);
    } else if (mob.getData() instanceof ICommandData) {
      action = new CommandAction(player, mob);
    } else if (mob.getData() instanceof IWarpData) {
      action = new WarpAction(player, mob, ma.getWarpManager());
    }

    if (action != null) {
      try {
        action.run();
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
