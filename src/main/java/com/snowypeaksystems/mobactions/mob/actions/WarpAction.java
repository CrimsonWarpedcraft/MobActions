package com.snowypeaksystems.mobactions.mob.actions;

import com.snowypeaksystems.mobactions.player.IMobActionsPlayer;
import io.papermc.lib.PaperLib;
import java.util.concurrent.CompletableFuture;
import org.bukkit.Location;

public class WarpAction implements IWarpAction {
  private final Location dest;
  private final IMobActionsPlayer player;
  private final CompletableFuture<Boolean> future;

  /** Creates a warp action. */
  public WarpAction(IMobActionsPlayer player, Location dest, CompletableFuture<Boolean> future) {
    this.dest = dest;
    this.player = player;
    this.future = future;
  }

  @Override
  public void run() {
    //TODO: Check permissions, throw error
    PaperLib.getChunkAtAsync(dest).thenAccept(
        chunk -> future.complete(player.getPlayer().teleport(dest)));
  }
}
