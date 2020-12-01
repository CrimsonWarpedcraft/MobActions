package com.snowypeaksystems.mobactions.warp;

import java.util.Set;
import org.bukkit.Location;

/**
 * Collection of IWarp objects that have been made available from storage.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IWarpManager {
  /** Create an IWarp with the provided name and destination. */
  void makeWarp(String name, Location destination);

  /** Returns the IWarp with the given name if present, or null otherwise. */
  IWarp getWarp(String name);

  /** Removes and returns the IWarp with the provided name if present, or null otherwise. */
  IWarp unregister(String name);

  /** Returns true if a warp with the given name exists, and false otherwise. */
  boolean exists(String name);

  /** Returns a set of loaded warp names. */
  Set<String> getLoadedWarpNames();

  /** Reloads the warp list from storage. */
  void reload();
}
