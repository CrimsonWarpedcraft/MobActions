package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;

public class SetWarpEvent extends MobActionsUserEvent {
  private static final HandlerList handlers = new HandlerList();
  private final Location location;
  private final String name;

  /** Create a SetWarpEvent object from the given user and warp name and location. */
  public SetWarpEvent(MobActionsUser user, String name, Location location) {
    super(user);
    this.name = name;
    this.location = location;
  }

  public String getName() {
    return name;
  }

  public Location getLocation() {
    return location;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}
