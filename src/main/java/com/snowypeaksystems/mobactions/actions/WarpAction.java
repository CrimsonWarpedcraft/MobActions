package com.snowypeaksystems.mobactions.actions;

import com.snowypeaksystems.mobactions.data.IWarpData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.warp.IWarpManager;
import java.util.concurrent.CompletableFuture;

public class WarpAction implements IWarpAction {
  // private final WarpData warp;
  // private final IWarpManager warpManager;
  // private final MobActionsUser player;
  // private final CompletableFuture<Boolean> future;

  /** Creates a warp action. */
  public WarpAction(MobActionsUser player, IWarpData warp, IWarpManager warpManager,
                    CompletableFuture<Boolean> future) {
    // this.warp = warp;
    // this.player = player;
    // this.future = future;
    // this.warpManager = warpManager;
  }

  @Override
  public void run() throws PlayerException {
    //TODO Mason
    /*
    1. Check permissions, throw error if insufficient
    2. Check if (lowercase) warp exists, throw error if not
    3. Use code below to teleport the player to destination
    PaperLib.getChunkAtAsync(warp.getDestination()).thenAccept(
        chunk -> future.complete(player.getPlayer().teleport(warp.getDestination())));
     */
  }
}
