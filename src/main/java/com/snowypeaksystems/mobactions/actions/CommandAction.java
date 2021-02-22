package com.snowypeaksystems.mobactions.actions;

import com.snowypeaksystems.mobactions.AMobActions;
import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.data.ConsoleCommandData;
import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.event.CommandInteractEvent;
import com.snowypeaksystems.mobactions.player.ConsoleActionUser;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import org.bukkit.Bukkit;

public class CommandAction implements ICommandAction {
  private final ICommandData command;
  private final IInteractiveMob mob;
  private final AMobActions ma;

  /** Creates a CommandAction for a command to be ran by a player. */
  public CommandAction(IInteractiveMob mob, ICommandData data, AMobActions ma) {
    this.mob = mob;
    this.command = data;
    this.ma = ma;
  }

  /** Creates a CommandAction for a command to be ran by a player. */
  @Deprecated
  public CommandAction(IInteractiveMob mob, ICommandData data) {
    this.mob = mob;
    this.command = data;
    this.ma = null;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Executing command");
    boolean isConsoleCommand = ma != null && command instanceof ConsoleCommandData
        && ((ConsoleCommandData) command).isConsoleCommand();

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

      if (!user.performCommand(commandStr)) {
        DebugLogger.getLogger().log("Command execution failed");
        throw new CommandActionException();
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
