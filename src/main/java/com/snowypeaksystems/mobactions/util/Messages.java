package com.snowypeaksystems.mobactions.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Collection of messages to be used throughout the project.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public final class Messages {
  private static File dataDir;
  private static final Map<String, MessageImpl> messages = new HashMap<>();

  public static void setDataDir(File dataDir) {
    Messages.dataDir = dataDir;
  }

  /** Loads message data. */
  public static void initialize() {
    messages.clear();
    YamlConfiguration rawMessages = new YamlConfiguration();
    rawMessages.options().copyDefaults(true);
    rawMessages.addDefaults(getDefaults());

    if (dataDir != null) {
      String messagesLoc = dataDir.getPath() + "/messages.yml";
      DebugLogger.getLogger().log("Attempting to load and update message file at " + messagesLoc);

      try {
        rawMessages.load(messagesLoc);
      } catch (IOException e) {
        DebugLogger.getLogger().log("Error reading messages file");
      } catch (InvalidConfigurationException e) {
        DebugLogger.getLogger().log("Invalid messages file");
        Bukkit.getLogger().log(Level.WARNING, "Message file is invalid!");
      }

      try {
        // This "updates" the file
        rawMessages.save(messagesLoc);
      } catch (IOException exception) {
        DebugLogger.getLogger().log("Error saving messages file");
      }
    } else {
      DebugLogger.getLogger().log("No data directory provided, skip writing messages");
    }

    for (String key : rawMessages.getKeys(false)) {
      String lowerKey = key.toLowerCase();
      String message = rawMessages.getString(lowerKey);
      if (message != null) {
        messages.put(lowerKey, new MessageImpl(message));
      }
    }
  }

  /**
   * Get the message for the given string, replacing instances of the token with args.
   *
   * @param key the identifier of the message
   * @param args list of strings to replace tokens with in messages
   * @return Returns the message for the key
   * @throws IllegalArgumentException If a message is not found for the provided key
   */
  public static String gm(String key, String... args) {
    if (messages.size() == 0) {
      initialize();
    }

    if (!messages.containsKey(key)) {
      throw new IllegalArgumentException("Message for " + key + " not found");
    }

    return messages.get(key).replace(args);
  }

  private static Map<String, Object> getDefaults() {
    Map<String, Object> messages = new HashMap<>();

    // General messages
    messages.put("permission-error", "&cYou do not have permission to do that!");
    messages.put("reload-success", "&aMobActions reload complete!");
    messages.put("command-action", "action");
    messages.put("command-cancel", "cancel");
    messages.put("command-create", "create");
    messages.put("command-command", "command");
    messages.put("command-consolecmd", "consolecmd");
    messages.put("command-event", "event");
    messages.put("command-events", "events");
    messages.put("command-forcestart", "forcestart");
    messages.put("command-help", "help");
    messages.put("command-info", "info");
    messages.put("command-open", "open");
    messages.put("command-reload", "reload");
    messages.put("command-remove", "remove");
    messages.put("command-set", "set");
    messages.put("command-warp", "warp");
    messages.put("command-warps", "warps");

    // Command messages
    messages.put("create-command", "&bClick on a mob to set the action!");
    messages.put("edit-cancel", "&eUse \"/mac action cancel\" to cancel");
    messages.put("edit-cancel-success", "&eCancelled!");
    messages.put("remove-command", "&bClick on a mob to remove it");
    messages.put("edit-cancel-error", "&eNothing to cancel!");

    // Event message
    messages.put("event-countdown-text", "&eStarting {&c} event in {&c} seconds.");
    messages.put("event-joined-text", "&aSuccessfully joined the {&c} event!");
    messages.put("event-left-text", "&eLeft the {&c} event!");
    messages.put("event-create-text", "&aCreated {&c} event!");
    messages.put("event-remove-text", "&aRemoved {&c} event!");
    messages.put("event-open-text", "&aOpened {&c} event!");
    messages.put("event-cancel-text", "&aCancelled {&c} event!");
    messages.put("event-cancelled-text", "&eThe {&c} event has been cancelled!");
    messages.put("event-forcestart-text", "&aStarted {&c} event!");
    messages.put("event-leave-info", "&eClick again to leave the event!");
    messages.put("event-missing-error", "&cThe {} event doesn't exist!");
    messages.put("event-exists-error", "&cThe {} event already exists!");
    messages.put("event-already-open-error", "&cThe {} event is already open!");
    messages.put("event-closed-error", "&cThe {} event isn't open!");
    messages.put("event-save-error", "&cCouldn't save event \"{}\"!");
    messages.put("event-timeout-error", "&cTimeout cannot be less than 1!");
    messages.put("event-players-error", "&cMax players cannot be less than 0!");
    messages.put("event-info-text", "&6&l{} info:");
    messages.put("event-status-text", "&6Status: {}");
    messages.put("event-status-open", "&aOpen");
    messages.put("event-status-closed", "&cClosed");
    messages.put("event-status-starting", "&eStarting");
    messages.put("event-timeout-text", "&6Wait time: {&c} seconds");
    messages.put("event-players-text", "&6Players: {&c}");
    messages.put("event-type-text", "&6Type: {&c}");
    messages.put("event-type-command", "Command");
    messages.put("event-type-consolecmd", "Console command");
    messages.put("event-type-warp", "Warp");
    messages.put("event-type-details-text", "&6{}: {&c}");
    messages.put("event-list-message", "&aAvailable events:");
    messages.put("event-list-empty-message", "&eNo events available!");

    // Help messages
    messages.put("help-usage", "&eUsage: /mac <subcommand>");
    messages.put("help-command-action", "&e/mac action create command \"command[;command 2]\""
        + " \"name tag text\" - Create a new command mob");
    messages.put("help-consolecmd-action", "&e/mac action create consolecmd \"command[;command 2]\""
        + " \"name tag text\" - Create a new console command mob");
    messages.put("help-event-action", "&e/mac action create event <event> - Create a new event mob"
        + " action");
    messages.put("help-warp-action", "&e/mac action create warp <warp> - Create a new warp mob"
        + " action");
    messages.put("help-action-remove", "&e/mac action remove - Remove a mob action");
    messages.put("help-action-cancel", "&e/mac action cancel - Cancels the current action"
        + " operation");
    messages.put("help-command-event", "&e/mac events create <event name> <wait seconds> [max"
        + " players] command \"command[;command 2]\" - Create a command event with an optional"
        + " player limit");
    messages.put("help-consolecmd-event", "&e/mac events create <event name> <wait seconds> [max"
        + " players] consolecmd \"command[;command 2]\" - Create a consolecmd event with an"
        + " optional player limit");
    messages.put("help-warp-event", "&e/mac events create <event name> <wait seconds> [max players]"
        + " warp <warp name> - Create a warp event with an optional player limit");
    messages.put("help-event-open", "&e/mac events open <name> - Opens event, starts event timer");
    messages.put("help-event-cancel", "&e/mac events cancel <name> - Cancel an event");
    messages.put("help-event-remove", "&e/mac events remove <name> - Remove an event");
    messages.put("help-event-forcestart", "&e/mac events forcestart <name> - Forces an event to"
        + " start now");
    messages.put("help-event-info", "&e/mac events info <name> - Show information about the event");
    messages.put("help-event-list", "&e/mac events - List all events");
    messages.put("help-warp", "&e/mac warp <warp> - Teleport to a warp");
    messages.put("help-warps-list", "&e/mac warps - List available warps");
    messages.put("help-warps-set", "&e/mac warps set <name> - Create a warp");
    messages.put("help-warps-remove", "&e/mac warps remove <name> - Delete a warp");
    messages.put("help-reload", "&e/mac reload - Reloads the plugin's configuration");
    messages.put("help-page-number", "&6Help page {} / {}:");
    messages.put("help-command-usage", "&6/mac help [page] - Shows the specified help page");
    messages.put("help-command-error", "&cThat page does not exist!");

    // Mob messages
    messages.put("nametag-command-text", "&6Click to {}!");
    messages.put("action-create-success", "&aMobAction created successfully!");
    messages.put("action-remove-success", "&aMobAction successfully removed!");
    messages.put("nametag-event-text", "&6Click to join the {&c} event!");
    messages.put("nametag-portal-text", "&6Click to warp to {&c}!");
    messages.put("mob-exists-error", "&cA MobAction already exists here! Delete it to recreate.");
    messages.put("remove-error", "&cNo mob action found!");
    messages.put("command-error", "&cThere was a problem running this MobAction command!");
    messages.put("mob-corrupt-error", "&cA MobAction mob exists here, but it's data became corrupt"
        + " (possibly due to another plugin). Please destroy this mob. See console for details.");

    // Warp messages
    messages.put("warp-create-error", "&cCouldn't save warp!");
    messages.put("warp-create-success", "&aWarp {&c} created successfully!");
    messages.put("warp-delete-success", "&aWarp {&c} deleted successfully!");
    messages.put("warp-missing", "&cWarp \"{}\" not found!");
    messages.put("warp-already-exists", "&cWarp \"{}\" already exists! Please remove it to recreate"
        + " it.");
    messages.put("warp-save-error", "&cCouldn't save warp \"{}\"!");
    messages.put("warp-success", "&6Welcome to {&c}!");
    messages.put("list-message", "&aAvailable warps:");
    messages.put("list-empty-message", "&eNo warps available!");

    return messages;
  }
}
