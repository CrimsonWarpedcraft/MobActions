package com.snowypeaksystems.mobportals.warps;

import com.snowypeaksystems.mobportals.exceptions.WarpConfigException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.Location;
import org.bukkit.Server;

/**
 * A map implementation used for warps that persists the keys upon write and remove.
 * This is particularly useful for making sure we keep track of warp locations even after restart.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Warps implements IWarps {
  private final File storageDir;
  private final Server server;
  private HashMap<String, IWarp> warps;

  /**
   * Construct a new Warps map from the specified dataDir and server.
   * @param dataDir folder for the map to write to
   * @param server server instance to pull world, logger, and other data from
   */
  public Warps(File dataDir, Server server) throws FileNotFoundException {
    this.server = server;
    this.storageDir = dataDir;

    if (!storageDir.exists()) {
      throw new FileNotFoundException("Warp directory not found");
    }

    reload();
  }

  @Override
  public IWarp create(String name, Location location) throws IOException {
    return new Warp(name, location, storageDir);
  }

  @Override
  public synchronized IWarp add(IWarp warp) {
    String nameLower = warp.getName().toLowerCase();
    IWarp old = warps.get(nameLower);

    warps.put(nameLower, warp);

    return old;
  }

  @Override
  public synchronized IWarp get(String name) {
    return warps.get(name.toLowerCase());
  }

  @Override
  public synchronized IWarp remove(String name) {
    return warps.remove(name.toLowerCase());
  }

  @Override
  public synchronized boolean exists(String key) {
    return warps.containsKey(key);
  }

  @Override
  public synchronized Set<String> getWarpNames() {
    return warps.keySet();
  }

  @Override
  public synchronized int size() {
    return warps.size();
  }

  @Override
  public synchronized void reload() {
    warps = new HashMap<>();
    File[] files = storageDir.listFiles();

    // TODO: Maybe async this?
    if (files != null) {
      for (File f : files) {
        try {
          IWarp warp = new Warp(f, server);
          warps.put(warp.getName().toLowerCase(), warp);
        } catch (WarpConfigException e) {
          server.getLogger().log(Level.FINE, e.getMessage(), e);
        }
      }
    }
  }
}