package com.snowypeaksystems.mobactions.listener;

import com.snowypeaksystems.mobactions.AMobActions;
import com.snowypeaksystems.mobactions.command.CancelCommand;
import com.snowypeaksystems.mobactions.command.CreateCommand;
import com.snowypeaksystems.mobactions.command.DelWarpCommand;
import com.snowypeaksystems.mobactions.command.ListWarpsCommand;
import com.snowypeaksystems.mobactions.command.PlayerCommand;
import com.snowypeaksystems.mobactions.command.ReloadCommand;
import com.snowypeaksystems.mobactions.command.RemoveCommand;
import com.snowypeaksystems.mobactions.command.SetWarpCommand;
import com.snowypeaksystems.mobactions.command.WarpCommand;
import com.snowypeaksystems.mobactions.data.CommandData;
import com.snowypeaksystems.mobactions.data.WarpData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
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
  private final String[] help = {
      "Usage: /mac <subcommand>",
      "/mac create command <name> \"command\" \"description\" - Create a new command mob",
      "/mac create portal <warp> - Create a new mob portal",
      "/mac remove - Remove a mob's action",
      "/mac cancel - Cancels the current operation",
      "/mac warp <warp> - Teleport to a warp",
      "/mac warps - List available warps",
      "/mac setwarp <name> - Create a warp",
      "/mac delwarp <name> - Delete a warp",
      "/mac reload - Reloads the plugin's configuration",
      "/mac - Shows this message"
  };

  private final String[] subcommands =
      {"cancel", "create", "delwarp", "help", "reload", "remove", "setwarp", "warp", "warps"};
  private final String[] createcommands = {"command", "portal"};

  public CommandListener(AMobActions parent) {
    this.ma = parent;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command,
                                    String alias, String[] args) {
    MobActionsUser user = ma.getPlayer(sender);
    List<String> completions = new ArrayList<>();
    if (command.getName().equalsIgnoreCase("mac")) {
      if (args.length == 1) {
        StringUtil.copyPartialMatches(args[0], Arrays.asList(subcommands), completions);
      } else if (args.length == 2) {
        if (args[0].equalsIgnoreCase("create")) {
          StringUtil.copyPartialMatches(args[1], Arrays.asList(createcommands), completions);
        } else if (args[0].equalsIgnoreCase("warp")) {
          Set<String> warps = ma.getWarpManager().getLoadedWarpNames();

          warps.removeIf(warp -> !user.canUseWarp(warp));

          StringUtil.copyPartialMatches(args[1], new ArrayList<>(warps), completions);
        }
      }
    }

    Collections.sort(completions);

    return completions;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label,
                           String[] args) {
    DebugLogger.getLogger().log("Processing command");
    if (command.getName().equalsIgnoreCase("mac")) {
      DebugLogger.getLogger().log("Arguments: " + Arrays.toString(args));
      MobActionsUser user = ma.getPlayer(sender);
      PlayerCommand cmd = null;

      if (args.length > 3 && args[0].equalsIgnoreCase("create")
          && args[1].equalsIgnoreCase("command")) {
        String[] sublist = Arrays.asList(args).subList(4, args.length).toArray(new String[]{});
        List<String> strArgs = parseForStrings(sublist);

        if (strArgs.size() == 2) {
          cmd = new CreateCommand(user, new CommandData(args[3], strArgs.get(0), strArgs.get(1)));
        }
      } else if (args.length == 3 && args[0].equalsIgnoreCase("create")
          && args[1].equalsIgnoreCase("warp")) {
        cmd = new CreateCommand(user, new WarpData(args[2]));
      } else if (args.length == 2 && args[0].equalsIgnoreCase("warp")) {
        cmd = new WarpCommand(user, args[1], ma.getWarpManager());
      } else if (args.length == 2 && args[0].equalsIgnoreCase("setwarp")) {
        cmd = new SetWarpCommand(user, args[1], ma.getWarpManager());
      } else if (args.length == 2 && args[0].equalsIgnoreCase("delwarp")) {
        cmd = new DelWarpCommand(user, args[1], ma.getWarpManager());
      } else if (args.length == 1 && args[0].equalsIgnoreCase("warps")) {
        cmd = new ListWarpsCommand(user, ma.getWarpManager());
      } else if (args.length == 1 && args[0].equalsIgnoreCase("remove")) {
        cmd = new RemoveCommand(user);
      } else if (args.length == 1 && args[0].equalsIgnoreCase("cancel")) {
        cmd = new CancelCommand(user);
      } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
        cmd = new ReloadCommand(user, ma);
      }

      if (cmd != null) {
        DebugLogger.getLogger().log("Running command");
        try {
          cmd.run();
        } catch (PlayerException e) {
          DebugLogger.getLogger().log("Error: " + e.getPlayerFormattedString());
          user.sendMessage(e.getPlayerFormattedString());
        }
      } else {
        DebugLogger.getLogger().log("Command not found");
        user.sendMessage(help);
      }

      return true;
    }

    return false;
  }

  private List<String> parseForStrings(String[] args) {
    String text = String.join(" ", args);
    List<String> strings = new ArrayList<>();
    StringBuilder sb = null;

    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      if (sb == null && c == '"') {
        sb = new StringBuilder();
      } else if (sb != null) {
        if (c == '"') {
          strings.add(sb.toString());
          sb = null;
        } else {
          sb.append(c);
        }
      }
    }

    return strings;
  }
}
