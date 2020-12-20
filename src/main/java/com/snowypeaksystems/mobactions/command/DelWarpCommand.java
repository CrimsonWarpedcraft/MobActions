package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.player.WarpNotFoundException;
import com.snowypeaksystems.mobactions.warp.IWarpManager;

public class DelWarpCommand implements IDelWarpCommand {
  private final String name;
  private final IWarpManager warpManager;
  private final MobActionsUser player;

  /** Creates a DelWarpCommand object. Can be performed by the console. */
  public DelWarpCommand(MobActionsUser player, String name, IWarpManager warpManager) {
    this.name = name;
    this.player = player;
    this.warpManager = warpManager;
  }

  @Override
  public void run() throws PlayerException {
    if (!player.canRemoveWarp()) {
      throw new PermissionException();
    }

    if (!warpManager.exists(name)) {
      throw new WarpNotFoundException(name);
    }

    warpManager.unregister(name);
    player.sendMessage(gm("warp-delete-success", name));
  }
}