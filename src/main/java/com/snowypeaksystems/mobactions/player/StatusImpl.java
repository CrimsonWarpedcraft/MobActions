package com.snowypeaksystems.mobactions.player;

import com.snowypeaksystems.mobactions.data.MobData;

/**
 * Implementation of Status.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class StatusImpl implements Status {
  private MobData data;
  private Mode mode;

  public StatusImpl() {
    mode = Mode.NONE;
  }

  @Override
  public Mode getMode() {
    return mode;
  }

  @Override
  public void setMode(Mode mode) {
    if (mode == null) {
      throw new NullPointerException("Mode cannot be null.");
    }

    this.mode = mode;

    if (mode != Mode.CREATING) {
      data = null;
    }
  }

  @Override
  public MobData getMobData() {
    return data;
  }

  @Override
  public void setMobData(MobData data) {
    if (mode == Mode.CREATING) {
      this.data = data;
    }
  }
}
