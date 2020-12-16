package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.MobActions;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Implementation of IReloadCommand.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class ReloadCommand implements IReloadCommand {
  private final MobActionsUser player;
  private final MobActions ma;

  public ReloadCommand(MobActionsUser player, MobActions ma) {
    this.player = player;
    this.ma = ma;
  }

  @Override
  public void run() throws PlayerException {
    if (!player.canReload()) {
      throw new PermissionException();
    }

    ma.reloadConfig();
    player.sendMessage(gm("reload-success"));
  }
}
