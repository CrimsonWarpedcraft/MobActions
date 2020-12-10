package com.snowypeaksystems.mobactions.player;

import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.warp.IWarp;
import org.bukkit.Location;

/**
 * An object that represents a player interacting with the plugin.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface MobActionsUser {
  /** Returns the status of the user. */
  IStatus getStatus();

  /** Teleports the user to the location. */
  void teleport(Location location);

  /** Sends a message to the user. */
  void sendMessage(String... messages);

  /** Performs a command as the and returns true on success, false otherwise.*/
  boolean performCommand(String command);

  /** Returns the display name for this user. */
  String getDisplayName();

  boolean canUseWarp(IWarp warp);

  boolean canRunCommand(ICommandData command);

  boolean canCreate();

  boolean canRemove();

  boolean canUseWarpCommand();

  boolean canSetWarp();

  boolean canRemoveWarp();

  boolean canReload();
}
