package com.snowypeaksystems.mobportals.messages;

/**
 * Mutable extension of Messages.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MutableMessages extends Messages {
  public static Message put(String key, Message value) {
    return messages.put(key, value);
  }

  public static boolean contains(String key) {
    return messages.containsKey(key);
  }
}
