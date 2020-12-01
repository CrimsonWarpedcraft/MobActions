package com.snowypeaksystems.mobactions.data;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Data for IWarps.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WarpData implements IWarpData {
  private final String name;

  public WarpData(LivingEntity entity, JavaPlugin plugin) {
    this.name = entity.getPersistentDataContainer().get(
        new NamespacedKey(plugin, WARP_KEY), PersistentDataType.STRING);
  }

  public WarpData(String name) {
    this.name = name;
  }

  @Override
  public void store(LivingEntity entity, JavaPlugin plugin) {
    entity.getPersistentDataContainer()
        .set(new NamespacedKey(plugin, WARP_KEY), PersistentDataType.STRING, name);
  }

  @Override
  public void purge(LivingEntity entity, JavaPlugin plugin) {
    entity.getPersistentDataContainer().remove(new NamespacedKey(plugin, WARP_KEY));
  }

  @Override
  public String getKeyString() {
    return WARP_KEY;
  }

  @Override
  public String getAlias() {
    return name;
  }
}
