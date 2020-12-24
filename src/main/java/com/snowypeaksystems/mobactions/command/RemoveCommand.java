package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;

public class RemoveCommand implements IRemoveCommand {
  private final MobActionsUser player;

  public RemoveCommand(MobActionsUser player) {
    this.player = player;
  }

  @Override
  public void run() throws PlayerException {
    DebugLogger.getLogger().log("Setting remove mode");
    if (!player.canRemove()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    player.getStatus().setMode(IStatus.Mode.DESTROYING);
    player.sendMessage(gm("remove-command"), gm("edit-cancel"));
    DebugLogger.getLogger().log("Remove mode set");
  }
}
