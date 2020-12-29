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

    // General messages/errors
    messages.put("permission-error",
        new Message("&cYou do not have permission to do that!"));

    // Command messages
    messages.put("create-command", new Message("&bRight click on a mob to set the action!"));
    messages.put("edit-cancel", new Message("&eUse /mac cancel to cancel"));
    messages.put("edit-cancel-success", new Message("&eCancelled!"));
    messages.put("remove-command", new Message("&bRight click on a mob to remove it"));

    // Command errors
    messages.put("edit-cancel-error", new Message("&eNothing to cancel!"));
    messages.put("reload-success", new Message("&aMobActions reload complete!"));

    // Event message
    messages.put("countdown-text", new Message("&eJoining event in {&c} seconds."));

    // Mob messages
    messages.put("action-create-success", new Message("&aMobAction created successfully!"));
    messages.put("action-remove-success", new Message("&aMobAction successfully removed!"));
    messages.put("nametag-command-text", new Message("&6{}!"));
    messages.put("nametag-event-text", new Message("&6Click to join the {&c} event!"));
    messages.put("nametag-portal-text", new Message("&6Click to warp to {&c}!"));

    // Mob errors
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
