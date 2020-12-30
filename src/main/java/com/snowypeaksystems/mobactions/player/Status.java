package com.snowypeaksystems.mobactions.player;

import com.snowypeaksystems.mobactions.data.StatusData;

public class Status implements IStatus {
  private StatusData data;
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

    if (mode != Mode.CREATING && mode != Mode.ACTIVATING) {
      data = null;
    }
  }

  @Override
  public StatusData getStatusData() {
    return data;
  }

  @Override
  public void setStatusData(StatusData data) {
    if (mode == Mode.CREATING || mode == Mode.ACTIVATING) {
      this.data = data;
    }
  }
}
