package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import com.snowypeaksystems.mobactions.warp.IWarpManager;
import java.util.Set;

public class ListWarpsCommand implements IListWarpsCommand {
  private final IWarpManager wm;
  private final MobActionsUser player;

  public ListWarpsCommand(MobActionsUser player, IWarpManager wm) {
    this.player = player;
    this.wm = wm;
  }

  @Override
  public void run() throws PlayerException {
    DebugLogger.getLogger().log("Listing warps");
    if (!player.canUseWarpsCommand()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

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
