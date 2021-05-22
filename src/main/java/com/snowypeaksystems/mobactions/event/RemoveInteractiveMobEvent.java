package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.InteractiveMob;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import org.bukkit.event.HandlerList;

/**
 * Event called when a player removes an interactive mob.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class RemoveInteractiveMobEvent extends MobActionsUserEvent
    implements InteractiveMobEvent {
  private static final HandlerList handlers = new HandlerList();
  private final InteractiveMob mob;

  public RemoveInteractiveMobEvent(MobActionsUser user, InteractiveMob mob) {
    super(user);
    this.mob = mob;
  }

  @Override
  public InteractiveMob getInteractiveMob() {
    return mob;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}
