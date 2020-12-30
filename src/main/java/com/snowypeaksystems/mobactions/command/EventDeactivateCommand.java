package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;

public class EventDeactivateCommand implements IEventDeactivateCommand {
  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Setting deactivate mode");
    if (!player.canCancelEvents()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    player.getStatus().setMode(IStatus.Mode.DEACTIVATING);
    player.sendMessage(gm("event-deactivate-command"), gm("edit-cancel"));
    DebugLogger.getLogger().log("Deactivate mode set");
  }
}
