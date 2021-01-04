package com.snowypeaksystems.mobactions.actions;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.event.CommandInteractEvent;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import org.bukkit.Bukkit;

public class CommandAction implements ICommandAction {
  private final ICommandData command;
  private final IInteractiveMob mob;

  /** Creates a CommandAction for a command to be ran by a player. */
  public CommandAction(IInteractiveMob mob, ICommandData data) {
    this.mob = mob;
    this.command = data;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Executing command");
    if (!player.canRunCommand()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    String commandStr = command.getCommand(player.getName());
    DebugLogger.getLogger().log("Command: " + commandStr);

    if (mob == null || !callEvent(player, commandStr)) {
      if (!player.performCommand(commandStr)) {
        DebugLogger.getLogger().log("Command execution failed");
        throw new CommandActionException();
      }

      DebugLogger.getLogger().log("Command executed");
    }
  }

  private boolean callEvent(MobActionsUser player, String commandStr) {
    CommandInteractEvent event = new CommandInteractEvent(player, mob, commandStr);
    Bukkit.getPluginManager().callEvent(event);
    return event.isCancelled();
  }

}
