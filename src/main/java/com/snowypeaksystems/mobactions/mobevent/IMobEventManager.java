package com.snowypeaksystems.mobactions.mobevent;

import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;

/**
 * Objects to store and manage IMobEvents.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IMobEventManager {
  /** Creates and returns an IMobEvent. */
  IMobEvent createEvent(String name, MobData data, long timeout, int maxPlayers);

  /** Removes and cancels the event. */
  void removeEvent(String name);

  /** Returns the event with the provided name, or null if it does not exist. */
  IMobEvent getEvent(String name);

  /** Returns true if the event with the provided name exists, or null otherwise. */
  boolean exists(String name);

  /** Removes all events. */
  void clear();

  /** Removes the player from all events. Helpful for logouts. */
  void removeFromAll(MobActionsUser player);
}
