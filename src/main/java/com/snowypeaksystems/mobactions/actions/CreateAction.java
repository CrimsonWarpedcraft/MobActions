package com.snowypeaksystems.mobactions.actions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.InteractiveMobAlreadyExistsException;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;

public class CreateAction implements ICreateAction {
  private final IInteractiveMob mob;
  private final MobActionsUser player;
  private final MobData data;

  /** Create an action to run when a player creates a MobAction mob with the given data. */
  public CreateAction(MobActionsUser player, IInteractiveMob mob, MobData data) {
    this.player = player;
    this.mob = mob;
    this.data = data;
  }

  @Override
  public void run() throws PlayerException {
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
