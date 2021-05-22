package com.snowypeaksystems.mobactions.data;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Data for IWarps.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WarpDataImpl implements WarpData {
  private final String name;

  /** Create WarpData from a current entity. */
  public WarpDataImpl(LivingEntity entity, JavaPlugin plugin) throws IncompleteDataException {
    PersistentDataContainer container = entity.getPersistentDataContainer();
    NamespacedKey warpKey = new NamespacedKey(plugin, WARP_KEY);

    if (!container.has(warpKey, PersistentDataType.STRING)) {
      throw new IncompleteDataException();
    }

    this.name = container.get(warpKey, PersistentDataType.STRING);
  }

  public WarpDataImpl(String name) {
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

  @Override
  public String getNametagString() {
    return gm("nametag-portal-text", name);
  }
}
