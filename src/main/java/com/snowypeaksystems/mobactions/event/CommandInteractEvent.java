package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import org.bukkit.event.HandlerList;

public class CommandInteractEvent extends MobActionsUserEvent
    implements InteractEvent, CommandEvent {
  private static final HandlerList handlers = new HandlerList();
  private final IInteractiveMob mob;
  private final String command;

  /** Create a CommandInteractEvent from the provided user, mob, and command. */
  public CommandInteractEvent(MobActionsUser user, IInteractiveMob mob, String command) {
    super(user);
    this.mob = mob;
    this.command = command;
  }

  @Override
  public IInteractiveMob getIInteractiveMob() {
    return mob;
  }

  @Override
  public String getCommand() {
    return command;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}
