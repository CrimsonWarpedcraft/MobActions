package com.snowypeaksystems.mobactions.mob.data.warp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * A map implementation used for warps that persists the keys upon write and remove.
 * This is particularly useful for making sure we keep track of warp locations even after restart.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WarpManager implements IWarpManager {
  private final File storageDir;
  private HashMap<String, IWarp> warps;

  /**
   * Construct a new WarpManager map from the specified dataDir and server.
   * @param dataDir folder for the map to write to
   */
  public WarpManager(File dataDir) throws FileNotFoundException {
    this.storageDir = dataDir;

    if (!storageDir.exists()) {
      throw new FileNotFoundException("Warp directory not found");
    }

    reload();
  }

  @Override
  public IWarp makeWarp(String name, Location destination) {
    return null;
  }

  @Override
  public IWarp getWarp(String name) {
    return null;
  }

  @Override
  public IWarp unregister(String name) {
    return null;
  }

  @Override
  public boolean exists(String name) {
    return warps.containsKey(name.toLowerCase());
  }

  @Override
  public Set<String> getLoadedWarpNames() {
    Set<String> names = new HashSet<>();

    for (IWarp warp : warps.values()) {
      names.add(warp.getAlias());
    }

    return names;
  }

  @Override
  public void reload() {
    warps = new HashMap<>();
    File[] files = storageDir.listFiles();

    // TODO: Maybe async this?
    if (files != null) {
      for (File f : files) {
        if (f.getName().startsWith(".")) {
          continue;
        }

        try {
          IWarp warp = new Warp(f);
          warps.put(warp.getAlias().toLowerCase(), warp);
        } catch (WarpConfigException e) {
          Bukkit.getLogger().log(Level.FINE, e.getMessage(), e);
        }
      }
    }
  }
}