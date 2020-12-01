package com.snowypeaksystems.mobactions.warp;

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
    this.warps = new HashMap<>();

    if (!storageDir.exists()) {
      throw new FileNotFoundException("Warp directory not found");
    }

    reload();
  }

  @Override
  public void makeWarp(String name, Location destination) {
    // TODO Mason
    /*
    1. create warp object with lowercase name, dest, and storageDir
    2. add warp to warps hashmap with lowercase name as key
     */
  }

  @Override
  public IWarp getWarp(String name) {
    // TODO Mason
    // Return warp in warps hashmap with lowercase name
    return null;
  }

  @Override
  public IWarp unregister(String name) {
    // TODO Mason
    // remove and return the warp with lowercase name or null
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