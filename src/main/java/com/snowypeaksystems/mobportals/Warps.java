package com.snowypeaksystems.mobportals;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * A map implementation used for warps that persists the keys upon write and remove.
 * This is particularly useful for making sure we keep track of warp locations even after restart.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Warps {
  private final File storageDir;
  private final Server server;
  private final HashMap<String, Location> warps;

  /**
   * Construct a new Warps map from the specified dataDir and server.
   * @param dataDir folder for the map to write to
   * @param server server instance to pull world, logger, and other data from
   */
  public Warps(File dataDir, Server server) throws IOException {
    this.server = server;
    this.storageDir = dataDir;
    this.warps = new HashMap<>();

    if (storageDir.exists()) {
      loadAll();
    } else {
      if (!storageDir.mkdirs()) {
        throw new IOException("Failed to create warps directory");
      }
    }
  }

  /**
   * Associates the specified location with the specified name in this map.
   * @param name name of the warp
   * @param location location in which the warp takes you
   */
  public void create(String name, Location location) throws IOException {
    writeWarp(name, location);

    warps.put(name, location);
  }

  /**
   * Removes the mapping for the specified name from this map if present.
   * @param name name of the warp to remove
   */
  public void remove(Object name) {
    deleteWarp((String) name);

    warps.remove(name);
  }

  /** Returns the location for the given warp. */
  public Location get(String key) {
    return warps.get(key);
  }

  /** Returns true if the given warp exists, and false otherwise. */
  public boolean exists(String key) {
    return warps.containsKey(key);
  }

  /** Loads all warps from the warp directory. */
  private void loadAll() throws IOException {
    File[] files = storageDir.listFiles();

    if (files == null) {
      throw new IOException("Storage directory does not exist!");
    }

    for (File f : files) {
      // TODO: Maybe async this?
      YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

      String name = config.getString("name");

      convertOldName(f, name);

      warps.put(name, yamlToLocation(config));
    }
  }

  private void writeWarp(String name, Location location) throws IOException {
    YamlConfiguration config = new YamlConfiguration();
    config.set("name", name);
    locationToYaml(location, config);

    // TODO: Definitely async this
    config.save(new File(storageDir, String.valueOf(name.hashCode())));
  }

  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_BAD_PRACTICE")
  private void deleteWarp(String name) {
    File f = new File(storageDir, String.valueOf(name.hashCode()));

    if (f.exists()) { // Should we async this? exists and delete should be O(1)
      f.delete(); // Ignore return since if we can't access it, it's good as gone
    }
  }

  private void locationToYaml(Location loc, YamlConfiguration config) {
    config.set("world", loc.getWorld().getName());
    config.set("x", loc.getX());
    config.set("y", loc.getY());
    config.set("z", loc.getZ());
    config.set("yaw", loc.getYaw());
    config.set("pitch", loc.getPitch());
  }

  private Location yamlToLocation(YamlConfiguration config) throws IOException {
    String worldName = config.getString("world");
    if (worldName == null) {
      throw new IOException("Missing world name in warp config!");
    }
    World world = server.getWorld(worldName);
    double x = config.getDouble("x");
    double y = config.getDouble("y");
    double z = config.getDouble("z");
    float yaw = (float) config.getDouble("yaw");
    float pitch = (float) config.getDouble("pitch");

    return new Location(world, x, y, z, yaw, pitch);
  }

  private void convertOldName(File file, String name) throws IOException {
    if (!NumberUtils.isCreatable(file.getName())) {
      File newName = new File(file.getParentFile(), String.valueOf(name.hashCode()));
      if (!file.renameTo(newName)) {
        throw new IOException("Could not convert old save!");
      }
    }
  }
}