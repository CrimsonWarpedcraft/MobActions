package com.snowypeaksystems.mobportals.exceptions;

/**
 * Created by Levi Muniz on 9/3/20.
 * Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MessageNotFoundException extends RuntimeException {
  private final String missingMessage;

  public MessageNotFoundException(String message) {
    super("Message for " + message + " not found");
    missingMessage = message;
  }

  public String getMissingMessage() {
    return missingMessage;
  }
}
