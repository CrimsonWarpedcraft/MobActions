package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.InteractiveMob;
import com.snowypeaksystems.mobactions.mobevent.MobEvent;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import org.bukkit.event.HandlerList;

/**
 * Event called when a player joins an event mob event.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MobEventJoinEvent extends MobActionsUserEvent
    implements InteractiveMobEvent, MobEventEvent {
  private static final HandlerList handlers = new HandlerList();
  private final InteractiveMob mob;
  private final MobEvent event;

  /** Create a MobEventJoinEvent from a user, mob, and mob event. */
  public MobEventJoinEvent(MobActionsUser user, InteractiveMob mob, MobEvent event) {
    super(user);
    this.mob = mob;
    this.event = event;
  }

  @Override
  public InteractiveMob getInteractiveMob() {
    return mob;
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
