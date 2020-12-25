package com.snowypeaksystems.mobactions.player;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

/**
 * Thrown when a warp cannot be created successfully.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WarpCreateException extends PlayerException {
  public WarpCreateException() {
    super(gm("warp-create-error"));
  }
}
