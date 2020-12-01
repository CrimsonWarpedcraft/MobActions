package com.snowypeaksystems.mobactions.listener;

import com.snowypeaksystems.mobactions.AMobActions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

/**
 * Handles all command related information for the plugin.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandListener implements ICommandListener {
  private final AMobActions ma;
  // TODO: Add to Messages class
  private static final String[] HELP = {
      "Usage: /mac <subcommand>",
      "/mac warp <warp> - Teleport to a warp",
      "/mac list - List available warps",
      "/mac create portal <warp> - Create a new mob portal",
      "/mac create command <name> <command> - Create a new command mob",
      "/mac remove - Remove a mob's action",
      "/mac cancel - Cancels the current operation",
      "/mac setwarp <name> - Create a warp",
      "/mac delwarp <name> - Delete a warp",
      "/mac reload - Reloads the plugin's configuration",
      "/mac help - Shows this message"
  };

  private static final String[] SUBCOMMANDS =
      {"create", "delwarp", "help", "reload", "remove", "setwarp", "warp", "list", "cancel"};

  public CommandListener(AMobActions parent) {
    this.ma = parent;
  }

  /** Handles tab completion of the remainder of the command. */
  @Override
  public List<String> onTabComplete(CommandSender sender, Command command,
                                    String alias, String[] args) {
    // TODO: Improve this
    List<String> completions = new ArrayList<>();
    if (command.getName().equals("mac") && args.length == 1) {
      StringUtil.copyPartialMatches(args[0], Arrays.asList(SUBCOMMANDS), completions);
      Collections.sort(completions);
    }

    return completions;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label,
                           String[] args) {
    if (args.length == 0) {
      sender.sendMessage(HELP);

      return true;
    }

    //TODO: The rest

    return false;
  }
}
