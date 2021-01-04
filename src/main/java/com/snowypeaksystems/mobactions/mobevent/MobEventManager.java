package com.snowypeaksystems.mobactions.mobevent;

import com.snowypeaksystems.mobactions.AMobActions;
import com.snowypeaksystems.mobactions.actions.CommandAction;
import com.snowypeaksystems.mobactions.actions.MobAction;
import com.snowypeaksystems.mobactions.actions.WarpAction;
import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.data.IWarpData;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import java.util.HashMap;
import java.util.Map;

public class MobEventManager implements IMobEventManager {
  private final Map<String, IMobEvent> events;
  private final AMobActions plugin;

  public MobEventManager(AMobActions plugin) {
    this.plugin = plugin;
    events = new HashMap<>();
  }

  @Override
  public IMobEvent createEvent(String name, MobData data, long timeout, int maxPlayers) {
    MobAction action;

    if (data instanceof IWarpData) {
      action = new WarpAction(null, (IWarpData) data, plugin.getWarpManager());
    } else if (data instanceof ICommandData) {
      action = new CommandAction(null, (ICommandData) data);
    } else {
      throw new IllegalArgumentException("That data is not yet supported with events.");
    }

    IMobEvent event = new MobEvent(name, action, timeout, plugin, maxPlayers);
    events.put(name.toLowerCase(), event);

    return event;
  }

  @Override
  public void removeEvent(String name) {
    IMobEvent event = events.remove(name.toLowerCase());

    if (event != null && event.getState() != IMobEvent.State.CLOSED) {
      event.cancel();
    }
  }

  @Override
  public IMobEvent getEvent(String name) {
    return events.get(name.toLowerCase());
  }

  @Override
  public boolean exists(String name) {
    return events.containsKey(name.toLowerCase());
  }

  @Override
  public void reload() {
    for (IMobEvent event : events.values()) {
      if (event.getState() != IMobEvent.State.CLOSED) {
        event.cancel();
      }
    }

    events.clear();
  }

  @Override
  public void removeFromAll(MobActionsUser player) {
    for (IMobEvent event : events.values()) {
      event.removePlayer(player);
    }
  }
}
