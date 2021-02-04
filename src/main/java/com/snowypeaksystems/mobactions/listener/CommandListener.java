package com.snowypeaksystems.mobactions.listener;

import com.snowypeaksystems.mobactions.AMobActions;
import com.snowypeaksystems.mobactions.command.CancelCommand;
import com.snowypeaksystems.mobactions.command.CreateCommand;
import com.snowypeaksystems.mobactions.command.DelWarpCommand;
import com.snowypeaksystems.mobactions.command.EventCancelCommand;
import com.snowypeaksystems.mobactions.command.EventCreateCommand;
import com.snowypeaksystems.mobactions.command.EventForceStartCommand;
import com.snowypeaksystems.mobactions.command.EventInfoCommand;
import com.snowypeaksystems.mobactions.command.EventListCommand;
import com.snowypeaksystems.mobactions.command.EventOpenCommand;
import com.snowypeaksystems.mobactions.command.EventRemoveCommand;
import com.snowypeaksystems.mobactions.command.ListWarpsCommand;
import com.snowypeaksystems.mobactions.command.PlayerCommand;
import com.snowypeaksystems.mobactions.command.ReloadCommand;
import com.snowypeaksystems.mobactions.command.RemoveCommand;
import com.snowypeaksystems.mobactions.command.SetWarpCommand;
import com.snowypeaksystems.mobactions.command.WarpCommand;
import com.snowypeaksystems.mobactions.data.CommandData;
import com.snowypeaksystems.mobactions.data.EventData;
import com.snowypeaksystems.mobactions.data.WarpData;
import com.snowypeaksystems.mobactions.mobevent.IMobEvent;
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
      "/mac action create command \"command\" \"name tag text\" - Create a new command mob",
      "/mac action create event <event> - Create a new event mob action",
      "/mac action create warp <warp> - Create a new warp mob action",
      "/mac action remove - Remove a mob action",
      "/mac action cancel - Cancels the current action operation",
      "/mac events create <event name> <wait seconds> [max players] command \"command\" - Create a "
          + "command event with an optional player limit",
      "/mac events create <event name> <wait seconds> [max players] warp <warp name> - Create a "
          + "warp event with an optional player limit",
      "/mac events open <name> - Opens event, starts event timer",
      "/mac events cancel <name> - Cancel an event",
      "/mac events remove <name> - Remove an event",
      "/mac events forcestart <name> - Forces an event to start now",
      "/mac events info <name> - Show information about the event",
      "/mac events - List all events",
      "/mac warp <warp> - Teleport to a warp",
      "/mac warps - List available warps",
      "/mac warps set <name> - Create a warp",
      "/mac warps remove <name> - Delete a warp",
      "/mac reload - Reloads the plugin's configuration",
      "/mac - Shows this message"
  };

  private final String[] subcommands = {"events", "help", "action", "reload", "warp", "warps"};
  private final String[] createCommands = {"command", "event", "warp"};
  private final String[] eventCommands =
      {"cancel", "create", "forcestart", "info", "open", "remove"};
  private final String[] eventTypes = {"command", "warp"};
  private final String[] mobCommands = {"cancel", "create", "remove"};
  private final String[] warpCommands = {"set", "remove"};

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
        if (args[0].equalsIgnoreCase("events")) {
          StringUtil.copyPartialMatches(args[1], Arrays.asList(eventCommands), completions);

        } else if (args[0].equalsIgnoreCase("action")) {
          StringUtil.copyPartialMatches(args[1], Arrays.asList(mobCommands), completions);

        } else if (args[0].equalsIgnoreCase("warps")) {
          StringUtil.copyPartialMatches(args[1], Arrays.asList(warpCommands), completions);

        } else if (args[0].equalsIgnoreCase("warp")) {
          if (user.canUseWarpCommand()) {
            Set<String> warps = ma.getWarpManager().getLoadedWarpNames();
            warps.removeIf(warp -> !user.canUseWarp(warp));
            StringUtil.copyPartialMatches(args[1], new ArrayList<>(warps), completions);
          }
        }

      } else if (args.length == 3) {
        if (args[0].equalsIgnoreCase("action")
            && args[1].equalsIgnoreCase("create") && user.canCreate()) {
          StringUtil.copyPartialMatches(args[2], Arrays.asList(createCommands), completions);

        } else if (args[0].equalsIgnoreCase("events")) {
          if (args[1].equalsIgnoreCase("cancel") && user.canCancelEvents()) {
            Set<IMobEvent> events = ma.getMobEventManager().getLoadedEvents();
            ArrayList<String> names = new ArrayList<>();

            for (IMobEvent event : events) {
              if (event.getState() != IMobEvent.State.CLOSED) {
                names.add(event.getAlias().toLowerCase());
              }
            }

            StringUtil.copyPartialMatches(args[2], names, completions);

          } else if (args[1].equalsIgnoreCase("forcestart") && user.canStartEvents()) {
            Set<IMobEvent> events = ma.getMobEventManager().getLoadedEvents();
            ArrayList<String> names = new ArrayList<>();

            for (IMobEvent event : events) {
              if (event.getState() == IMobEvent.State.OPEN) {
                names.add(event.getAlias().toLowerCase());
              }
            }

            StringUtil.copyPartialMatches(args[2], names, completions);

          } else if (args[1].equalsIgnoreCase("open") && user.canStartEvents()) {
            Set<IMobEvent> events = ma.getMobEventManager().getLoadedEvents();
            ArrayList<String> names = new ArrayList<>();

            for (IMobEvent event : events) {
              if (event.getState() == IMobEvent.State.CLOSED) {
                names.add(event.getAlias().toLowerCase());
              }
            }

            StringUtil.copyPartialMatches(args[2], names, completions);

          } else if (args[1].equalsIgnoreCase("remove") && user.canRemoveEvents()) {
            Set<String> events = ma.getMobEventManager().getLoadedEventNames();
            StringUtil.copyPartialMatches(args[2], new ArrayList<>(events), completions);

          } else if (args[1].equalsIgnoreCase("info") && user.canGetEventInfo()) {
            Set<String> events = ma.getMobEventManager().getLoadedEventNames();
            StringUtil.copyPartialMatches(args[2], new ArrayList<>(events), completions);
          }

        } else if (args[0].equalsIgnoreCase("warps")
            && args[1].equalsIgnoreCase("remove") && user.canRemoveWarp()) {
          Set<String> warps = ma.getWarpManager().getLoadedWarpNames();
          StringUtil.copyPartialMatches(args[2], new ArrayList<>(warps), completions);
        }
      } else if (args.length == 4) {
        if (args[0].equalsIgnoreCase("action")
            && args[1].equalsIgnoreCase("create") && user.canCreate()) {
          if (args[2].equalsIgnoreCase("event")) {
            Set<String> events = ma.getMobEventManager().getLoadedEventNames();
            StringUtil.copyPartialMatches(args[3], new ArrayList<>(events), completions);

          } else if (args[2].equalsIgnoreCase("warp")) {
            Set<String> warps = ma.getWarpManager().getLoadedWarpNames();
            StringUtil.copyPartialMatches(args[3], new ArrayList<>(warps), completions);
          }

        }
      } else if (args.length >= 5) {
        if (args[0].equalsIgnoreCase("events")
            && args[1].equalsIgnoreCase("create") && user.canCreate()) {
          int typeIndexOffset;
          try {
            Integer.parseInt(args[4]);
            typeIndexOffset = 1;
          } catch (NumberFormatException e) {
            typeIndexOffset = 0;
          }

          if (args.length == 5 + typeIndexOffset) {
            StringUtil.copyPartialMatches(args[4 + typeIndexOffset], Arrays.asList(eventTypes),
                completions);
          } else if (args.length == 6 + typeIndexOffset
              && args[4 + typeIndexOffset].equalsIgnoreCase("warp")) {
            Set<String> warps = ma.getWarpManager().getLoadedWarpNames();
            StringUtil.copyPartialMatches(args[5 + typeIndexOffset], new ArrayList<>(warps),
                completions);
          }
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

      if (args.length >= 6 && args[0].equalsIgnoreCase("events")
          && args[1].equalsIgnoreCase("create")) {
        int playerLimit;
        int typeIndexOffset;

        try {
          playerLimit = Integer.parseInt(args[4]);
          typeIndexOffset = 1;
        } catch (NumberFormatException e) {
          playerLimit = 0;
          typeIndexOffset = 0;
        }

        try {
          long waitTime = Long.parseLong(args[3]);

          if (args[4 + typeIndexOffset].equalsIgnoreCase("command")) {
            String[] sublist = Arrays.asList(args).subList(5 + typeIndexOffset, args.length)
                .toArray(new String[]{});
            List<String> strArgs = parseForStrings(sublist);
            if (strArgs.size() == 1) {
              cmd = new EventCreateCommand(args[2], new CommandData(strArgs.get(0)), waitTime,
                  playerLimit, ma.getMobEventManager());
            }

          } else if (args[4 + typeIndexOffset].equalsIgnoreCase("warp")) {
            cmd = new EventCreateCommand(args[2], new WarpData(args[5 + typeIndexOffset]), waitTime,
                playerLimit, ma.getMobEventManager());
          }
        } catch (NumberFormatException e) {
          DebugLogger.getLogger().log("Wait time is not a valid long");
        }

      } else if (args.length >= 5 && args[0].equalsIgnoreCase("action")
          && args[1].equalsIgnoreCase("create")
          && args[2].equalsIgnoreCase("command")) {
        String[] sublist = Arrays.asList(args).subList(3, args.length).toArray(new String[]{});
        List<String> strArgs = parseForStrings(sublist);

        DebugLogger.getLogger().log("String arguments: " + strArgs.toString());
        if (strArgs.size() == 2) {
          cmd = new CreateCommand(new CommandData(strArgs.get(0), strArgs.get(1)));
        }
      } else if (args.length == 4 && args[0].equalsIgnoreCase("action")
          && args[1].equalsIgnoreCase("create")
          && args[2].equalsIgnoreCase("event")) {
        cmd = new CreateCommand(new EventData(args[3]));
      } else if (args.length == 4 && args[0].equalsIgnoreCase("action")
          && args[1].equalsIgnoreCase("create")
          && args[2].equalsIgnoreCase("warp")) {
        cmd = new CreateCommand(new WarpData(args[3]));
      } else if (args.length == 3 && args[0].equalsIgnoreCase("events")
          && args[1].equalsIgnoreCase("open")) {
        cmd = new EventOpenCommand(args[2], ma.getMobEventManager());
      } else if (args.length == 3 && args[0].equalsIgnoreCase("events")
          && args[1].equalsIgnoreCase("cancel")) {
        cmd = new EventCancelCommand(args[2], ma.getMobEventManager());
      } else if (args.length == 3 && args[0].equalsIgnoreCase("events")
          && args[1].equalsIgnoreCase("remove")) {
        cmd = new EventRemoveCommand(args[2], ma.getMobEventManager());
      } else if (args.length == 3 && args[0].equalsIgnoreCase("events")
          && args[1].equalsIgnoreCase("forcestart")) {
        cmd = new EventForceStartCommand(args[2], ma.getMobEventManager());
      } else if (args.length == 3 && args[0].equalsIgnoreCase("events")
          && args[1].equalsIgnoreCase("info")) {
        cmd = new EventInfoCommand(args[2], ma.getMobEventManager());
      } else if (args.length == 3 && args[0].equalsIgnoreCase("warps")
          && args[1].equalsIgnoreCase("set")) {
        cmd = new SetWarpCommand(args[2], ma.getWarpManager());
      } else if (args.length == 3 && args[0].equalsIgnoreCase("warps")
          && args[1].equalsIgnoreCase("remove")) {
        cmd = new DelWarpCommand(args[2], ma.getWarpManager());
      } else if (args.length == 2 && args[0].equalsIgnoreCase("warp")) {
        cmd = new WarpCommand(args[1], ma.getWarpManager());
      } else if (args.length == 2 && args[0].equalsIgnoreCase("action")
          && args[1].equalsIgnoreCase("remove")) {
        cmd = new RemoveCommand();
      } else if (args.length == 2 && args[0].equalsIgnoreCase("action")
          && args[1].equalsIgnoreCase("cancel")) {
        cmd = new CancelCommand();
      } else if (args.length == 1 && args[0].equalsIgnoreCase("events")) {
        cmd = new EventListCommand(ma.getMobEventManager());
      } else if (args.length == 1 && args[0].equalsIgnoreCase("warps")) {
        cmd = new ListWarpsCommand(ma.getWarpManager());
      } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
        cmd = new ReloadCommand(ma);
      }

      if (cmd != null) {
        DebugLogger.getLogger().log("Running command");
        try {
          cmd.run(user);
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
      if (sb == null && c == '"' && (i == 0 || (text.charAt(i - 1) != '\\'
          || (i >= 2 && text.charAt(i - 2) == '\\')))) {
        sb = new StringBuilder();
      } else if (sb != null) {
        if (c == '"' && (text.charAt(i - 1) != '\\' || (i >= 2 && text.charAt(i - 2) == '\\'))) {
          String str = sb.toString();
          if (str.length() > 0) {
            strings.add(str);
          }
          sb = null;
        } else if (i == text.length() - 1 || text.charAt(i) != '\\' || text.charAt(i + 1) != '"'
            || (i >= 2 && text.charAt(i - 2) == '\\')) {
          sb.append(c);
        }
      }
    }

    return strings;
  }
}
