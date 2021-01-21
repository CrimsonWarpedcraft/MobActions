package com.snowypeaksystems.mobactions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Exception thrown when trying to remove a InteractiveMob that doesn't exist.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class InteractiveMobNotFoundException extends PlayerException {
  public InteractiveMobNotFoundException() {
    super(gm("remove-error"));
  }
}
