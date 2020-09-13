package com.snowypeaksystems.mobportals.messages;

import java.util.HashMap;
import java.util.Map;

/**
 * Collection of messages to be used throughout the project.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Messages {
  private static HashMap<String, IMessage> messages;

  /** Loads message data. */
  public static void initialize() {
    // TODO: Load other language translations if available in data folder and store in 2D map
    messages = new HashMap<>();

    // Command messages
    messages.put("command-create",
        new Message("&bRight click on a mob to bind the command {&c}"));
    messages.put("command-create-success",
        new Message("&aCommand {&c} bound successfully!"));
    messages.put("command-remove-success",
        new Message("&aCommand {&c} successfully removed!"));

    // Console messages
    messages.put("console-command-error",
        new Message("&cYou cannot execute commands from the console!"));

    // Cancel messages
    messages.put("edit-cancel", new Message("&eUse /mp cancel to cancel"));
    messages.put("edit-cancel-error", new Message("&eNothing to cancel!"));
    messages.put("edit-cancel-success", new Message("&eCancelled!"));

    // Remove messages
    messages.put("edit-remove", new Message("&bRight click on a mob to disable it"));
    messages.put("edit-remove-error", new Message("&cNo active mob found!"));

    // List messages
    messages.put("list-message", new Message("&aAvailable warps:"));
    messages.put("list-empty-message", new Message("&eNo warps available!"));

    // Mob nametag message
    messages.put("nametag-command-text", new Message("&6Click to {&c}!"));
    messages.put("nametag-portal-text", new Message("&6Click to warp to {&c}!"));

    // Permission messages
    messages.put("permission-error",
        new Message("&cYou need the {} permission node to do that!"));

    // Portal messages
    messages.put("portal-create",
        new Message("&bRight click on a mob to create a portal to {&c}"));
    messages.put("portal-create-success",
        new Message("&aPortal to {&c} created successfully!"));
    messages.put("portal-remove-success",
        new Message("&aPortal to {&c} successfully removed!"));

    // Reload messages
    messages.put("reload-success", new Message("&aMobPortals reload complete!"));

    // Warp messages
    messages.put("warp-create-error",
        new Message("&cCouldn't save warp! See console!"));
    messages.put("warp-create-success",
        new Message("&aWarp {&c} created successfully!"));
    messages.put("warp-delete-success",
        new Message("&aWarp {&c} deleted successfully!"));
    messages.put("warp-missing", new Message("&cWarp \"{}\" not found!"));
    messages.put("warp-save-error", new Message("&cCouldn't save warp \"{}\"!"));
    messages.put("warp-success", new Message("&6Welcome to {&c}!"));
  }

  /**
   * Get the message for the given string, replacing instances of IMessages.TOKEN with args.
   * @param key the identifier of the message
   * @param args list of strings to replace tokens with in messages
   * @return Returns the message for the key
   * @throws RuntimeException If a message is not found for the provided key
   */
  public static String gm(String key, String... args) {
    if (messages == null) {
      initialize();
    }

    if (!messages.containsKey(key)) {
      throw new RuntimeException("Message for " + key + " not found");
    }

    return messages.get(key).getString(args);
  }

  /** Returns the Map of messages kept by this class. */
  public static Map<String, IMessage> getMessages() {
    if (messages == null) {
      initialize();
    }

    return messages;
  }
}
