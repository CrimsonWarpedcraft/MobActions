package com.snowypeaksystems.mobactions.player;

import com.snowypeaksystems.mobactions.data.StatusData;

/**
 * Represents a user's state and creation.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IStatus {
  enum Mode {
    CREATING,
    DESTROYING,
    ACTIVATING,
    DEACTIVATING,
    NONE
  }

  /** Returns the current mode. */
  Mode getMode();

  /** Sets the mode. */
  void setMode(Mode mode);

  /** Returns the MobData if it exists, or null otherwise. */
  StatusData getStatusData();

  /** Sets the MobData. */
  void setStatusData(StatusData data);
}
