package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import org.bukkit.event.HandlerList;

public class CommandInteractEvent extends MobActionsUserEvent
    implements InteractEvent, ConsoleCommandEvent {
  private static final HandlerList handlers = new HandlerList();
  private final IInteractiveMob mob;
  private final String command;
  private final boolean isServerCommand;

  /** Create a CommandInteractEvent from the provided user, mob, command, and command state. */
  public CommandInteractEvent(MobActionsUser user, IInteractiveMob mob, String command,
                              boolean isServerCommand) {
    super(user);
    this.mob = mob;
    this.command = command;
    this.isServerCommand = isServerCommand;
  }

  /** Create a CommandInteractEvent from the provided user, mob, and command. */
  public CommandInteractEvent(MobActionsUser user, IInteractiveMob mob, String command) {
    this(user, mob, command, false);
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

  @Override
  public boolean isConsoleCommand() {
    return isServerCommand;
  }
}
