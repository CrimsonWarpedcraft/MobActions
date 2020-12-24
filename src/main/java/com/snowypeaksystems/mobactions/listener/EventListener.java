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
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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
      DebugLogger.getLogger().log("Cancelled interact event");
      return;
    }

    MobAction action = null;
    MobActionsUser player = ma.getPlayer(event.getPlayer());
    IInteractiveMob mob = ma.getInteractiveMob((LivingEntity) event.getRightClicked());

    if (player.getStatus().getMode() == IStatus.Mode.CREATING) {
      action = new CreateAction(player, mob);
    } else if (player.getStatus().getMode() == IStatus.Mode.DESTROYING) {
      action = new RemoveAction(player, mob);
    } else if (mob.getData() instanceof ICommandData) {
      action = new CommandAction(player, (ICommandData) mob.getData());
    } else if (mob.getData() instanceof IWarpData) {
      action = new WarpAction(player, (IWarpData) mob.getData(), ma.getWarpManager());
    }

    if (action != null) {
      event.setCancelled(true);
      DebugLogger.getLogger().log("Cancelled event");
      try {
        action.run();
      } catch (PlayerException e) {
        player.sendMessage(e.getMessage());
        DebugLogger.getLogger().log("Error: " + e.getMessage());
      }
    } else {
      DebugLogger.getLogger().log("No applicable actions found");
    }
  }

  @Override
  @EventHandler(priority = EventPriority.HIGH)
  public void onMobDamage(EntityDamageEvent event) {
    if (!(event.getEntity() instanceof LivingEntity) || event.getEntity() instanceof Player) {
      return;
    }

    IInteractiveMob mob = ma.getInteractiveMob((LivingEntity) event.getEntity());
    if (mob.exists()) {
      event.setCancelled(true);
    }
  }

  @Override
  public void onWorldLoad(WorldLoadEvent event) {
    ma.getWarpManager().reload();
  }

  @Override
  public void onPlayerLogout(PlayerQuitEvent event) {
    players.remove(event.getPlayer());
  }
}
