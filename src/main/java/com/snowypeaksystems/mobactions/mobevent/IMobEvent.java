package com.snowypeaksystems.mobactions.mobevent;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Objects representing running MobEvents.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IMobEvent {
  /** Returns the maximum amount of player for this event. */
  int getMaxPlayers();

  /** Returns the task that runs the countdown. */
  BukkitRunnable getRunnableTask();

  /** Adds the provided player to the list of joined players. */
  void addPlayer(MobActionsUser player);

  /** Removes the provided player from the list of joined players. */
  void removePlayer(MobActionsUser player);

  /** Returns true if the provided player has joined, false otherwise. */
  boolean hasPlayerJoined(MobActionsUser player);
}
