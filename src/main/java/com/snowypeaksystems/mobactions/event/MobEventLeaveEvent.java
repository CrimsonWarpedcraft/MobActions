package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.mobevent.IMobEvent;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import org.bukkit.event.HandlerList;

public class MobEventLeaveEvent extends MobActionsUserEvent
    implements IInteractiveMobEvent, MobEventEvent {
  private static final HandlerList handlers = new HandlerList();
  private final IInteractiveMob mob;
  private final IMobEvent event;

  /** Create a MobEventLeaveEvent from a user, mob, and mob event. */
  public MobEventLeaveEvent(MobActionsUser user, IInteractiveMob mob, IMobEvent event) {
    super(user);
    this.mob = mob;
    this.event = event;
  }

  @Override
  public IInteractiveMob getIInteractiveMob() {
    return mob;
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
