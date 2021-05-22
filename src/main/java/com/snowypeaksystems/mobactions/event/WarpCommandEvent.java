package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.warp.Warp;
import org.bukkit.event.HandlerList;

/**
 * Event called when a player warps using the warp command.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WarpCommandEvent extends MobActionsUserEvent implements WarpEvent {
  private static final HandlerList handlers = new HandlerList();
  private final Warp warp;

  public WarpCommandEvent(MobActionsUser user, Warp warp) {
    super(user);
    this.warp = warp;
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
