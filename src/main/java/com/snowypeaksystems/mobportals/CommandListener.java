package com.snowypeaksystems.mobportals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

/**
 * Handles all command related information for the plugin.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandListener implements TabExecutor {
  private final MobPortals mp;
  private static final String[] HELP = {
      "Usage: /mp <subcommand>",
      "/mp create <warp> - Create a portal to the warp",
      "/mp remove - Remove a portal",
      "/mp setwarp <name> - Create a warp",
      "/mp delwarp <name> - Delete a warp",
      "/mp warp <warp> - Teleport to a warp",
      "/mp reload - Reloads the plugin's configuration",
      "/mp help - Shows this message"
  };
  private static final String[] SUBCOMMANDS =
      {"create", "delwarp", "help", "reload", "remove", "setwarp", "warp"};

  public CommandListener(MobPortals parent) {
    this.mp = parent;
  }

  /** Handles tab completion of the remainder of the command. */
  @Override
  public List<String> onTabComplete(CommandSender sender, Command command,
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
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(mp.messages.consoleCommandError);
      return true;
    }

    Player player = (Player) sender;

    switch (args[0]) {
      case "create":
        if (args.length == 2) {
          return portalCreate(player, args[1]);
        }
        break;
      case "remove":
        if (args.length == 1) {
          return portalRemove(player);
        }
        break;
      case "cancel":
        if (args.length == 1) {
          return cancel(player);
        }
        break;
      case "setwarp":
        if (args.length == 2) {
          return setWarp(player, args[1]);
        }
        break;
      case "delwarp":
        if (args.length == 2) {
          return delWarp(player, args[1]);
        }
        break;
      case "warp":
        if (args.length == 2) {
          return warp(player, args[1]);
        }
        break;
      case "reload":
        if (args.length == 1) {
          return reload(player);
        }
        break;
      case "help":
        if (args.length == 1) {
          return help(player);
        }
        break;
      default:
        return false;
    }

    return false;
  }

  private boolean portalCreate(Player sender, String name) {
    try {
      if (mp.warpExists(name)) {
        mp.setCreating(sender, name);
        sender.sendMessage(new String[]
            {mp.messages.portalCreateInfo.replaceAll(mp.messages.warpToken, name),
                mp.messages.cancelInfo});
      } else {
        sender.sendMessage(
            mp.messages.warpNotFound.replaceAll(mp.messages.warpToken, name));
      }
    } catch (PermissionException e) {
      sender.sendMessage(
          mp.messages.permissionError.replaceAll(mp.messages.permToken, e.getMissingPermission()));
    }
    return true;
  }

  private boolean portalRemove(Player sender) {
    try {
      mp.setRemoving(sender);
      sender.sendMessage(new String[] {mp.messages.portalRemoveInfo, mp.messages.cancelInfo});
    } catch (PermissionException e) {
      sender.sendMessage(
          mp.messages.permissionError.replaceAll(mp.messages.permToken, e.getMissingPermission()));
    }

    return true;
  }

  private boolean cancel(Player sender) {
    boolean changed = false;

    if (mp.isCreating(sender)) {
      mp.stopCreating(sender);
      changed = true;
    }

    if (mp.isRemoving(sender)) {
      mp.stopRemoving(sender);
      changed = true;
    }

    if (changed) {
      sender.sendMessage(mp.messages.cancelSuccess);
    } else {
      sender.sendMessage(mp.messages.cancelError);
    }

    return true;
  }

  private boolean setWarp(Player sender, String name) {
    try {
      if (!sender.hasPermission("mobportals.setwarp")) {
        throw new PermissionException("mobportals.setwarp");
      }

      if (mp.setWarp(name, sender.getLocation())) {
        sender.sendMessage(mp.messages.warpCreateSuccess.replaceAll(mp.messages.warpToken, name));
      } else {
        sender.sendMessage(mp.messages.warpCreateError.replaceAll(mp.messages.warpToken, name));
      }

    } catch (PermissionException e) {
      sender.sendMessage(
          mp.messages.permissionError.replaceAll(mp.messages.permToken, e.getMissingPermission()));
    }

    return true;
  }

  private boolean delWarp(Player sender, String name) {
    try {
      if (!sender.hasPermission("mobportals.delwarp")) {
        throw new PermissionException("mobportals.delwarp");
      }
      if (mp.delWarp(name)) {
        sender.sendMessage(mp.messages.warpDeleteSuccess.replaceAll(mp.messages.warpToken, name));
      } else {
        sender.sendMessage(mp.messages.warpNotFound.replaceAll(mp.messages.warpToken, name));
      }

    } catch (PermissionException e) {
      sender.sendMessage(
          mp.messages.permissionError.replaceAll(mp.messages.permToken, e.getMissingPermission()));
    }

    return true;
  }

  private boolean warp(Player sender, String name) {
    try {
      if (!sender.hasPermission("mobportals.warp")) {
        throw new PermissionException("mobportals.warp");
      }
      mp.warpPlayer(sender, name);
    } catch (PermissionException e) {
      sender.sendMessage(
          mp.messages.permissionError.replaceAll(mp.messages.permToken, e.getMissingPermission()));
    }

    return true;
  }

  private boolean reload(Player sender) {
    try {
      if (!sender.hasPermission("mobportals.reload")) {
        throw new PermissionException("mobportals.reload");
      }
      mp.reload();
      sender.sendMessage(mp.messages.reloadComplete);
    } catch (PermissionException e) {
      sender.sendMessage(
          mp.messages.permissionError.replaceAll(mp.messages.permToken, e.getMissingPermission()));
    }

    return true;
  }

  private boolean help(Player sender) {
    sender.sendMessage(HELP);

    return true;
  }
}
