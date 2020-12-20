package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.player.WarpNotFoundException;
import com.snowypeaksystems.mobactions.warp.IWarp;
import com.snowypeaksystems.mobactions.warp.IWarpManager;

public class WarpCommand implements IWarpCommand {
  private final String warpName;
  private final IWarpManager warpManager;
  private final MobActionsUser player;

  /** Creates a warp command. */
  public WarpCommand(MobActionsUser player, String warp, IWarpManager warpManager) {
    this.warpName = warp;
    this.player = player;
    this.warpManager = warpManager;
  }

  @Override
  public void run() throws PlayerException {
    if (!player.canUseWarpCommand() || !player.canUseWarp(warpName)) {
      throw new PermissionException();
    }

    if (!warpManager.exists(warpName)) {
      throw new WarpNotFoundException(warpName);
    }

    IWarp warp = warpManager.getWarp(warpName);

    player.teleport(warp.getDestination()).thenAccept(success -> {
      if (success) {
        player.sendMessage(gm("warp-success", warpName));
      }
    });
  }
}
