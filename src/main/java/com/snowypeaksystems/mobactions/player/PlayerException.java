package com.snowypeaksystems.mobactions.player;

/**
 * Exceptions sent to Players.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public abstract class PlayerException extends Exception {
  private final String message;

  public PlayerException(String message) {
    this.message = message;
  }

  /** Returns the String formatted to send to the Player. */
  public String getPlayerFormattedString() {
    return message;
  }
}
