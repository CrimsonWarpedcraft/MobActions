package com.snowypeaksystems.mobactions.warp;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Thrown when creating a warp that already exists.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WarpExistsException extends PlayerException {
  public WarpExistsException(String warpName) {
    super(gm("warp-already-exists", warpName));
  }
}
