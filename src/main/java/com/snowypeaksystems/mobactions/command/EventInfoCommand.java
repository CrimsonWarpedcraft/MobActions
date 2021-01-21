package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.data.IWarpData;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.mobevent.EventNotFoundException;
import com.snowypeaksystems.mobactions.mobevent.IMobEvent;
import com.snowypeaksystems.mobactions.mobevent.IMobEventManager;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;

public class EventInfoCommand implements IEventInfoCommand {
  private final String name;
  private final IMobEventManager manager;

  public EventInfoCommand(String name, IMobEventManager manager) {
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

    IMobEvent event = manager.getEvent(name);

    String status = "";
    if (event.getState() == IMobEvent.State.OPEN) {
      status = "Open";
    } else if (event.getState() == IMobEvent.State.CLOSED) {
      status = "Closed";
    } else if (event.getState() == IMobEvent.State.COUNTDOWN) {
      status = "Starting";
    }

    String players = String.valueOf(event.getPlayerSet().size());
    if (event.getMaxPlayers() > 0) {
      players += " / " + event.getMaxPlayers();
    }

    String type = "";
    String details = "";
    MobData data = event.getData();
    if (data instanceof ICommandData) {
      type = "Command";
      details = ((ICommandData) event.getData()).getCommand("{player}");
    } else if (data instanceof IWarpData) {
      type = "Warp";
      details = ((IWarpData) event.getData()).getAlias();
    }

    player.sendMessage(gm("event-info-text", event.getAlias()),
        gm("event-status-text", status),
        gm("event-timeout-text", String.valueOf(event.getTimeout())),
        gm("event-players-text", players), gm("event-type-text", type),
        gm("event-type-details-text", type, details));
    DebugLogger.getLogger().log("Event info displayed");
  }
}
