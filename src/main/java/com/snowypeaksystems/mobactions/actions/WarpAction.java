package com.snowypeaksystems.mobactions.actions;

import com.snowypeaksystems.mobactions.data.IWarpData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.player.WarpNotFoundException;
import com.snowypeaksystems.mobactions.warp.IWarp;
import com.snowypeaksystems.mobactions.warp.IWarpManager;
import io.papermc.lib.PaperLib;
import java.util.concurrent.CompletableFuture;

public class WarpAction implements IWarpAction {
  private final IWarpData warpData;
  private final IWarpManager warpManager;
  private final MobActionsUser player;
  private final CompletableFuture<Boolean> future;

  /** Creates a warp action. */
  public WarpAction(MobActionsUser player, IWarpData warp, IWarpManager warpManager,
                    CompletableFuture<Boolean> future) {
    this.warpData = warp;
    this.player = player;
    this.future = future;
    this.warpManager = warpManager;
  }

  @Override
  public void run() throws PlayerException {
    String warpName = warpData.getAlias();
    IWarp warp = warpManager.getWarp(warpName);

    if (!player.canUseWarp(warp)) {
      throw new PermissionException();
    }
    if (warpManager.exists(warpName)) {
      throw new WarpNotFoundException(warpName);
    }

    PaperLib.getChunkAtAsync(warp.getDestination()).thenAccept(
        chunk -> future.complete(player.teleport(warp.getDestination())));
  }
}
