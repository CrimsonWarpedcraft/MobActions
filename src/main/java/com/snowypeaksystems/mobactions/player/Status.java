package com.snowypeaksystems.mobactions.player;

import com.snowypeaksystems.mobactions.data.MobData;

public class Status implements IStatus {
  private MobData data;
  private Mode mode;

  public Status() {
    mode = Mode.NONE;
  }

  @Override
  public Mode getMode() {
    return mode;
  }

  @Override
  public void setMode(Mode mode) {
    if (mode == null) {
      throw new NullPointerException("mode cannot be null.");
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
