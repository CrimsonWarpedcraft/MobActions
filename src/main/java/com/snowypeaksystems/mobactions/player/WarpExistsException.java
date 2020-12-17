package com.snowypeaksystems.mobactions.player;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

public class WarpExistsException extends PlayerException {
  public WarpExistsException() {
    super(gm("warp-already-exists"));
  }
}
