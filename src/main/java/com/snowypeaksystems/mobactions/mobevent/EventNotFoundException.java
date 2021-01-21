package com.snowypeaksystems.mobactions.mobevent;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Thrown when an event is not found.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventNotFoundException extends PlayerException {
  public EventNotFoundException(String event) {
    super(gm("event-missing-error", event));
  }
}
