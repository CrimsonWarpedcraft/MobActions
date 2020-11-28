package com.snowypeaksystems.mobactions.player;

import com.snowypeaksystems.mobactions.mob.data.MobData;

/**
 * An object that can create or destroy MobAction mobs.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface Editor {
  /** Stores the data and marks the player as creating, or not creating if null. */
  void setCreating(MobData data);

  /** Marks the player as destroying if true, or not destroying if false. */
  void setDestroying(boolean value);

  /** Returns the status of the editor. */
  IStatus getStatus();
}
