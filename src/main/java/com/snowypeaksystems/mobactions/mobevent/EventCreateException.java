package com.snowypeaksystems.mobactions.mobevent;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

public class EventCreateException extends PlayerException {
  public EventCreateException(String message) {
    super(message);
  }
}
