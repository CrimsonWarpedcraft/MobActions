package com.snowypeaksystems.mobactions.mobevent;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

public class EventCreateException extends PlayerException {
  public EventCreateException(String name) {
    super(gm("event-save-error", name));
  }
}
