package com.snowypeaksystems.mobactions.mobevent;

import com.snowypeaksystems.mobactions.data.StatusData;

/**
 * Data for activating a mob event.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IActivateMobEventData extends StatusData {
  int getMaxPlayers();

  long getTimeout();
}
