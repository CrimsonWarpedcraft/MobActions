package com.snowypeaksystems.mobactions.player;

/**
 * Exceptions sent to Players.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public abstract class PlayerException extends Exception {
  /** Returns the String formatted to send to the Player. */
  abstract String getPlayerFormattedString();
}
