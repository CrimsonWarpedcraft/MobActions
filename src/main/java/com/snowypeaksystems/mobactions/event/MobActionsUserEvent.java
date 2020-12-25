package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * Events that are caused by IMobPortalUsers.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public abstract class MobActionsUserEvent extends Event implements Cancellable {
  private boolean cancelled;
  private final MobActionsUser user;

  public MobActionsUserEvent(MobActionsUser user) {
    this.user = user;
    cancelled = false;
  }

  @Override
  public void setCancelled(boolean cancel) {
    cancelled = cancel;
  }

  @Override
  public boolean isCancelled() {
    return cancelled;
  }

  public MobActionsUser getMobActionsUser() {
    return user;
  }
}
