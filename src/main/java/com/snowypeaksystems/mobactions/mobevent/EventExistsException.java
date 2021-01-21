package com.snowypeaksystems.mobactions.mobevent;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

public class EventExistsException extends PlayerException {
  public EventExistsException(String name) {
    super(gm("event-exists-error", name));
  }
}
