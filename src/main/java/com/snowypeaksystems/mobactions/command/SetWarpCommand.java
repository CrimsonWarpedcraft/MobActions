package com.snowypeaksystems.mobactions.command;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.player.WarpExistsException;
import com.snowypeaksystems.mobactions.warp.IWarpManager;

public class SetWarpCommand implements ISetWarpCommand {
  private String name;
  private IWarpManager warpManager;
  private MobActionsUser player;

  /** Creates a SetWarpCommand object. */
  public SetWarpCommand(MobActionsUser player, String name, IWarpManager warpManager) {
    this.name = name;
    this.player = player;
    this.warpManager = warpManager;
  }

  @Override
  public void run() throws PlayerException {

    if (!player.canSetWarp()) {
      throw new PermissionException();
    }
    if (warpManager.exists(name.toLowerCase())) {
      throw new WarpExistsException();
    }

    warpManager.makeWarp(name, player.getLocation());

  }
}
