package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.warp.IWarp;
import org.bukkit.event.HandlerList;

public class WarpInteractEvent extends MobActionsUserEvent implements InteractEvent, WarpEvent {
  private static final HandlerList handlers = new HandlerList();
  private final IInteractiveMob mob;
  private final IWarp warp;

  /** Create a WarpInteractEvent with the provided user, mob, and warp. */
  public WarpInteractEvent(MobActionsUser user, IInteractiveMob mob, IWarp warp) {
    super(user);
    this.mob = mob;
    this.warp = warp;
  }


  @Override
  public IInteractiveMob getIInteractiveMob() {
    return mob;
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
