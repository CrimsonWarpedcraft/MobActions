package com.snowypeaksystems.mobportals.exceptions;

/**
 * Exception thrown when a warp is incorrectly configured.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WarpConfigException extends RuntimeException {
  public WarpConfigException(String message) {
    super(message);
  }
}
