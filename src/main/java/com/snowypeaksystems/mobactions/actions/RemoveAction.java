package com.snowypeaksystems.mobactions.actions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.InteractiveMobNotFoundException;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;

public class RemoveAction implements IRemoveAction {
  private final MobActionsUser player;
  private final IInteractiveMob mob;

  public RemoveAction(MobActionsUser player, IInteractiveMob mob) {
    this.player = player;
    this.mob = mob;
  }

  @Override
  public void run() throws PlayerException {
    player.getStatus().setMode(IStatus.Mode.NONE);

    if (!player.canRemove()) {
      throw new PermissionException();
    }

    if (mob.exists()) {
      mob.purge();
    } else {
      throw new InteractiveMobNotFoundException(gm("remove-error"));
    }
  }
}
