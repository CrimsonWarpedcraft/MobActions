package com.snowypeaksystems.mobactions.warp;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Implementation of IWarp stored to File.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Warp implements IWarp {
  private final File save;
  private final String name;
  private final Location dest;

  Warp(File saveFile) throws WarpConfigException {
    save = saveFile;
    YamlConfiguration config = YamlConfiguration.loadConfiguration(save);

    if (!config.isSet("name")) {
      throw new WarpConfigException("Missing warp name");
    }

    name = config.getString("name");
    dest = loadLocation(config);
  }

  Warp(String alias, Location dest, File warpFolder) {
    this.dest = dest;
    this.name = alias;
    save = new File(warpFolder, String.valueOf(name.toLowerCase().hashCode()));
  }

  @Override
  public String getAlias() {
    return name;
  }

  @Override
  public void save() throws IOException {
    YamlConfiguration config = toYamlConfiguration();
    config.set("name", name);

    // TODO: Async?
    config.save(save);
  }

  @Override
  public boolean delete() {
    // TODO: Async?
    return save.delete();
  }

  private YamlConfiguration toYamlConfiguration() {
    YamlConfiguration config = new YamlConfiguration();
    config.set("world", dest.getWorld().getName());
    config.set("x", dest.getX());
    config.set("y", dest.getY());
    config.set("z", dest.getZ());
    config.set("yaw", dest.getYaw());
    config.set("pitch", dest.getPitch());
    return config;
  }

  private Location loadLocation(YamlConfiguration config)
      throws WarpConfigException {
    if (!config.isSet("world") || !config.isSet("x") || !config.isSet("y")
        || !config.isSet("z") || !config.isSet("yaw") || !config.isSet("pitch")) {
      throw new WarpConfigException("Missing location information for warp " + name);
    }

    String worldName = config.getString("world", "");
    World world = worldName != null ? Bukkit.getWorld(worldName) : null;
    if (world == null) {
      throw new WarpConfigException("World not found!");
    }

    double x = config.getDouble("x");
    double y = config.getDouble("y");
    double z = config.getDouble("z");
    float yaw = (float) config.getDouble("yaw");
    float pitch = (float) config.getDouble("pitch");

    return new Location(world, x, y, z, yaw, pitch);
  }

  @Override
  public Location getDestination() {
    return dest;
  }
}
