package com.snowypeaksystems.mobactions.player;

import java.util.concurrent.CompletableFuture;
import org.bukkit.Location;

/**
 * An object that represents a player interacting with the plugin.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface MobActionsUser {
  /** Returns the status of the user. */
  IStatus getStatus();

  /** Teleports the user to the location. Returns a CompletableFuture for async behavior. */
  CompletableFuture<Boolean> teleport(Location location);

  /** Sends a message to the user. */
  void sendMessage(String... messages);

  /** Performs a command as the and returns true on success, false otherwise.*/
  boolean performCommand(String command);

  /** Returns the location of the user. */
  Location getLocation();

  /** Returns the display name for this user. */
  String getName();

  boolean isOnline();

  boolean canUseWarp(String warp);

  boolean canRunCommand();

  boolean canJoinEvent(String event);

  boolean canStartEvents();

  boolean canCancelEvents();

  boolean canCreate();

  boolean canRemove();

  boolean canUseWarpCommand();

  boolean canUseWarpsCommand();

  boolean canSetWarp();

  boolean canRemoveWarp();

  boolean canReload();
}
