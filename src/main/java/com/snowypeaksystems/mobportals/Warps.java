package com.snowypeaksystems.mobportals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import javax.annotation.Nonnull;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * A map implementation used for warps that persists the keys upon write and remove.
 * This is particularly useful for making sure we keep track of warp locations even after restart.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Warps extends HashMap<String, Location> {
  private final File storageDir;
  private final Server server;

  /**
   * Construct a new Warps map from the specified dataDir and server.
   * @param dataDir folder for the map to write to
   * @param server server instance to pull world, logger, and other data from
   */
  public Warps(File dataDir, Server server) {
    super();

    this.server = server;
    this.storageDir = dataDir;

    if (!storageDir.exists()) {
      storageDir.mkdirs();
    } else {
      loadAll();
    }
  }

  /**
   * Associates the specified location with the specified name in this map.
   * @param name name of the warp
   * @param location location in which the warp takes you
   * @return last location the warp was set to if any
   */
  @Override
  public Location put(@Nonnull String name, @Nonnull Location location) {
    Location last = super.put(name, location);

    writeWarp(name, location);

    return last;
  }

  /**
   * Removes the mapping for the specified name from this map if present.
   * @param name name of the warp to remove
   * @return last location the warp was set to
   */
  @Override
  public Location remove(Object name) {
    Location last = super.remove(name);

    deleteWarp((String) name);

    return last;
  }

  /** Loads all warps from the warp directory. */
  private void loadAll() {
    File[] files = storageDir.listFiles();

    for (File f : files) {
      // TODO: Maybe async this?
      YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

      String name = config.getString("name");

      super.put(name, yamlToLocation(config));
    }
  }

  private void writeWarp(String name, Location location) {
    YamlConfiguration config = new YamlConfiguration();
    config.set("name", name);
    locationToYaml(location, config);

    // TODO: Definitely async this
    try {
      config.save(new File(storageDir, name));
    } catch (IOException e) {
      server.getLogger().log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private void deleteWarp(String name) {
    File f = new File(storageDir, name);

    if (f.exists()) { // Should we async this? exists and delete should be O(1)
      f.delete();
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

  private Location yamlToLocation(YamlConfiguration config) {
    World world = server.getWorld(config.getString("world"));
    double x = config.getDouble("x");
    double y = config.getDouble("y");
    double z = config.getDouble("z");
    float yaw = (float) config.getDouble("yaw");
    float pitch = (float) config.getDouble("pitch");

    return new Location(world, x, y, z, yaw, pitch);
  }
}