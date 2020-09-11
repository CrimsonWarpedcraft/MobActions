package com.snowypeaksystems.mobportals.warps;

import com.snowypeaksystems.mobportals.exceptions.MissingWorldException;
import com.snowypeaksystems.mobportals.exceptions.WarpConfigException;
import java.io.File;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.Server;
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

  Warp(File file, Server server) throws WarpConfigException {
    save = file;
    YamlConfiguration config = YamlConfiguration.loadConfiguration(save);

    if (!config.isSet("name")) {
      throw new WarpConfigException("Missing warp name");
    }

    name = config.getString("name");
    dest = loadLocation(config, server);
  }

  Warp(String name, Location dest, File warpFolder) throws IOException {
    this.name = name;
    this.dest = dest;
    save = new File(warpFolder, String.valueOf(name.toLowerCase().hashCode()));
    save();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Location getDestination() {
    return dest;
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

  @Override
  public String getKey() {
    return name;
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

  private Location loadLocation(YamlConfiguration config, Server server)
      throws WarpConfigException {
    if (!config.isSet("world") || !config.isSet("x") || !config.isSet("y")
        || !config.isSet("z") || !config.isSet("yaw") || !config.isSet("pitch")) {
      throw new WarpConfigException("Missing location information for warp " + name);
    }

    String worldName = config.getString("world", "");
    World world = worldName != null ? server.getWorld(worldName) : null;
    if (world == null) {
      throw new MissingWorldException(worldName);
    }

    double x = config.getDouble("x");
    double y = config.getDouble("y");
    double z = config.getDouble("z");
    float yaw = (float) config.getDouble("yaw");
    float pitch = (float) config.getDouble("pitch");

    return new Location(world, x, y, z, yaw, pitch);
  }
}
