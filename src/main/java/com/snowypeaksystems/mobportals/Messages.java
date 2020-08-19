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
  public String reloadError;
  public String warpExistsError;
  public String warpWriteError;
  public String warpCreateSuccess;
  public String warpDeleteSuccess;
  public String warpNotFound;
  public String warpSuccess;
  public String listMessage;
  public String noWarpsFound;
  public static final String permToken = "%perm%";
  public static final String warpToken = "%warp%";
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
    String warpName = config.getString("warp-name-formatting", "");
    if (warpName == null) {
      warpName = "";
    }
    final String warpFormat = ChatColor.translateAlternateColorCodes('&', warpName);

    consoleCommandError = ChatColor.RED + "You cannot execute commands from the console!";

    cancelInfo = ChatColor.YELLOW + "Use /mp cancel to cancel";

    cancelSuccess = ChatColor.YELLOW + "Cancelled!";

    cancelError = ChatColor.YELLOW + "Nothing to cancel!";

    portalCreateInfo = setTokenFormat(
        ChatColor.AQUA + "Right click on a mob to create a portal to %warp%",
        warpFormat);

    portalCreateSuccess = setTokenFormat(
        ChatColor.GREEN + "Portal to %warp% created successfully!", warpFormat);

    portalCreateError = ChatColor.RED + "Portals cannot be set to players!";

    portalRemoveInfo = setTokenFormat(
        ChatColor.AQUA + "Right click on a mob to remove the portal",
        warpFormat);

    portalRemoveSuccess = ChatColor.GREEN + "Portal successfully removed!";

    portalRemoveError = ChatColor.RED + "No portal found!";

    permissionError = ChatColor.RED + "You need the %perm% permission node to do that!";

    reloadComplete = ChatColor.GREEN + "MobPortals reload complete!";

    reloadError = ChatColor.RED + "Unable to reload MobPortals!";

    String warpMessage = config.getString("warp-message", "");
    if (warpMessage == null) {
      warpMessage = "";
    }
    warpSuccess = setTokenFormat(
        ChatColor.translateAlternateColorCodes('&', warpMessage), warpFormat);

    warpNotFound = setTokenFormat(ChatColor.RED + "Warp %warp% not found!",
        warpFormat);

    warpExistsError = setTokenFormat(
        ChatColor.RED + "Warp %warp% already exists!", warpFormat);

    warpWriteError = setTokenFormat(
        ChatColor.RED + "Couldn't save warp %warp%!", warpFormat);

    warpCreateSuccess = setTokenFormat(
        ChatColor.GREEN + "Warp %warp% created successfully!", warpFormat);

    warpDeleteSuccess = setTokenFormat(
        ChatColor.GREEN + "Warp %warp% deleted successfully!", warpFormat);

    listMessage = ChatColor.GREEN + "Available warps:";

    noWarpsFound = ChatColor.YELLOW + "No warps available!";

    String nametagText = config.getString("mob-nametag-text", "");
    if (nametagText == null) {
      nametagText = "";
    }
    mobNameText = setTokenFormat(
        ChatColor.translateAlternateColorCodes('&', nametagText), warpFormat);
  }

  private String setTokenFormat(String message, String format) {
    //TODO: Locate token and find last colors before it rather than the end of the message
    String lastFormat = ChatColor.getLastColors(message);

    String wrappedToken = ChatColor.RESET + format + Messages.warpToken
        + ChatColor.RESET + lastFormat;

    return message.replaceAll(Messages.warpToken, wrappedToken);
  }
}
