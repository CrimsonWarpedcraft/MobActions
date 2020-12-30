package com.snowypeaksystems.mobactions.actions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.data.IEventData;
import com.snowypeaksystems.mobactions.mobevent.EventNotFoundException;
import com.snowypeaksystems.mobactions.mobevent.IMobEvent;
import com.snowypeaksystems.mobactions.mobevent.IMobEventManager;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;

public class EventMobAction implements IEventMobAction {
  private final IInteractiveMob mob;
  private final IEventData data;
  private final IMobEventManager manager;

  /** Create an EventMobAction. */
  public EventMobAction(IInteractiveMob mob, IEventData data, IMobEventManager manager) {
    this.mob = mob;
    this.data = data;
    this.manager = manager;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Changing player's event join status");
    String eventName = data.getAlias();

    if (!player.canJoinEvent(eventName)) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (!manager.exists(data.getAlias())) {
      DebugLogger.getLogger().log("Event not found");
      throw new EventNotFoundException(data.getAlias());
    }

    IMobEvent mobEvent = manager.getEvent(mob.getData().getAlias());
    if (!mobEvent.hasPlayerJoined(player)) {
      mobEvent.addPlayer(player);
      player.sendMessage(gm("event-joined-text", data.getAlias()), gm("event-leave-info"));
    } else {
      mobEvent.removePlayer(player);
      player.sendMessage(gm("event-left-text"));
    }
  }
}
