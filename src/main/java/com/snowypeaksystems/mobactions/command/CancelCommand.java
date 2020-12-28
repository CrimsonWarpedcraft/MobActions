package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.NothingToCancelException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;

public class CancelCommand implements ICancelCommand {
  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Cancelling command");
    if (player.getStatus().getMode() == IStatus.Mode.NONE) {
      DebugLogger.getLogger().log("Not currently editing");
      throw new NothingToCancelException();
    }

    player.getStatus().setMode(IStatus.Mode.NONE);
    player.sendMessage(gm("edit-cancel-success"));
    DebugLogger.getLogger().log("Command cancelled");
  }
}
