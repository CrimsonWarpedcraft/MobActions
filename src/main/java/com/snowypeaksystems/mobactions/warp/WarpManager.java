package com.snowypeaksystems.mobactions.warp;

import java.io.IOException;
import java.util.Set;
import org.bukkit.Location;

/**
 * Collection of Warp objects that have been made available from storage.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface WarpManager {
  /** Create an Warp with the provided name and destination. */
  Warp makeWarp(String name, Location destination) throws IOException;

  /** Returns the Warp with the given name if present, or null otherwise. */
  Warp getWarp(String name);

  /** Removes and returns the Warp with the provided name if present, or null otherwise. */
  void unregister(String name);

  /** Returns true if a warp with the given name exists, and false otherwise. */
  boolean exists(String name);

  /** Returns a set of loaded warp names. */
  Set<String> getLoadedWarpNames();

  /** Returns a set of loaded warps. */
  Set<Warp> getLoadedWarps();

  /** Reloads the warp list from storage. */
  void reload();
}
