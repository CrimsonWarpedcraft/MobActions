package com.snowypeaksystems.mobactions.actions;

import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.player.IMobActionsPlayer;
import java.util.concurrent.CompletableFuture;
import org.bukkit.entity.Player;

public class CommandAction implements ICommandAction {
  private final ICommandData command;
  private final IMobActionsPlayer player;
  private final CompletableFuture<Boolean> future;

  /**
   * Creates a CommandAction for a command to be ran by a player.
   * @param player the player running the command
   * @param command the command that the player will run
   * @param future the code to run if the execution is successful
   */
  public CommandAction(IMobActionsPlayer player, ICommandData command,
                       CompletableFuture<Boolean> future) {
    this.player = player;
    this.command = command;
    this.future = future;
  }

  @Override
  public void run() {
    if (player.canRunCommand(command)) {
      Player p = player.getPlayer();
      future.complete(p.performCommand(command.replace(p.getDisplayName())));
    }
  }
}
