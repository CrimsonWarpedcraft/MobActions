package com.snowypeaksystems.mobactions.mobevent;

import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Thrown when an event could not be created.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventCreateException extends PlayerException {
  public EventCreateException(String message) {
    super(message);
  }
}
