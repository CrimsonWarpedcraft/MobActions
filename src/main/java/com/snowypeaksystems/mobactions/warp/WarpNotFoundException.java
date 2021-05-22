package com.snowypeaksystems.mobactions.warp;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Thrown when a requested warp does not exist.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WarpNotFoundException extends PlayerException {
  public WarpNotFoundException(String warpName) {
    super(gm("warp-missing", warpName));
  }
}
