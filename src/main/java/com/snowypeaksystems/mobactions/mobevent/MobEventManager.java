package com.snowypeaksystems.mobactions.mobevent;

import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import java.io.IOException;
import java.util.Set;

/**
 * Objects to store and manage MobEvents.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface MobEventManager {
  /** Creates and returns an MobEvent. */
  MobEvent createEvent(String name, MobData data, long timeout, int maxPlayers) throws IOException;

  /** Removes and cancels the event. */
  void removeEvent(String name);

  /** Returns the event with the provided name, or null if it does not exist. */
  MobEvent getEvent(String name);

  /** Returns true if the event with the provided name exists, or null otherwise. */
  boolean exists(String name);

  /** Reloads all events. */
  void reload();

  /** Removes the player from all events. Helpful for logouts. */
  void removeFromAll(MobActionsUser player);

  /** Returns a set of event names. */
  Set<String> getLoadedEventNames();

  /** Returns a set of loaded events. */
  Set<MobEvent> getLoadedEvents();
}
