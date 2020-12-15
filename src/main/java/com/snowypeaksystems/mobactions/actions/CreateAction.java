package com.snowypeaksystems.mobactions.actions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.IStatus;
import com.snowypeaksystems.mobactions.player.InteractiveMobAlreadyExistsException;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;

public class CreateAction implements ICreateAction {
  private final IInteractiveMob mob;
  private final MobActionsUser player;

  /** Create an action to run when a player creates a MobAction mob with the given data. */
  public CreateAction(MobActionsUser player, IInteractiveMob mob) {
    this.player = player;
    this.mob = mob;
  }

  @Override
  public void run() throws PlayerException {
    final MobData data = player.getStatus().getMobData();
    player.getStatus().setMode(IStatus.Mode.NONE);

    if (!player.canCreate()) {
      throw new PermissionException();
    }

    if (mob.exists()) {
      throw new InteractiveMobAlreadyExistsException();
    }

    mob.setData(data);
    mob.store();

    player.sendMessage(gm("action-create-success"));
  }
}
