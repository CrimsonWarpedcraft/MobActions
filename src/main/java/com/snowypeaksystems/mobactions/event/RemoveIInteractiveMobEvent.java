package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import org.bukkit.event.HandlerList;

public class RemoveIInteractiveMobEvent extends MobActionsUserEvent
    implements IInteractiveMobEvent {
  private static final HandlerList handlers = new HandlerList();
  private final IInteractiveMob mob;

  public RemoveIInteractiveMobEvent(MobActionsUser user, IInteractiveMob mob) {
    super(user);
    this.mob = mob;
  }

  @Override
  public IInteractiveMob getIInteractiveMob() {
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
