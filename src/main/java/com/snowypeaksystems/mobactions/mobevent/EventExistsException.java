package com.snowypeaksystems.mobactions.mobevent;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Thrown when creating an event that already exists.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventExistsException extends PlayerException {
  public EventExistsException(String name) {
    super(gm("event-exists-error", name));
  }
}
