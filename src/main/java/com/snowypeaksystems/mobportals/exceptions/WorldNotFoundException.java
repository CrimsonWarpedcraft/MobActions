package com.snowypeaksystems.mobportals.exceptions;

/**
 * Exception that is thrown when the world give is invalid.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WorldNotFoundException extends RuntimeException {
  public WorldNotFoundException(String message) {
    super(message);
  }
}
