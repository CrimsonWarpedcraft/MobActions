package com.snowypeaksystems.mobportals.exceptions;

/**
 * Exception that is thrown when the world give is invalid.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WorldNotFoundException extends RuntimeException {
  private final String missingWorld;

  public WorldNotFoundException(String world) {
    super("World \"" + world + "\" not found");
    missingWorld = world;
  }

  public String getMissingWorld() {
    return missingWorld;
  }
}
