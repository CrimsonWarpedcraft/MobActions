package com.snowypeaksystems.mobportals.listeners;

import static com.snowypeaksystems.mobportals.messages.Messages.gm;

import com.snowypeaksystems.mobportals.IMobPortalPlayer;
import com.snowypeaksystems.mobportals.MobPortals;
import com.snowypeaksystems.mobportals.warps.IWarp;
import com.snowypeaksystems.mobportals.warps.IWarps;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
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
  // TODO: Add to Messages
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
  // How could these be translated?
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
    if (args[0].equals("create") && args.length == 2) {
      String perm = "mobportals.admin.create";
      if (!sender.hasPermission(perm)) {
        sender.sendMessage(gm("permission-error", "mobportals.admin.create"));

      } else if (!(sender instanceof Player)) {
        sender.sendMessage(gm("console-command-error"));

      } else {
        IMobPortalPlayer player = mp.getPlayer((Player) sender);

        if (mp.getWarps().exists(args[1])) {
          player.setCreation(mp.getWarps().get(args[1]));
          player.setDestroying(false);
          sender.sendMessage(
              new String[]{gm("portal-create", args[1]), gm("portal-cancel")});

        } else {
          sender.sendMessage(gm("warp-missing", args[1]));
        }
      }

      return true;
    }

    if (args[0].equals("remove") && args.length == 1) {
      String perm = "mobportals.admin.remove";
      if (!sender.hasPermission(perm)) {
        sender.sendMessage(gm("permission-error", perm));

      } else if (!(sender instanceof Player)) {
        sender.sendMessage(gm("console-command-error"));

      } else {
        IMobPortalPlayer player = mp.getPlayer((Player) sender);

        player.setCreation(null);
        player.setDestroying(true);
        sender.sendMessage(gm("portal-remove"));
      }

      return true;
    }

    if (args[0].equals("cancel") && args.length == 1) {
      if (!(sender instanceof Player)) {
        sender.sendMessage(gm("console-command-error"));

      } else {
        IMobPortalPlayer player = mp.getPlayer((Player) sender);

        if (player.isCreating() || player.isDestroying()) {
          player.setCreation(null);
          player.setDestroying(false);
          sender.sendMessage(gm("portal-cancel-success"));

        } else {
          sender.sendMessage(gm("portal-cancel-error"));
        }
      }

      return true;
    }

    if (args[0].equals("warp") && args.length == 2) {
      String warpPerm = "mobportals.warp";
      if (!sender.hasPermission(warpPerm)) {
        sender.sendMessage(gm("permission-error", warpPerm));

      } else if (!(sender instanceof Player)) {
        sender.sendMessage(gm("console-command-error"));

      } else {
        if (mp.getWarps().exists(args[1])) {
          String allPerm = "mobportals.warp.*";
          String altPerm = "mobportals.warp." + args[1];
          if (sender.hasPermission(allPerm) || sender.hasPermission(altPerm)) {
            mp.getPlayer((Player) sender).warp(mp.getWarps().get(args[1]));
          } else {
            sender.sendMessage(gm("permission-error", allPerm + " or " + altPerm));
          }

        } else {
          sender.sendMessage(gm("warp-missing", args[1]));
        }
      }

      return true;
    }

    if (args[0].equals("list") && args.length == 1) {
      String perm = "mobportals.listwarp";
      if (!sender.hasPermission(perm)) {
        sender.sendMessage(gm("permission-error", perm));

      } else {
        Set<String> warpNames = mp.getWarps().getWarpNames();

        if (!sender.hasPermission("mobportals.warp.*")) {
          warpNames.removeIf(warp -> !sender.hasPermission("mobportals.warp." + warp));
        }

        if (warpNames.size() > 0) {
          sender.sendMessage(new String[]{gm("list-message"),
              String.join(", ", warpNames)});
        } else {
          sender.sendMessage(gm("list-empty-message"));
        }
      }

      return true;
    }

    if (args[0].equals("setwarp") && args.length == 2) {
      String perm = "mobportals.admin.setwarp";
      if (!sender.hasPermission(perm)) {
        sender.sendMessage(gm("permission-error", perm));

      } else if (!(sender instanceof Player)) {
        sender.sendMessage(gm("console-command-error"));

      } else {
        IMobPortalPlayer player = mp.getPlayer((Player) sender);

        try {
          IWarps warps = mp.getWarps();
          warps.add(warps.create(args[1], player.getPlayer().getLocation()));
        } catch (IOException e) {
          sender.sendMessage(gm("warp-create-error"));
          mp.getLogger().log(Level.SEVERE, "Error saving warp file!", e);
        }
      }

      return true;
    }

    if (args[0].equals("delwarp") && args.length == 2) {
      String perm = "mobportals.admin.delwarp";
      if (!sender.hasPermission(perm)) {
        sender.sendMessage(gm("permission-error", perm));

      } else {
        IWarp old = mp.getWarps().remove(args[1]);

        if (old == null) {
          sender.sendMessage(gm("warp-missing", args[1]));
        } else {
          if (!old.delete()) {
            mp.getLogger().log(Level.FINE,
                "Unable to delete the save file for warp " + old.getName());
          }
          sender.sendMessage(gm("warp-delete-success", args[1]));
        }
      }

      return true;
    }

    if (args[0].equals("reload") && args.length == 1) {
      String perm = "mobportals.admin.reload";
      if (!sender.hasPermission(perm)) {
        sender.sendMessage(gm("permission-error", perm));

      } else {
        mp.reloadConfig();
      }

      return true;
    }

    if (args[0].equals("help")) {
      sender.sendMessage(HELP);

      return true;
    }

    return false;
  }
}
