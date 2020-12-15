package com.snowypeaksystems.mobactions.util;

import java.util.HashMap;

/**
 * Collection of messages to be used throughout the project.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public final class Messages {
  private static final HashMap<String, Message> messages = new HashMap<>();

  /** Loads message data. */
  public static void initialize() {
    // TODO: Load other language translations if available in data folder and store in 2D map
    messages.clear();

    // Command messages
    messages.put("create-command",
        new Message("&bRight click on a mob to bind the command!"));
    messages.put("create-portal",
        new Message("&bRight click on a mob to create a warp to {&c}"));

    // Mob messages
    messages.put("action-create-success",
        new Message("&aMobAction created successfully!"));
    messages.put("action-remove-success",
        new Message("&MobAction removed"));

    // Console messages
    messages.put("console-command-error",
        new Message("&cYou cannot execute commands from the console!"));

    // Cancel messages
    messages.put("edit-cancel", new Message("&eUse /mac cancel to cancel"));
    messages.put("edit-cancel-error", new Message("&eNothing to cancel!"));
    messages.put("edit-cancel-success", new Message("&eCancelled!"));

    // Remove messages
    messages.put("edit-remove", new Message("&bRight click on a mob to remove it"));
    messages.put("remove-error", new Message("&cNo active mob found!"));
    messages.put("edit-remove-success", new Message("&aMob successfully removed!"));

    // List messages
    messages.put("list-message", new Message("&aAvailable warps:"));
    messages.put("list-empty-message", new Message("&eNo warps available!"));

    // Mob exists message
    messages.put("mob-exists-error",
        new Message("&cA MobAction already exists here! Delete it to recreate."));

    // Nametag messages
    messages.put("nametag-command-text", new Message("&6Click to {&c}!"));
    messages.put("nametag-portal-text", new Message("&6Click to warp to {&c}!"));

    // Permission messages
    messages.put("permission-error",
        new Message("&cYou do not have permission to do that!"));

    // Reload messages
    messages.put("reload-success", new Message("&aMobActions reload complete!"));

    // Warp messages
    messages.put("warp-create-error",
        new Message("&cCouldn't save warp! See console!"));
    messages.put("warp-create-success",
        new Message("&aWarp {&c} created successfully!"));
    messages.put("warp-delete-success",
        new Message("&aWarp {&c} deleted successfully!"));
    messages.put("warp-missing", new Message("&cWarp \"{}\" not found!"));
    messages.put("warp-already-exists",
        new Message("&cWarp \"{}\" already exists! Please remove it to recreate it."));
    messages.put("warp-save-error", new Message("&cCouldn't save warp \"{}\"!"));
    messages.put("warp-success", new Message("&6Welcome to {&c}!"));
  }

  /**
   * Get the message for the given string, replacing instances of the token with args.
   * @param key the identifier of the message
   * @param args list of strings to replace tokens with in messages
   * @return Returns the message for the key
   * @throws RuntimeException If a message is not found for the provided key
   */
  public static String gm(String key, String... args) {
    if (messages.size() == 0) {
      initialize();
    }

    if (!messages.containsKey(key)) {
      throw new RuntimeException("Message for " + key + " not found");
    }

    return messages.get(key).replace(args);
  }

  /** Returns true if the message key is present, false otherwise. */
  public static boolean contains(String key) {
    return messages.containsKey(key);
  }

  static void put(String key, String value) {
    messages.put(key, new Message(value));
  }
}
