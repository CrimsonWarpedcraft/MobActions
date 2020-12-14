package com.snowypeaksystems.mobactions.player;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

/**
 * Thrown when an InteractiveMob already exists where an new instance is attempting to be created.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class InteractiveMobAlreadyExistsException extends PlayerException {
  public InteractiveMobAlreadyExistsException() {
    super(gm("mob-exists-error"));
  }
}
