package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.NothingToCancelException;
import com.snowypeaksystems.mobactions.player.PlayerException;

public class CancelCommand implements ICancelCommand {
  private final MobActionsUser player;

  public CancelCommand(MobActionsUser player) {
    this.player = player;
  }

  @Override
  public void run() throws PlayerException {
    if (player.getStatus().getMode() == IStatus.Mode.NONE) {
      throw new NothingToCancelException();
    }

    player.getStatus().setMode(IStatus.Mode.NONE);
    player.sendMessage(gm("edit-cancel-success"));
  }
}
