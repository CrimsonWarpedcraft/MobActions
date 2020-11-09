package com.snowypeaksystems.mobactions.command;

import com.snowypeaksystems.mobactions.player.PlayerException;
import java.util.List;
import org.bukkit.command.CommandSender;

/**
 * Runs when a player issues a MobActions command.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface PlayerCommand {
  /** Returns a help message for this command. */
  String getHelpMessage();

  /** Returns the list of completions available for this command with the given arguments. */
  List<String> getCompletions(String[] args);

  /** Runs the command with the given arguments. */
  void run(CommandSender sender, String[] args) throws PlayerException;
}
