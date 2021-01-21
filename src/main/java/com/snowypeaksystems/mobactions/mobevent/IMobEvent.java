package com.snowypeaksystems.mobactions.mobevent;

import com.snowypeaksystems.mobactions.data.AliasedData;
import com.snowypeaksystems.mobactions.data.FileData;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import java.util.Set;

/**
 * Objects representing running MobEvents.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IMobEvent extends AliasedData, FileData {
  enum State {
    OPEN,
    COUNTDOWN,
    CLOSED
  }

  /** Adds the provided player to the list of joined players. */
  void addPlayer(MobActionsUser player) throws EventStateException;

  /** Removes the provided player from the list of joined players. */
  void removePlayer(MobActionsUser player);

  /** Returns true if the provided player has joined, false otherwise. */
  boolean hasPlayerJoined(MobActionsUser player);

  /** Returns an unmodifiable copy of the set of joined players. */
  Set<MobActionsUser> getPlayerSet();

  /** Returns the mob data for the event. */
  MobData getData();

  /** Returns the timeout in seconds. */
  long getTimeout();

  /** Returns the max players allowed for the event. */
  int getMaxPlayers();

  /**
   * Allows players to join the event and begins the event's timeout period. The event countdown
   * will start when the timeout has been reached or the maximum players if set have joined. Players
   * will no longer be able to join after the countdown begins. The event action will trigger after
   * the countdown completes.
   */
  void open() throws EventStateException;

  /**
   * Forces the event countdown to start now with all currently joined players. This stops any
   * timeout periods and prevents players from joining. The event action will trigger after the
   * countdown completes.
   */
  void forceStart() throws EventStateException;

  /**
   * Stops any running timeout period or countdown and prevents players from joining without the
   * event action triggering. Clears the list of all joined players.
   */
  void cancel();

  /**
   * Returns State.OPEN if the event is open, State.COUNTDOWN when the countdown is running, and
   * State.CLOSED when the event is not running.
   */
  State getState();
}
