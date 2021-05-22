package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.player.Status;
import com.snowypeaksystems.mobactions.util.DebugLogger;

/**
 * Implementation of CreateCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CreateCommandImpl implements CreateCommand {
  private final MobData data;

  public CreateCommandImpl(MobData data) {
    this.data = data;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Setting create mode");
    if (!player.canCreate()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    player.getStatus().setMode(Status.Mode.CREATING);
    player.getStatus().setMobData(data);
    player.sendMessage(gm("create-command"), gm("edit-cancel"));
    DebugLogger.getLogger().log("Create mode set");
  }
}
