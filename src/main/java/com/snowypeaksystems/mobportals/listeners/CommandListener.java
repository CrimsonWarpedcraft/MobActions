package com.snowypeaksystems.mobportals.listeners;

import static com.snowypeaksystems.mobportals.messages.Messages.gm;

import com.snowypeaksystems.mobportals.AbstractMobPortals;
import com.snowypeaksystems.mobportals.IMobPortalPlayer;
import com.snowypeaksystems.mobportals.MobCommand;
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
  private final AbstractMobPortals mp;
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
  // How could these be translated?
  private static final String[] SUBCOMMANDS =
      {"create", "delwarp", "help", "reload", "remove", "setwarp", "warp", "list", "cancel"};

  public CommandListener(AbstractMobPortals parent) {
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
    if (args.length == 0) {
      sender.sendMessage(HELP);

      return true;
    }

    if (args[0].equals("create") && args.length > 2) {
      if (args[1].equals("portal") && args.length == 3) {
        String perm = "mobportals.admin.createportal";
        if (!sender.hasPermission(perm)) {
          sender.sendMessage(gm("permission-error", "mobportals.admin.createportal"));

        } else if (!(sender instanceof Player)) {
          sender.sendMessage(gm("console-command-error"));

        } else {
          IMobPortalPlayer player = mp.getPlayer((Player) sender);

          if (mp.getWarps().exists(args[2])) {
            player.setCreation(IMobPortalPlayer.Type.PORTAL, mp.getWarps().get(args[2]));
            sender.sendMessage(
                new String[]{gm("portal-create", args[2]), gm("edit-cancel")});

          } else {
            sender.sendMessage(gm("warp-missing", args[2]));
          }
        }
      } else if (args[1].equals("command") && args.length >= 4) {
        String perm = "mobportals.admin.createcommand";
        if (!sender.hasPermission(perm)) {
          sender.sendMessage(gm("permission-error", "mobportals.admin.createportal"));

        } else if (!(sender instanceof Player)) {
          sender.sendMessage(gm("console-command-error"));

        } else {
          IMobPortalPlayer player = mp.getPlayer((Player) sender);
          String rawCommand = String.join(" ",
              Arrays.copyOfRange(args, 3, args.length));
          player.setCreation(IMobPortalPlayer.Type.COMMAND, new MobCommand(args[2], rawCommand));
          sender.sendMessage(
              new String[]{gm("command-create", args[2]), gm("edit-cancel")});
        }

      } else {
        return false;
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

        player.setDestroying(true);
        sender.sendMessage(gm("edit-remove"));
      }

      return true;
    }

    if (args[0].equals("cancel") && args.length == 1) {
      if (!(sender instanceof Player)) {
        sender.sendMessage(gm("console-command-error"));

      } else {
        IMobPortalPlayer player = mp.getPlayer((Player) sender);

        if (player.isCreating() || player.isDestroying()) {
          player.setCreation(IMobPortalPlayer.Type.NONE, null);
          player.setDestroying(false);
          sender.sendMessage(gm("edit-cancel-success"));

        } else {
          sender.sendMessage(gm("edit-cancel-error"));
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
          IWarp warp = warps.create(args[1], player.getPlayer().getLocation());
          warps.add(warp);
          warp.save();
          sender.sendMessage(gm("warp-create-success", warp.getName()));
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
        if (sender instanceof Player) {
          sender.sendMessage(gm("reload-success"));
        }
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
