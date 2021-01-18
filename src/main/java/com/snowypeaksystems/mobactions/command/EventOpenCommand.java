package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.mobevent.EventNotFoundException;
import com.snowypeaksystems.mobactions.mobevent.IMobEventManager;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;

public class EventOpenCommand implements IEventRemoveCommand {
  private final String name;
  private final IMobEventManager manager;

  public EventOpenCommand(String name, IMobEventManager manager) {
    this.name = name;
    this.manager = manager;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Opening event");
    if (!player.canStartEvents()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (!manager.exists(name)) {
      DebugLogger.getLogger().log("Event not found");
      throw new EventNotFoundException(name);
    }

    manager.getEvent(name).open();
    player.sendMessage(gm("event-open-text", name));
    DebugLogger.getLogger().log("Event opened");
  }
}
