package com.snowypeaksystems.mobportals;

import static com.snowypeaksystems.mobportals.messages.Messages.gm;

import com.snowypeaksystems.mobportals.exceptions.PermissionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
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
      "/mp warp <warp> - Teleport to a warp",
      "/mp list - List available warps",
      "/mp setwarp <name> - Create a warp",
      "/mp delwarp <name> - Delete a warp",
      "/mp reload - Reloads the plugin's configuration",
      "/mp help - Shows this message"
  };
  private static final String[] SUBCOMMANDS =
      {"create", "delwarp", "help", "reload", "remove", "setwarp", "warp", "list"};

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
      sender.sendMessage(gm("console-command-error"));
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
      case "list":
        if (args.length == 1) {
          return list(player);
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
        sender.sendMessage(new String[]{gm("portal-create", name), gm("portal-cancel")});
      } else {
        sender.sendMessage(gm("warp-missing", name));
      }
    } catch (PermissionException e) {
      sender.sendMessage(gm("permission-error", e.getMissingPermission()));
    }
    return true;
  }

  private boolean portalRemove(Player sender) {
    try {
      mp.setRemoving(sender);
      sender.sendMessage(new String[] {gm("portal-remove"), gm("portal-cancel")});
    } catch (PermissionException e) {
      sender.sendMessage(gm("permission-error", e.getMissingPermission()));
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
      sender.sendMessage(gm("cancel-success"));
    } else {
      sender.sendMessage(gm("cancel-error"));
    }

    return true;
  }

  private boolean setWarp(Player sender, String name) {
    try {
      if (!sender.hasPermission("mobportals.setwarp")) {
        throw new PermissionException("mobportals.setwarp");
      }

      if (mp.setWarp(name, sender.getLocation())) {
        sender.sendMessage(gm("warp-create-success", name));
      } else {
        sender.sendMessage(gm("warp-create-error", name));
      }

    } catch (PermissionException e) {
      sender.sendMessage(gm("permission-error", e.getMissingPermission()));
    } catch (IOException e) {
      mp.getServer().getLogger().severe(e.getMessage());
      e.printStackTrace();
      sender.sendMessage(gm("warp-save-error", name));
    }

    return true;
  }

  private boolean delWarp(Player sender, String name) {
    try {
      if (!sender.hasPermission("mobportals.delwarp")) {
        throw new PermissionException("mobportals.delwarp");
      }
      if (mp.delWarp(name)) {
        sender.sendMessage(gm("warp-delete-success", name));
      } else {
        sender.sendMessage(gm("warp-missing", name));
      }

    } catch (PermissionException e) {
      sender.sendMessage(gm("permission-error", e.getMissingPermission()));
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
      sender.sendMessage(gm("permission-error", e.getMissingPermission()));
    }

    return true;
  }

  private boolean list(Player sender) {
    try {
      if (!sender.hasPermission("mobportals.warp")) {
        throw new PermissionException("mobportals.warp");
      }
      Set<String> warpNames = mp.getWarpNames();
      if (warpNames.size() > 0) {
        sender.sendMessage(gm("list-message"));
        sender.sendMessage(String.join(", ", warpNames));
      } else {
        sender.sendMessage(gm("list-empty-message"));
      }
    } catch (PermissionException e) {
      sender.sendMessage(gm("permission-error", e.getMissingPermission()));
    }

    return true;
  }

  private boolean reload(Player sender) {
    try {
      if (!sender.hasPermission("mobportals.reload")) {
        throw new PermissionException("mobportals.reload");
      }
      mp.reload();
      sender.sendMessage(gm("reload-success"));
    } catch (PermissionException e) {
      sender.sendMessage(gm("permission-error", e.getMissingPermission()));
    } catch (IOException e) {
      mp.getServer().getLogger().severe(e.getMessage());
      e.printStackTrace();
      sender.sendMessage(gm("reload-error"));
    }

    return true;
  }

  private boolean help(Player sender) {
    sender.sendMessage(HELP);

    return true;
  }
}
