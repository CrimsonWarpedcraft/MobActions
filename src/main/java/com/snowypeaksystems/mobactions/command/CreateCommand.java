package com.snowypeaksystems.mobactions.command;

import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;

public class CreateCommand implements ICreateCommand {
  private final MobActionsUser player;
  private final MobData data;

  public CreateCommand(MobActionsUser player, MobData data) {
    this.player = player;
    this.data = data;
  }

  @Override
  public void run() throws PlayerException {
    if (!player.canCreate()) {
      throw new PermissionException();
    }

    player.getStatus().setMode(IStatus.Mode.CREATING);
    player.getStatus().setMobData(data);
  }
}
