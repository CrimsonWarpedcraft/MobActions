package com.snowypeaksystems.mobportals.messages;

/**
 * Message to be sent to the user.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IMessage {
  /**
   * Gets the message, replacing IMessage.TOKEN with any specified args.
   * @param args List of arguments to replace in the message
   * @return Returns the String form of the message
   * @throws IllegalArgumentException if the instances of IMessage.TOKEN in the instance are
   *             less than the length of args
   */
  String getMessage(String... args);
}
