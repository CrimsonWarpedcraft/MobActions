package com.snowypeaksystems.mobactions.command;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.warp.IWarpManager;

public class SetWarpCommand implements ISetWarpCommand {
  // private String name;
  // private IWarpManager warpManager;
  // private MobActionsUser player;

  /** Creates a SetWarpCommand object. */
  public SetWarpCommand(MobActionsUser player, String name, IWarpManager warpManager) {
    // this.name = name;
    // this.player = player;
    // this.warpManager = warpManager;
  }

  @Override
  public void run() throws PlayerException {
    // TODO Mason
    /*
    1. Check player's permission, throw error if insufficient
    2. Check if (lowercase) warp exists, throw error if so
    3. Make an IWarp (lowercase) using the warp manager
     */
  }
}
