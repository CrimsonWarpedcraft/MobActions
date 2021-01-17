package com.snowypeaksystems.mobactions.event;

import com.snowypeaksystems.mobactions.data.MobData;

/**
 * Events that have associated mob data.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface MobDataEvent {
  /** Returns the MobData for this event. */
  MobData getData();
}
