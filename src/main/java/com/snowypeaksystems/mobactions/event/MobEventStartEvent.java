package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.mobevent.MobEvent;
import org.bukkit.event.HandlerList;

/**
 * Event called when an event mob event is started.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MobEventStartEvent extends CancellableEvent implements MobEventEvent {
  private static final HandlerList handlers = new HandlerList();
  private final MobEvent event;

  public MobEventStartEvent(MobEvent event) {
    this.event = event;
  }

  @Override
  public MobEvent getMobEvent() {
    return event;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}
