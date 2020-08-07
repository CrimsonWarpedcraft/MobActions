package com.snowypeaksystems.mobportals;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Class to store messages used throughout the plugin.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Messages {
  public String cancelError;
  public String cancelInfo;
  public String cancelSuccess;
  public String consoleCommandError;
  public String mobNameText;
  public String portalCreateError;
  public String portalCreateInfo;
  public String portalCreateSuccess;
  public String portalRemoveError;
  public String portalRemoveInfo;
  public String portalRemoveSuccess;
  public String permissionError;
  public String reloadComplete;
  public String warpCreateError;
  public String warpCreateSuccess;
  public String warpDeleteError;
  public String warpDeleteSuccess;
  public String warpNotFound;
  public String warpSuccess;
  public final String permToken = "%perm%";
  public final String warpToken = "%warp%";
  private static Messages singleton;

  /** Get the message object based on the specified config. */
  public static Messages getMessages(FileConfiguration config) {
    if (singleton == null) {
      singleton = new Messages(config);
    }

    return singleton;
  }

  private Messages(FileConfiguration config) {
    generateMessages(config);
  }

  public void regenerateMessages(FileConfiguration config) {
    generateMessages(config);
  }

  private void generateMessages(FileConfiguration config) {
    final String warpFormat =
        ChatColor.translateAlternateColorCodes('&',
            config.getString("warp-name-formatting", ""));

    consoleCommandError = ChatColor.RED + "You cannot execute commands from the console!";

    cancelInfo = ChatColor.YELLOW + "Use /mp cancel to cancel";

    cancelSuccess = ChatColor.YELLOW + "Cancelled!";

    cancelError = ChatColor.YELLOW + "Nothing to cancel!";

    portalCreateInfo = setTokenFormat(
        ChatColor.AQUA + "Right click on a mob to create a portal to %warp%",
        warpToken, warpFormat);

    portalCreateSuccess = setTokenFormat(
        ChatColor.GREEN + "Portal to %warp% created successfully!", warpToken, warpFormat);

    portalCreateError = ChatColor.RED + "Portals cannot be set to players!";

    portalRemoveInfo = setTokenFormat(
        ChatColor.AQUA + "Right click on a mob to remove the portal",
        warpToken, warpFormat);

    portalRemoveSuccess = setTokenFormat(
        ChatColor.GREEN + "Portal to %warp% successfully removed!", warpToken, warpFormat);

    portalRemoveError = ChatColor.RED + "No portal found!";

    permissionError = ChatColor.RED + "You need the %perm% permission node to do that!";

    reloadComplete = ChatColor.GREEN + "MobPortals reload complete!";

    warpSuccess = setTokenFormat(ChatColor.translateAlternateColorCodes('&',
        config.getString("warp-message", "")), warpToken, warpFormat);

    warpNotFound = setTokenFormat(ChatColor.RED + "Warp %warp% not found!",
        warpToken, warpFormat);

    warpCreateError = setTokenFormat(
        ChatColor.RED + "Warp %warp% already exists!", warpToken, warpFormat);

    warpCreateSuccess = setTokenFormat(
        ChatColor.GREEN + "Warp %warp% created successfully!", warpToken, warpFormat);

    warpDeleteSuccess = setTokenFormat(
        ChatColor.GREEN + "Warp %warp% deleted successfully!", warpToken, warpFormat);

    mobNameText = setTokenFormat(ChatColor.translateAlternateColorCodes('&',
        config.getString("mob-nametag-text", "")), warpToken, warpFormat);
  }

  private String setTokenFormat(String message, String token, String format) {
    //TODO: Locate token and find last colors before it rather than the end of the message
    String lastFormat = ChatColor.getLastColors(message);

    String wrappedToken = ChatColor.RESET + format + token
        + ChatColor.RESET + lastFormat;

    return message.replaceAll(token, wrappedToken);
  }
}
