package com.snowypeaksystems.mobactions.actions;

import com.snowypeaksystems.mobactions.AbstractMobActions;
import com.snowypeaksystems.mobactions.InteractiveMob;
import com.snowypeaksystems.mobactions.data.CommandData;
import com.snowypeaksystems.mobactions.event.CommandInteractEvent;
import com.snowypeaksystems.mobactions.player.ConsoleActionUser;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import org.bukkit.Bukkit;

/**
 * Implementation of CommandAction.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandActionImpl implements CommandAction {
  private final CommandData command;
  private final InteractiveMob mob;
  private final AbstractMobActions ma;

  /** Creates a CommandAction for a command to be ran by a player. */
  public CommandActionImpl(InteractiveMob mob, CommandData data, AbstractMobActions ma) {
    this.mob = mob;
    this.command = data;
    this.ma = ma;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Executing command");
    boolean isConsoleCommand = command.isConsoleCommand();

    if ((!isConsoleCommand && !player.canRunCommand())
        || (isConsoleCommand && player instanceof ConsoleActionUser
        && !((ConsoleActionUser) player).canUseConsoleCommand())) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    String commandStr = command.getCommand(player.getName());
    DebugLogger.getLogger().log("Command: " + commandStr);

    if (!callEvent(player, commandStr, isConsoleCommand)) {
      MobActionsUser user;
      if (isConsoleCommand) {
        user = ma.getPlayer(ma.getServer().getConsoleSender());
      } else {
        user = player;
      }

      String[] commandList = commandStr.split("(?<!\\\\);");
      for (String command : commandList) {
        if (!user.performCommand(command.trim())) {
          DebugLogger.getLogger().log("Command execution failed");
          throw new CommandActionException();
        }
      }

      DebugLogger.getLogger().log("Command executed");
    }
  }

  private boolean callEvent(MobActionsUser player, String commandStr, boolean isConsoleCommand) {
    CommandInteractEvent event = new CommandInteractEvent(player, mob, commandStr,
        isConsoleCommand);
    Bukkit.getPluginManager().callEvent(event);
    return event.isCancelled();
  }

}
