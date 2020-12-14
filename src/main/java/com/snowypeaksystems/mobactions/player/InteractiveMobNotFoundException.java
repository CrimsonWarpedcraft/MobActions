package com.snowypeaksystems.mobactions.player;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

/**
 * Exception thrown when trying to remove a InteractiveMob that doesn't exist.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class InteractiveMobNotFoundException extends PlayerException {
  public InteractiveMobNotFoundException() {
    super(gm("remove-error"));
  }
}
