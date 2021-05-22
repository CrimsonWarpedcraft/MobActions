package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.mobevent.EventNotFoundException;
import com.snowypeaksystems.mobactions.mobevent.MobEventManager;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;

/**
 * Implementation of EventRemoveCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventRemoveCommandImpl implements EventRemoveCommand {
  private final String name;
  private final MobEventManager manager;

  public EventRemoveCommandImpl(String name, MobEventManager manager) {
    this.name = name;
    this.manager = manager;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Deleting mob event");
    if (!player.canRemoveEvents()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (!manager.exists(name)) {
      DebugLogger.getLogger().log("Mob event not found");
      throw new EventNotFoundException(name);
    }

    manager.removeEvent(name);
    player.sendMessage(gm("event-remove-text", name));
    DebugLogger.getLogger().log("Mob event deleted");
  }
}
