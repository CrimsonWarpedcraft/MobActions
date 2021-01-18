package com.snowypeaksystems.mobactions.mobevent;

import com.snowypeaksystems.mobactions.AMobActions;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MobEventManager implements IMobEventManager {
  private final Map<String, IMobEvent> events;
  private final AMobActions plugin;
  // todo: declare warp folder variable

  // todo: require warp folder argument, throw file not found if does not exist
  public MobEventManager(AMobActions plugin) {
    this.plugin = plugin;
    events = new HashMap<>();
  }

  @Override
  public IMobEvent createEvent(String name, MobData data, long timeout, int maxPlayers) {
    IMobEvent event = new MobEvent(name, data, timeout, plugin, maxPlayers);
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

    // todo: load events from data folder
  }

  @Override
  public void removeFromAll(MobActionsUser player) {
    for (IMobEvent event : events.values()) {
      event.removePlayer(player);
    }
  }

  @Override
  public Set<String> getLoadedEventNames() {
    return events.keySet();
  }

  @Override
  public Set<IMobEvent> getLoadedEvents() {
    return new HashSet<>(events.values());
  }
}
