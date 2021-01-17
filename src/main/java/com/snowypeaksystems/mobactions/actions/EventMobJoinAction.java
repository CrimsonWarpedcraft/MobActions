package com.snowypeaksystems.mobactions.actions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.IInteractiveMob;
import com.snowypeaksystems.mobactions.data.IEventData;
import com.snowypeaksystems.mobactions.event.MobEventJoinEvent;
import com.snowypeaksystems.mobactions.event.MobEventLeaveEvent;
import com.snowypeaksystems.mobactions.mobevent.EventNotFoundException;
import com.snowypeaksystems.mobactions.mobevent.IMobEvent;
import com.snowypeaksystems.mobactions.mobevent.IMobEventManager;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import org.bukkit.Bukkit;

public class EventMobJoinAction implements IEventMobJoinAction {
  private final IInteractiveMob mob;
  private final IEventData data;
  private final IMobEventManager manager;

  /** Create an EventMobJoinAction. */
  public EventMobJoinAction(IInteractiveMob mob, IEventData data, IMobEventManager manager) {
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

    if (!manager.exists(eventName)) {
      DebugLogger.getLogger().log("Event not found");
      throw new EventNotFoundException(eventName);
    }

    IMobEvent mobEvent = manager.getEvent(eventName);
    if (!mobEvent.hasPlayerJoined(player)) {
      MobEventJoinEvent event = new MobEventJoinEvent(player, mob, mobEvent);
      Bukkit.getPluginManager().callEvent(event);
      if (!event.isCancelled()) {
        mobEvent.addPlayer(player);
        player.sendMessage(gm("event-joined-text", eventName), gm("event-leave-info"));
      }
    } else {
      MobEventLeaveEvent event = new MobEventLeaveEvent(player, mob, mobEvent);
      Bukkit.getPluginManager().callEvent(event);
      if (!event.isCancelled()) {
        mobEvent.removePlayer(player);
        player.sendMessage(gm("event-left-text", eventName));
      }
    }
  }
}
