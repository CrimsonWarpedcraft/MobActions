package com.snowypeaksystems.mobactions.player;

/**
 * Exception thrown when trying to remove a InteractiveMob that doesn't exist.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class InteractiveMobNotFoundException extends PlayerException {
  public InteractiveMobNotFoundException(String message) {
    super(message);
  }
}
