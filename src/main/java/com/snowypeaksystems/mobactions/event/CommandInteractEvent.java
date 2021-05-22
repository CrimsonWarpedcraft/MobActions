package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.InteractiveMob;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import org.bukkit.event.HandlerList;

/**
 * Event called when a player interacts with a command mob.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandInteractEvent extends MobActionsUserEvent
    implements InteractEvent, ConsoleCommandEvent {
  private static final HandlerList handlers = new HandlerList();
  private final InteractiveMob mob;
  private final String command;
  private final boolean isServerCommand;

  /** Create a CommandInteractEvent from the provided user, mob, command, and command state. */
  public CommandInteractEvent(MobActionsUser user, InteractiveMob mob, String command,
                              boolean isServerCommand) {
    super(user);
    this.mob = mob;
    this.command = command;
    this.isServerCommand = isServerCommand;
  }

  /** Create a CommandInteractEvent from the provided user, mob, and command. */
  public CommandInteractEvent(MobActionsUser user, InteractiveMob mob, String command) {
    this(user, mob, command, false);
  }

  @Override
  public InteractiveMob getInteractiveMob() {
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
