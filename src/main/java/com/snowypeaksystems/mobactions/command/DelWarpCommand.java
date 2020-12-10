package com.snowypeaksystems.mobactions.command;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.warp.IWarpManager;

public class DelWarpCommand implements IDelWarpCommand {
  // private String name;
  // private IWarpManager warpManager;
  // private MobActionsUser player;

  /** Creates a DelWarpCommand object. Can be performed by the console. */
  public DelWarpCommand(MobActionsUser player, String name, IWarpManager warpManager) {
    // this.name = name;
    // this.player = player;
    // this.warpManager = warpManager;
  }

  @Override
  public void run() throws PlayerException {
    // TODO Mason
    /*
    1. Check player's permission, throw error if insufficient
    2. Check if (lowercase) warp exists, throw error if not
    3. Delete (lowercase) IWarp using the warp manager
     */
  }
}