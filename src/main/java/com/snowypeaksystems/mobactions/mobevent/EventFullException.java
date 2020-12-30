package com.snowypeaksystems.mobactions.mobevent;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Thrown when a player tries to join a full event.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventFullException extends PlayerException {
  public EventFullException(String name) {
    super(gm("event-full-error", name));
  }
}
