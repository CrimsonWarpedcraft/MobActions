package com.snowypeaksystems.mobportals.warps;

import java.io.IOException;
import java.util.Set;
import org.bukkit.Location;

/**
 * Collection of IWarp objects that have been made available from storage.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IWarps {
  /**
   * Creates an IWarp.
   * @param name name of the warp
   * @param location location in which the warp takes you
   * @return Returns the IWarp created
   * @throws IOException if the warp cannot be written to storage
   */
  IWarp create(String name, Location location) throws IOException;

  /** Add the IWarp to the available warps. */
  IWarp add(IWarp warp);

  /** Returns the IWarp for the given name if present, or null otherwise. */
  IWarp get(String name);

  /** Removes the mapping for the specified name from this map if present. */
  IWarp remove(String name);

  /** Returns true if a warp with "name" exists, and false otherwise. */
  boolean exists(String name);

  /** Returns a set of loaded warp names. */
  Set<String> getWarpNames();

  /** Returns the number of IWarps loaded. */
  int size();

  /** Reloads the warp list from storage. */
  void reload();
}
