package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import org.bukkit.event.HandlerList;

public class CreateIInteractiveMobEvent extends MobActionsUserEvent
    implements IInteractiveMobEvent, MobDataEvent {
  private static final HandlerList handlers = new HandlerList();
  private final IInteractiveMob mob;
  private final MobData data;

  /** Create a IInteractiveMob creation event with the provided mob and data. */
  public CreateIInteractiveMobEvent(MobActionsUser user, IInteractiveMob mob, MobData data) {
    super(user);
    this.mob = mob;
    this.data = data;
  }

  @Override
  public MobData getData() {
    return data;
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
