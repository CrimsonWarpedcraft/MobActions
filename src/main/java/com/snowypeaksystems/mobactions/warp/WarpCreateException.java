package com.snowypeaksystems.mobactions.warp;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Thrown when a warp cannot be created successfully.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WarpCreateException extends PlayerException {
  public WarpCreateException() {
    super(gm("warp-create-error"));
  }
}
