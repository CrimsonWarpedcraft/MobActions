package com.snowypeaksystems.mobportals.exceptions;

/**
 * Exception throw when a mob is created where another mob already exists.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MobAlreadyExists extends Exception {
  public MobAlreadyExists(String message) {
    super(message);
  }
}
