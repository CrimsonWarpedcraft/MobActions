package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.mobevent.IActivateMobEventData;
import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;

public class EventActivateCommand implements IEventActivateCommand {
  private final IActivateMobEventData data;

  public EventActivateCommand(IActivateMobEventData data) {
    this.data = data;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Setting activate mode");
    if (!player.canStartEvents()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    player.getStatus().setMode(IStatus.Mode.ACTIVATING);
    player.getStatus().setStatusData(data);
    player.sendMessage(gm("event-activate-command"), gm("edit-cancel"));
    DebugLogger.getLogger().log("Activate mode set");
  }
}
