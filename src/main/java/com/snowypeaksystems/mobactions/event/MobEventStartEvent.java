package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.mobevent.IMobEvent;
import org.bukkit.event.HandlerList;

public class MobEventStartEvent extends CancellableEvent implements MobEventEvent {
  private static final HandlerList handlers = new HandlerList();
  private final IMobEvent event;

  public MobEventStartEvent(IMobEvent event) {
    this.event = event;
  }

  @Override
  public IMobEvent getMobEvent() {
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
