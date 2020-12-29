package com.snowypeaksystems.mobactions.warp;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

public class WarpNotFoundException extends PlayerException {
  public WarpNotFoundException(String warpName) {
    super(gm("warp-missing", warpName));
  }
}
