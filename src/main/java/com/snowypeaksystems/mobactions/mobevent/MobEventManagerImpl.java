package com.snowypeaksystems.mobactions.mobevent;

import com.snowypeaksystems.mobactions.AbstractMobActions;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.Bukkit;

/**
 * Implementation of MobEventManager.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MobEventManagerImpl implements MobEventManager {
  private final Map<String, MobEvent> events;
  private final AbstractMobActions plugin;
  private final File eventFolder;

  /** Create MobEventManager from provided plugin and event folder. */
  public MobEventManagerImpl(AbstractMobActions plugin, File eventFolder)
      throws FileNotFoundException {
    this.plugin = plugin;
    events = new HashMap<>();

    if (!eventFolder.exists() || !eventFolder.isDirectory()) {
      throw new FileNotFoundException("Event Folder Not Found");
    }

    this.eventFolder = eventFolder;
    reload();
  }

  @Override
  public MobEvent createEvent(String name, MobData data, long timeout, int maxPlayers)
      throws IOException {
    MobEvent event = new MobEventImpl(name, data, timeout, plugin, maxPlayers, eventFolder);
    event.save();
    events.put(name.toLowerCase(), event);

    return event;
  }

  @Override
  public void removeEvent(String name) {
    MobEvent event = events.remove(name.toLowerCase());

    if (event != null) {
      event.cancel();
      event.delete();
    }
  }

  @Override
  public MobEvent getEvent(String name) {
    return events.get(name.toLowerCase());
  }

  @Override
  public boolean exists(String name) {
    return events.containsKey(name.toLowerCase());
  }

  @Override
  public void reload() {
    for (MobEvent event : events.values()) {
      if (event.getState() != MobEvent.State.CLOSED) {
        event.cancel();
      }
    }

    events.clear();
    File[] files = eventFolder.listFiles();

    if (files != null) {
      for (File f : files) {
        if (f.getName().startsWith(".") || f.isDirectory()) {
          continue;
        }

        try {
          MobEvent mobEvent = new MobEventImpl(f, plugin);
          events.put(mobEvent.getAlias().toLowerCase(), mobEvent);
        } catch (EventConfigException e) {
          Bukkit.getLogger().log(Level.FINE, e.getMessage(), e);
        }
      }
    }

  }

  @Override
  public void removeFromAll(MobActionsUser player) {
    for (MobEvent event : events.values()) {
      event.removePlayer(player);
    }
  }

  @Override
  public Set<String> getLoadedEventNames() {
    return events.keySet();
  }

  @Override
  public Set<MobEvent> getLoadedEvents() {
    return new HashSet<>(events.values());
  }
}
