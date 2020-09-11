package com.snowypeaksystems.mobportals.exceptions;

/**
 * Exception that is thrown when a world is missing.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MissingWorldException extends WarpConfigException {
  public MissingWorldException(String world) {
    super("World \"" + world + "\" not found");
  }
}