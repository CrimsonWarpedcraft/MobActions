package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import com.snowypeaksystems.mobactions.warp.WarpCreateException;
import com.snowypeaksystems.mobactions.warp.WarpExistsException;
import com.snowypeaksystems.mobactions.warp.WarpManager;
import java.io.IOException;

/**
 * Implementation of SetWarpCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class SetWarpCommandImpl implements SetWarpCommand {
  private final WarpManager warpManager;
  private final String name;

  /** Creates a SetWarpCommand object. */
  public SetWarpCommandImpl(String name, WarpManager warpManager) {
    this.name = name;
    this.warpManager = warpManager;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Setting warp");
    if (!player.canSetWarp()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (warpManager.exists(name)) {
      DebugLogger.getLogger().log("Warp already exists");
      throw new WarpExistsException(name);
    }

    try {
      warpManager.makeWarp(name, player.getLocation());
      player.sendMessage(gm("warp-create-success", name));
    } catch (IOException e) {
      DebugLogger.getLogger().log("Warp save error");
      throw new WarpCreateException();
    }

    DebugLogger.getLogger().log("Warp set");
  }
}
