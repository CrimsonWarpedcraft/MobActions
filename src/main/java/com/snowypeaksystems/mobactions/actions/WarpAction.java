package com.snowypeaksystems.mobactions.actions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.data.IWarpData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.player.WarpNotFoundException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import com.snowypeaksystems.mobactions.warp.IWarp;
import com.snowypeaksystems.mobactions.warp.IWarpManager;

public class WarpAction implements IWarpAction {
  private final IWarpData warpData;
  private final IWarpManager warpManager;
  private final MobActionsUser player;

  /** Creates a warp action. */
  public WarpAction(MobActionsUser player, IWarpData warp, IWarpManager warpManager) {
    this.warpData = warp;
    this.player = player;
    this.warpManager = warpManager;
  }

  @Override
  public void run() throws PlayerException {
    DebugLogger.getLogger().log("Warping player");
    String warpName = warpData.getAlias();

    if (!player.canUseWarp(warpName)) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (!warpManager.exists(warpName)) {
      DebugLogger.getLogger().log("Warp not found");
      throw new WarpNotFoundException(warpName);
    }

    IWarp warp = warpManager.getWarp(warpName);

    player.teleport(warp.getDestination()).thenAccept(success -> {
      if (success) {
        player.sendMessage(gm("warp-success", warpName));
        DebugLogger.getLogger().log("Player warped");
      }
    });
  }
}
