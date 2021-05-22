package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.NothingToCancelException;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.player.Status;
import com.snowypeaksystems.mobactions.util.DebugLogger;

/**
 * Implementation of CancelCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CancelCommandImpl implements CancelCommand {
  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Cancelling command");
    if (player.getStatus().getMode() == Status.Mode.NONE) {
      DebugLogger.getLogger().log("Not currently editing");
      throw new NothingToCancelException();
    }

    player.getStatus().setMode(Status.Mode.NONE);
    player.sendMessage(gm("edit-cancel-success"));
    DebugLogger.getLogger().log("Command cancelled");
  }
}
