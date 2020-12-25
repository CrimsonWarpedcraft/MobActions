package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.warp.IWarp;
import org.bukkit.event.HandlerList;

public class WarpCommandEvent extends MobActionsUserEvent implements WarpEvent {
  private static final HandlerList handlers = new HandlerList();
  private final IWarp warp;

  public WarpCommandEvent(MobActionsUser user, IWarp warp) {
    super(user);
    this.warp = warp;
  }

  @Override
  public IWarp getWarp() {
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
