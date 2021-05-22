package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.player.Status;
import com.snowypeaksystems.mobactions.util.DebugLogger;

/**
 * Implementation of RemoveCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class RemoveCommandImpl implements RemoveCommand {
  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Setting remove mode");
    if (!player.canRemove()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    player.getStatus().setMode(Status.Mode.DESTROYING);
    player.sendMessage(gm("remove-command"), gm("edit-cancel"));
    DebugLogger.getLogger().log("Remove mode set");
  }
}
