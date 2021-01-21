package com.snowypeaksystems.mobactions.warp;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

public class WarpExistsException extends PlayerException {
  public WarpExistsException(String warpName) {
    super(gm("warp-already-exists", warpName));
  }
}
