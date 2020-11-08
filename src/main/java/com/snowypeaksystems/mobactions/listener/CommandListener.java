package com.snowypeaksystems.mobactions.listener;

import com.snowypeaksystems.mobactions.AMobActions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

/**
 * Handles all command related information for the plugin.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandListener implements ICommandListener {
  private final AMobActions mp;
  // TODO: Add to Messages class
  private static final String[] HELP = {
      "Usage: /mp <subcommand>",
      "/mp warp <warp> - Teleport to a warp",
      "/mp list - List available warps",
      "/mp create portal <warp> - Create a new mob portal",
      "/mp create command <name> <command> - Create a new command mob",
      "/mp remove - Remove a mob's action",
      "/mp cancel - Cancels the current operation",
      "/mp setwarp <name> - Create a warp",
      "/mp delwarp <name> - Delete a warp",
      "/mp reload - Reloads the plugin's configuration",
      "/mp help - Shows this message"
  };

  private static final String[] SUBCOMMANDS =
      {"create", "delwarp", "help", "reload", "remove", "setwarp", "warp", "list", "cancel"};

  public CommandListener(AMobActions parent) {
    this.mp = parent;
  }

  /** Handles tab completion of the remainder of the command. */
  @Override
  public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command,
                                    String alias, String[] args) {
    // TODO: Improve this
    List<String> completions = new ArrayList<>();
    if (command.getName().equals("mp") && args.length == 1) {
      StringUtil.copyPartialMatches(args[0], Arrays.asList(SUBCOMMANDS), completions);
      Collections.sort(completions);
    }

    return completions;
  }

  @Override
  public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label,
                           String[] args) {
    if (args.length == 0) {
      sender.sendMessage(HELP);

      return true;
    }

    //TODO: The rest

    return false;
  }
}
