package com.snowypeaksystems.mobactions.data;

import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Data that can be stored by a Mob.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface MobData extends AliasedData {
  /** Stores the data on the entity. */
  void store(LivingEntity entity, JavaPlugin plugin);

  /** Remotes the data from the entity. */
  void purge(LivingEntity entity, JavaPlugin plugin);

  /** Returns the key String for the type of data. */
  String getKeyString();
}
