package com.snowypeaksystems.mobactions.player;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

public class WarpNotFoundException extends PlayerException {
  public WarpNotFoundException(String warpName) {
    super(gm("warp-missing", warpName));
  }
}
