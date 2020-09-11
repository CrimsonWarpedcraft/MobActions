package com.snowypeaksystems.mobportals.mobs;

import com.snowypeaksystems.mobportals.persistence.IMobWritable;
import org.bukkit.entity.LivingEntity;

/**
 * A mob that can be used to store IMobWritable data.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IMob<T extends IMobWritable> {
  /** Stores the IMobWritable data for this IMob. */
  void create(T data);

  /** Returns and removes the data stored for this IMob if present, returns null otherwise. */
  T destroy();

  /** Returns the stored instance, or null if it does not exist. */
  T getData();

  /** Returns true if the IMob has data stored, false otherwise. */
  boolean hasData();

  /** Returns the LivingEntity instance associated with the IMob. */
  LivingEntity getLivingEntity();
}
