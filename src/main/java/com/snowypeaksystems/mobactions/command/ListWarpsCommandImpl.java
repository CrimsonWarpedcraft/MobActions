package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import com.snowypeaksystems.mobactions.warp.WarpManager;
import java.util.Set;

/**
 * Implementation of ListWarpsCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class ListWarpsCommandImpl implements ListWarpsCommand {
  private final WarpManager wm;

  public ListWarpsCommandImpl(WarpManager wm) {
    this.wm = wm;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Listing warps");
    Set<String> warps = wm.getLoadedWarpNames();

    if (warps.size() > 0) {
      String warpsStr = String.join(", ", warps);
      player.sendMessage(gm("list-message"), warpsStr);
    } else {
      player.sendMessage(gm("list-empty-message"));
    }
    
    DebugLogger.getLogger().log("Warps listed");
  }
}
