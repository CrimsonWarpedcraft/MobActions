package com.snowypeaksystems.mobactions.mob;

import org.bukkit.entity.LivingEntity;

/**
 * Extends the Interactive interface by adding methods for mobs.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IInteractiveMob extends Interactive {
  String DATA_KEY = "data";

  /** Stores data on the LivingEntity. */
  void store();

  /** Removes data from the LivingEntity. */
  void purge();

  /** Returns true if data is stored on this LivingEntity, false otherwise. */
  boolean exists();

  /** Returns the LivingEntity instance associated with the Interactive. */
  LivingEntity getLivingEntity();
}
