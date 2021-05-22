package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.player.MobActionsUser;

/**
 * Events that are caused by MobActionsUsers.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public abstract class MobActionsUserEvent extends CancellableEvent {
  private final MobActionsUser user;

  public MobActionsUserEvent(MobActionsUser user) {
    this.user = user;
  }

  public MobActionsUser getMobActionsUser() {
    return user;
  }
}
