package com.snowypeaksystems.mobportals;

import com.snowypeaksystems.mobportals.exceptions.WarpConfigException;
import com.snowypeaksystems.mobportals.exceptions.WorldNotFoundException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * A map implementation used for warps that persists the keys upon write and remove.
 * This is particularly useful for making sure we keep track of warp locations even after restart.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Warps {
  private final File storageDir;
  private final Plugin plugin;
  private final Server server;
  private final HashMap<String, Location> warps;

  /**
   * Construct a new Warps map from the specified dataDir and server.
   * @param dataDir folder for the map to write to
   * @param server server instance to pull world, logger, and other data from
   * @param plugin the plugin creating the constructor
   */
  public Warps(File dataDir, Server server, Plugin plugin) throws IOException {
    this.plugin = plugin;
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

  /** Returns a set of warp names. */
  public Set<String> getWarpNames() {
    return warps.keySet();
  }

  /** Loads all warps from the warp directory. */
  private void loadAll() throws IOException {
    File[] files = storageDir.listFiles();
    HashSet<YamlConfiguration> retryList = new HashSet<>();

    if (files == null) {
      throw new IOException("Storage directory does not exist!");
    }

    for (File f : files) {
      // TODO: Maybe async this?
      YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

      String name = config.getString("name");

      try {
        convertOldName(f, name);
        warps.put(name, yamlToLocation(config));
      } catch (WarpConfigException | IOException e) {
        server.getLogger().severe(e.getMessage());
        e.printStackTrace();
      } catch (WorldNotFoundException e) {
        retryList.add(config);
      }
    }

    if (retryList.size() > 0) {
      BukkitRunnable retry = new BukkitRunnable() {
        @Override
        public void run() {
          for (YamlConfiguration config : retryList) {
            String name = config.getString("name");

            try {
              warps.put(name, yamlToLocation(config));
            } catch (Exception e) {
              server.getLogger().severe(e.getMessage());
              e.printStackTrace();
            }
          }
        }
      };

      retry.runTask(plugin); // Try again after other plugins are finished loading
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

  private Location yamlToLocation(YamlConfiguration config) {
    String worldName = config.getString("world");
    if (worldName == null) {
      throw new WarpConfigException("Missing world name in warp config!");
    }

    World world = server.getWorld(worldName);
    if (world == null) {
      throw new WorldNotFoundException(worldName);
    }

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