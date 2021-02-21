package com.snowypeaksystems.mobactions.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Collection of messages to be used throughout the project.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public final class Messages {
  private static final Map<String, Message> messages = new HashMap<>();

  /** Loads message data. */
  public static void initialize() {
    // TODO: Load other language translations if available in data folder and store in 2D map
    messages.clear();

    // General messages
    messages.put("permission-error",
        new Message("&cYou do not have permission to do that!"));
    messages.put("reload-success", new Message("&aMobActions reload complete!"));

    // Command messages
    messages.put("create-command", new Message("&bClick on a mob to set the action!"));
    messages.put("edit-cancel", new Message("&eUse \"/mac action cancel\" to cancel"));
    messages.put("edit-cancel-success", new Message("&eCancelled!"));
    messages.put("remove-command", new Message("&bClick on a mob to remove it"));
    messages.put("edit-cancel-error", new Message("&eNothing to cancel!"));

    // Event message
    messages.put("event-countdown-text", new Message("&eStarting {&c} event in {&c} seconds."));
    messages.put("event-joined-text", new Message("&aSuccessfully joined the {&c} event!"));
    messages.put("event-left-text", new Message("&eLeft the {&c} event!"));
    messages.put("event-create-text", new Message("&aCreated {&c} event!"));
    messages.put("event-remove-text", new Message("&aRemoved {&c} event!"));
    messages.put("event-open-text", new Message("&aOpened {&c} event!"));
    messages.put("event-cancel-text", new Message("&aCancelled {&c} event!"));
    messages.put("event-cancelled-text", new Message("&eThe {&c} event has been cancelled!"));
    messages.put("event-forcestart-text", new Message("&aStarted {&c} event!"));
    messages.put("event-leave-info", new Message("&eClick again to leave the event!"));
    messages.put("event-missing-error", new Message("&cThe {} event doesn't exist!"));
    messages.put("event-exists-error", new Message("&cThe {} event already exists!"));
    messages.put("event-already-open-error", new Message("&cThe {} event is already open!"));
    messages.put("event-closed-error", new Message("&cThe {} event isn't open!"));
    messages.put("event-save-error", new Message("&cCouldn't save event \"{}\"!"));
    messages.put("event-timeout-error", new Message("&cTimeout cannot be less than 1!"));
    messages.put("event-players-error", new Message("&cMax players cannot be less than 0!"));
    messages.put("event-info-text", new Message("&l&6{} Info"));
    messages.put("event-status-text", new Message("&l&6Status: {&c}"));
    messages.put("event-timeout-text", new Message("&l&6Wait time: {&c} seconds"));
    messages.put("event-players-text", new Message("&l&6Players: {&c}"));
    messages.put("event-type-text", new Message("&l&6Type: {&c}"));
    messages.put("event-type-details-text", new Message("&l&6{}: {&c}"));
    messages.put("event-list-message", new Message("&aAvailable events:"));
    messages.put("event-list-empty-message", new Message("&eNo events available!"));

    // Help messages
    messages.put("help-usage", new Message("&eUsage: /mac <subcommand>"));
    messages.put("help-command-action", new Message("&e/mac action create command \"command\""
        + " \"name tag text\" - Create a new command mob"));
    messages.put("help-event-action",
        new Message("&e/mac action create event <event> - Create a new event mob action"));
    messages.put("help-warp-action",
        new Message("&e/mac action create warp <warp> - Create a new warp mob action"));
    messages.put("help-action-remove", new Message("&e/mac action remove - Remove a mob action"));
    messages.put("help-action-cancel",
        new Message("&e/mac action cancel - Cancels the current action operation"));
    messages.put("help-command-event", new Message("&e/mac events create <event name>"
        + " <wait seconds> [max players] command \"command\" - Create a command event with an"
        + " optional player limit"));
    messages.put("help-warp-event", new Message("&e/mac events create <event name> <wait seconds>"
        + " [max players] warp <warp name> - Create a warp event with an optional player limit"));
    messages.put("help-event-open",
        new Message("&e/mac events open <name> - Opens event, starts event timer"));
    messages.put("help-event-cancel", new Message("&e/mac events cancel <name> - Cancel an event"));
    messages.put("help-event-remove", new Message("&e/mac events remove <name> - Remove an event"));
    messages.put("help-event-forcestart",
        new Message("&e/mac events forcestart <name> - Forces an event to start now"));
    messages.put("help-event-info",
        new Message("&e/mac events info <name> - Show information about the event"));
    messages.put("help-event-list", new Message("&e/mac events - List all events"));
    messages.put("help-warp", new Message("&e/mac warp <warp> - Teleport to a warp"));
    messages.put("help-warps-list", new Message("&e/mac warps - List available warps"));
    messages.put("help-warps-set", new Message("&e/mac warps set <name> - Create a warp"));
    messages.put("help-warps-remove", new Message("&e/mac warps remove <name> - Delete a warp"));
    messages.put("help-reload", new Message("&e/mac reload - Reloads the plugin's configuration"));
    messages.put("help-page-number", new Message("&6Help page {} / {}:"));
    messages.put("help-command-usage",
        new Message("&6/mac help [page] - Shows the specified help page"));
    messages.put("help-command-error", new Message("&cThat page does not exist!"));

    // Mob messages
    messages.put("nametag-command-text", new Message("&6Click to {}!"));
    messages.put("action-create-success", new Message("&aMobAction created successfully!"));
    messages.put("action-remove-success", new Message("&aMobAction successfully removed!"));
    messages.put("nametag-event-text", new Message("&6Click to join the {&c} event!"));
    messages.put("nametag-portal-text", new Message("&6Click to warp to {&c}!"));
    messages.put("mob-exists-error",
        new Message("&cA MobAction already exists here! Delete it to recreate."));
    messages.put("remove-error", new Message("&cNo active mob found!"));
    messages.put("command-error",
        new Message("&cThere was a problem running this MobAction command!"));
    messages.put("mob-corrupt-error",
        new Message("&cA MobAction mob exists here, but it's data became corrupt (possibly due to "
            + "another plugin). Please destroy this mob. See console for details."));

    // Warp messages
    messages.put("warp-create-error",
        new Message("&cCouldn't save warp!"));
    messages.put("warp-create-success",
        new Message("&aWarp {&c} created successfully!"));
    messages.put("warp-delete-success",
        new Message("&aWarp {&c} deleted successfully!"));
    messages.put("warp-missing", new Message("&cWarp \"{}\" not found!"));
    messages.put("warp-already-exists",
        new Message("&cWarp \"{}\" already exists! Please remove it to recreate it."));
    messages.put("warp-save-error", new Message("&cCouldn't save warp \"{}\"!"));
    messages.put("warp-success", new Message("&6Welcome to {&c}!"));
    messages.put("list-message", new Message("&aAvailable warps:"));
    messages.put("list-empty-message", new Message("&eNo warps available!"));
  }

  /**
   * Get the message for the given string, replacing instances of the token with args.
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
}
