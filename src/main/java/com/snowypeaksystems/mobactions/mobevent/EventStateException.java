package com.snowypeaksystems.mobactions.mobevent;

import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Thrown when an operation is not possible in the IMobEvent's current state.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventStateException extends PlayerException {
  public EventStateException(String message) {
    super(message);
  }
}
