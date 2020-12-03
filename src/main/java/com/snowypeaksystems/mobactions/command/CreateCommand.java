package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.IMobActionsPlayer;
import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;

public class CreateCommand implements ICreateCommand {
  private final IMobActionsPlayer player;
  private final MobData data;

  public CreateCommand(IMobActionsPlayer player, MobData data) {
    this.player = player;
    this.data = data;
  }

  @Override
  public void run() throws PlayerException {
    if (!player.canCreate()) {
      throw new PermissionException(gm("permission-exception"));
    }

    player.getStatus().setMode(IStatus.Mode.CREATING);
    player.getStatus().setMobData(data);
  }
}
