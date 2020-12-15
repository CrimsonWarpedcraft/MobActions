package com.snowypeaksystems.mobactions.listener;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.AMobActions;
import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.actions.CommandAction;
import com.snowypeaksystems.mobactions.actions.CreateAction;
import com.snowypeaksystems.mobactions.actions.RemoveAction;
import com.snowypeaksystems.mobactions.actions.WarpAction;
import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.data.IWarpData;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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
  public void onMobInteract(PlayerInteractEntityEvent event) {
    if (event.getHand().equals(EquipmentSlot.OFF_HAND)
        || !(event.getRightClicked() instanceof LivingEntity)
        || event.getRightClicked() instanceof Player) {
      return;
    }

    MobActionsUser player = ma.getPlayer(event.getPlayer());
    IInteractiveMob mob = ma.getInteractiveMob((LivingEntity) event.getRightClicked());
    if (player.getStatus().getMode() == IStatus.Mode.CREATING) {
      event.setCancelled(true);
      MobData data = player.getStatus().getMobData();

      try {
        (new CreateAction(player, mob, data)).run();
      } catch (PlayerException e) {
        player.sendMessage(e.getMessage());
      }

    } else if (player.getStatus().getMode() == IStatus.Mode.DESTROYING) {
      event.setCancelled(true);

      try {
        (new RemoveAction(player, mob)).run();
      } catch (PlayerException e) {
        player.sendMessage(e.getMessage());
      }

    } else if (mob.getData() instanceof ICommandData) {
      event.setCancelled(true);

      try {
        (new CommandAction(player, (ICommandData) mob.getData())).run();
      } catch (PlayerException e) {
        player.sendMessage(e.getMessage());
      }

    } else if (mob.getData() instanceof IWarpData) {
      event.setCancelled(true);

      CompletableFuture<Boolean> future = new CompletableFuture<>();
      future.thenAccept(success -> {
        if (success) {
          player.sendMessage(gm("warp-success", mob.getData().getAlias()));
        }
      });


      try {
        (new WarpAction(player, (IWarpData) mob.getData(), ma.getWarpManager(), future)).run();
      } catch (PlayerException e) {
        player.sendMessage(e.getMessage());
      }
    }
  }

  @Override
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
