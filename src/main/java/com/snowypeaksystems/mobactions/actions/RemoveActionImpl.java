package com.snowypeaksystems.mobactions.actions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.InteractiveMob;
import com.snowypeaksystems.mobactions.InteractiveMobNotFoundException;
import com.snowypeaksystems.mobactions.event.RemoveInteractiveMobEvent;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.player.Status;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import org.bukkit.Bukkit;

/**
 * Implementation of RemoveAction.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class RemoveActionImpl implements RemoveAction {
  private final InteractiveMob mob;

  public RemoveActionImpl(InteractiveMob mob) {
    this.mob = mob;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Removing mob");
    player.getStatus().setMode(Status.Mode.NONE);

    if (!player.canRemove()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (!mob.exists()) {
      DebugLogger.getLogger().log("No mob found");
      throw new InteractiveMobNotFoundException();
    }

    RemoveInteractiveMobEvent event = new RemoveInteractiveMobEvent(player, mob);
    Bukkit.getPluginManager().callEvent(event);
    if (!event.isCancelled()) {
      mob.purge();
      player.sendMessage(gm("action-remove-success"));
      DebugLogger.getLogger().log("Mob removed");
    } else {
      DebugLogger.getLogger().log("Event cancelled");
    }
  }
}
