package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.InteractiveMob;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.warp.Warp;
import org.bukkit.event.HandlerList;

/**
 * Event called when a player interacts with a warp mob.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WarpInteractEvent extends MobActionsUserEvent implements InteractEvent, WarpEvent {
  private static final HandlerList handlers = new HandlerList();
  private final InteractiveMob mob;
  private final Warp warp;

  /** Create a WarpInteractEvent with the provided user, mob, and warp. */
  public WarpInteractEvent(MobActionsUser user, InteractiveMob mob, Warp warp) {
    super(user);
    this.mob = mob;
    this.warp = warp;
  }


  @Override
  public InteractiveMob getInteractiveMob() {
    return mob;
  }

  @Override
  public Warp getWarp() {
    return warp;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}
