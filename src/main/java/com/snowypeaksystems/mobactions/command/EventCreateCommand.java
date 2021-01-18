package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.mobevent.EventCreateException;
import com.snowypeaksystems.mobactions.mobevent.EventExistsException;
import com.snowypeaksystems.mobactions.mobevent.IMobEventManager;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import java.io.IOException;

public class EventCreateCommand implements IEventCreateCommand {
  private final String name;
  private final MobData data;
  private final long timeout;
  private final int maxPlayers;
  private final IMobEventManager manager;

  /**
   * Create an event create command for an event with the provided name, data, timeout, and max
   * players.
   */
  public EventCreateCommand(String name, MobData data, long timeout, int maxPlayers,
                            IMobEventManager manager) {
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

    try {
      manager.createEvent(name, data, timeout, maxPlayers);
      player.sendMessage(gm("event-create-text", name));
    } catch (IOException e) {
      DebugLogger.getLogger().log("Event save error");
      throw new EventCreateException(name);
    }

    DebugLogger.getLogger().log("Event created");
  }
}
