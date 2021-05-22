package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.AbstractMobActions;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;

/**
 * Implementation of ReloadCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class ReloadCommandImpl implements ReloadCommand {
  private final AbstractMobActions ma;

  public ReloadCommandImpl(AbstractMobActions ma) {
    this.ma = ma;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Reloading plugin");
    if (!player.canReload()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    ma.reloadConfig();
    player.sendMessage(gm("reload-success"));
    DebugLogger.getLogger().log("Plugin reloaded");
  }
}
