package com.snowypeaksystems.mobactions.mobevent;

import com.snowypeaksystems.mobactions.actions.MobAction;
import com.snowypeaksystems.mobactions.player.MobActionsUser;

/**
 * Objects to store and manage IMobEvents.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IMobEventManager {
  /** Creates, schedules, and returns a IMobEvent. */
  IMobEvent createEvent(String name, int maxPlayers, long timeout, MobAction data);

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
