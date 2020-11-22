package com.snowypeaksystems.mobactions.command;

import com.snowypeaksystems.mobactions.mob.data.warp.IWarpManager;
import com.snowypeaksystems.mobactions.player.IMobActionsPlayer;
import com.snowypeaksystems.mobactions.player.PlayerException;
import java.util.concurrent.CompletableFuture;

public class WarpCommand implements IWarpCommand {
  // private final String warp;
  // private final IWarpManager warpManager;
  // private final IMobActionsPlayer player;
  // private final CompletableFuture<Boolean> future;

  /** Creates a warp command. */
  public WarpCommand(IMobActionsPlayer player, String warp, IWarpManager warpManager,
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
