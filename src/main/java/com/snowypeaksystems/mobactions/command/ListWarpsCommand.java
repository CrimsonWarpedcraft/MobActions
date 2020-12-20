package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.AMobActions;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import java.util.Set;

public class ListWarpsCommand implements IListWarpsCommand {
  private final AMobActions ma;
  private final MobActionsUser player;

  public ListWarpsCommand(MobActionsUser player, AMobActions ma) {
    this.player = player;
    this.ma = ma;
  }

  @Override
  public void run() throws PlayerException {
    if (!player.canUseWarpCommand()) {
      throw new PermissionException();
    }

    Set<String> warps = ma.getWarpManager().getLoadedWarpNames();
    if (warps.size() > 0) {
      String warpsStr = String.join(", ", warps);
      player.sendMessage(gm("list-message"), warpsStr);
    } else {
      player.sendMessage(gm("list-empty-message"));
    }
  }
}
