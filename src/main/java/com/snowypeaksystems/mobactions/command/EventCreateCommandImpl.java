package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.mobevent.EventCreateException;
import com.snowypeaksystems.mobactions.mobevent.EventExistsException;
import com.snowypeaksystems.mobactions.mobevent.MobEventManager;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import java.io.IOException;

/**
 * Implementation of EventCreateCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventCreateCommandImpl implements EventCreateCommand {
  private final String name;
  private final MobData data;
  private final long timeout;
  private final int maxPlayers;
  private final MobEventManager manager;

  /**
   * Create an event create command for an event with the provided name, data, timeout, and max
   * players.
   */
  public EventCreateCommandImpl(String name, MobData data, long timeout, int maxPlayers,
                                MobEventManager manager) {
    this.name = name;
    this.data = data;
    this.timeout = timeout;
    this.maxPlayers = maxPlayers;
    this.manager = manager;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Creating event");
    if (!player.canCreateEvents()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (manager.exists(name)) {
      DebugLogger.getLogger().log("Event already exists");
      throw new EventExistsException(name);
    }

    if (timeout < 1) {
      throw new EventCreateException(gm("event-timeout-error"));
    }

    if (maxPlayers < 0) {
      throw new EventCreateException(gm("event-players-error"));
    }

    try {
      manager.createEvent(name, data, timeout, maxPlayers);
      player.sendMessage(gm("event-create-text", name));
    } catch (IOException e) {
      DebugLogger.getLogger().log("Event save error");
      throw new EventCreateException(gm("event-save-error", name));
    }

    DebugLogger.getLogger().log("Event created");
  }
}
