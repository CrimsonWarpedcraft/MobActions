package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.mobevent.EventNotFoundException;
import com.snowypeaksystems.mobactions.mobevent.IMobEventManager;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;

public class EventForceStartCommand implements IEventForceStartCommand {
  private final String name;
  private final IMobEventManager manager;

  public EventForceStartCommand(String name, IMobEventManager manager) {
    this.name = name;
    this.manager = manager;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Starting event");
    if (!player.canStartEvents()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (!manager.exists(name)) {
      DebugLogger.getLogger().log("Event not found");
      throw new EventNotFoundException(name);
    }

    manager.getEvent(name).forceStart();
    player.sendMessage(gm("event-forcestart-text", name));
    DebugLogger.getLogger().log("Event started");
  }
}
