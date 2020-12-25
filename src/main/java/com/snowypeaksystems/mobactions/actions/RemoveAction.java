package com.snowypeaksystems.mobactions.actions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.event.RemoveIInteractiveMobEvent;
import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.InteractiveMobNotFoundException;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import org.bukkit.Bukkit;

public class RemoveAction implements IRemoveAction {
  private final MobActionsUser player;
  private final IInteractiveMob mob;

  public RemoveAction(MobActionsUser player, IInteractiveMob mob) {
    this.player = player;
    this.mob = mob;
  }

  @Override
  public void run() throws PlayerException {
    DebugLogger.getLogger().log("Removing mob");
    player.getStatus().setMode(IStatus.Mode.NONE);

    if (!player.canRemove()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (!mob.exists()) {
      DebugLogger.getLogger().log("No mob found");
      throw new InteractiveMobNotFoundException();
    }

    RemoveIInteractiveMobEvent event = new RemoveIInteractiveMobEvent(player, mob);
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
