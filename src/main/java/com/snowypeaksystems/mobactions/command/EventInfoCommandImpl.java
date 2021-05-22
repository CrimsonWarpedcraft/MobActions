package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.data.CommandData;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.data.WarpData;
import com.snowypeaksystems.mobactions.mobevent.EventNotFoundException;
import com.snowypeaksystems.mobactions.mobevent.MobEvent;
import com.snowypeaksystems.mobactions.mobevent.MobEventManager;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;

/**
 * Implementation of EventInfoCommandImpl.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventInfoCommandImpl implements EventInfoCommand {
  private final String name;
  private final MobEventManager manager;

  public EventInfoCommandImpl(String name, MobEventManager manager) {
    this.name = name;
    this.manager = manager;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Getting event info");
    if (!player.canGetEventInfo()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (!manager.exists(name)) {
      DebugLogger.getLogger().log("Event not found");
      throw new EventNotFoundException(name);
    }

    MobEvent event = manager.getEvent(name);

    String status = "";
    if (event.getState() == MobEvent.State.OPEN) {
      status = gm("event-status-open");
    } else if (event.getState() == MobEvent.State.CLOSED) {
      status = gm("event-status-closed");
    } else if (event.getState() == MobEvent.State.COUNTDOWN) {
      status = gm("event-status-starting");
    }

    String players = String.valueOf(event.getPlayerSet().size());
    if (event.getMaxPlayers() > 0) {
      players += " / " + event.getMaxPlayers();
    }

    String type = "";
    String details = "";
    MobData data = event.getData();
    if (data instanceof CommandData) {
      CommandData commandData = (CommandData) data;

      if (commandData.isConsoleCommand()) {
        type = gm("event-type-command");
      } else {
        type = gm("event-type-consolecmd");
      }

      details = commandData.getCommand("{player}");
    } else if (data instanceof WarpData) {
      type = gm("event-type-warp");
      details = ((WarpData) event.getData()).getAlias();
    }

    player.sendMessage(gm("event-info-text", event.getAlias()),
        gm("event-status-text", status),
        gm("event-timeout-text", String.valueOf(event.getTimeout())),
        gm("event-players-text", players), gm("event-type-text", type),
        gm("event-type-details-text", type, details));
    DebugLogger.getLogger().log("Event info displayed");
  }
}
