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
    messages.put("create-command", new Message("&bRight click on a mob to set the action!"));
    messages.put("edit-cancel", new Message("&eUse /mac cancel to cancel"));
    messages.put("edit-cancel-success", new Message("&eCancelled!"));
    messages.put("remove-command", new Message("&bRight click on a mob to remove it"));
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
    messages.put("event-info-text", new Message("&l&6{} Info"));
    messages.put("event-status-text", new Message("&l&6Status: {&c}"));
    messages.put("event-timeout-text", new Message("&l&6Wait time: {&c}"));
    messages.put("event-players-text", new Message("&l&6Players: {&c}"));
    messages.put("event-type-text", new Message("&l&6Type: {&c}"));
    messages.put("event-type-details-text", new Message("&l&6{}: {&c}"));

    // Mob messages
    messages.put("nametag-command-text", new Message("&6{}!"));
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
