package com.snowypeaksystems.mobactions.actions;

import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.player.CommandActionException;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;

public class CommandAction implements ICommandAction {
  private final ICommandData command;
  private final MobActionsUser player;

  /**
   * Creates a CommandAction for a command to be ran by a player.
   * @param player the player running the command
   * @param command the command that the player will run
   */
  public CommandAction(MobActionsUser player, ICommandData command) {
    this.player = player;
    this.command = command;
  }

  @Override
  public void run() throws PlayerException {
    DebugLogger.getLogger().log("Executing command");
    if (!player.canRunCommand(command.getAlias())) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    String commandStr = command.getCommand(player.getName());
    DebugLogger.getLogger().log("Command: " + commandStr);
    if (!player.performCommand(commandStr)) {
      DebugLogger.getLogger().log("Command execution failed");
      throw new CommandActionException();
    }

    DebugLogger.getLogger().log("Command executed");
  }
}
