package com.snowypeaksystems.mobactions.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * Events that can be cancelled.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public abstract class CancellableEvent extends Event implements Cancellable {
  private boolean cancelled;

  public CancellableEvent() {
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
}
