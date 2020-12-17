package com.snowypeaksystems.mobactions.command;

import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.player.WarpNotFoundException;
import com.snowypeaksystems.mobactions.warp.IWarpManager;

public class DelWarpCommand implements IDelWarpCommand {
  private String name;
  private IWarpManager warpManager;
  private MobActionsUser player;

  /** Creates a DelWarpCommand object. Can be performed by the console. */
  public DelWarpCommand(MobActionsUser player, String name, IWarpManager warpManager) {
    this.name = name;
    this.player = player;
    this.warpManager = warpManager;
  }

  @Override
  public void run() throws PlayerException {

    if (!player.canCreate()) {
      throw new PermissionException();
    }

    String nameToLower = name.toLowerCase();

    if (!warpManager.exists(nameToLower)) {
      throw new WarpNotFoundException(nameToLower);
    }

    player.getStatus().setMode(IStatus.Mode.DESTROYING);
    warpManager.unregister(nameToLower);
  }
}