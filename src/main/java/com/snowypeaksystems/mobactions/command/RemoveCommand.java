package com.snowypeaksystems.mobactions.command;

import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;

public class RemoveCommand implements IRemoveCommand {
  private final MobActionsUser player;

  public RemoveCommand(MobActionsUser player) {
    this.player = player;
  }

  @Override
  public void run() throws PlayerException {
    if (!player.canRemove()) {
      throw new PermissionException();
    }

    player.getStatus().setMode(IStatus.Mode.DESTROYING);
  }
}
