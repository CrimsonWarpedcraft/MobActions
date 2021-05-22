package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.InteractiveMob;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import org.bukkit.event.HandlerList;

/**
 * Event called when a player creates an interactive mob.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CreateInteractiveMobEvent extends MobActionsUserEvent
    implements InteractiveMobEvent, MobDataEvent {
  private static final HandlerList handlers = new HandlerList();
  private final InteractiveMob mob;
  private final MobData data;

  /** Create a InteractiveMob creation event with the provided mob and data. */
  public CreateInteractiveMobEvent(MobActionsUser user, InteractiveMob mob, MobData data) {
    super(user);
    this.mob = mob;
    this.data = data;
  }

  @Override
  public MobData getData() {
    return data;
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
