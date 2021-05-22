package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import com.snowypeaksystems.mobactions.warp.WarpManager;
import com.snowypeaksystems.mobactions.warp.WarpNotFoundException;

/**
 * Implementation of DelWarpCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class DelWarpCommandImpl implements DelWarpCommand {
  private final String name;
  private final WarpManager warpManager;

  /** Creates a DelWarpCommand object. Can be performed by the console. */
  public DelWarpCommandImpl(String name, WarpManager warpManager) {
    this.name = name;
    this.warpManager = warpManager;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Deleting warp");
    if (!player.canRemoveWarp()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (!warpManager.exists(name)) {
      DebugLogger.getLogger().log("Warp not found");
      throw new WarpNotFoundException(name);
    }

    warpManager.unregister(name);
    player.sendMessage(gm("warp-delete-success", name));
    DebugLogger.getLogger().log("Warp deleted");
  }
}