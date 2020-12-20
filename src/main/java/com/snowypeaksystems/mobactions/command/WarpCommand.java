package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.player.WarpNotFoundException;
import com.snowypeaksystems.mobactions.warp.IWarpManager;
import io.papermc.lib.PaperLib;
import java.util.concurrent.CompletableFuture;
import org.bukkit.Location;

public class WarpCommand implements IWarpCommand {
  private final String warp;
  private final IWarpManager warpManager;
  private final MobActionsUser player;
  private final CompletableFuture<Boolean> future;

  /** Creates a warp command. */
  public WarpCommand(MobActionsUser player, String warp, IWarpManager warpManager) {
    this.warp = warp;
    this.player = player;
    this.warpManager = warpManager;
    this.future = new CompletableFuture<>();

    future.thenAccept(success -> {
      if (success) {
        player.sendMessage(gm("warp-success", warp));
      }
    });
  }

  @Override
  public void run() throws PlayerException {
    if (!player.canUseWarpCommand()) {
      throw new PermissionException();
    }

    if (!warpManager.exists(warp)) {
      throw new WarpNotFoundException(warp);
    }

    Location location = warpManager.getWarp(warp).getDestination();
    PaperLib.getChunkAtAsync(location).thenAccept(
        chunk -> future.complete(player.teleport(location)));
  }
}
