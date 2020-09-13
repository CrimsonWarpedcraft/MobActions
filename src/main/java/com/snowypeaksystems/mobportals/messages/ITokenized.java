package com.snowypeaksystems.mobportals.messages;

/**
 * Classes that are tokenized and able to filled with Strings.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface ITokenized {
  /**
   * Gets the message, replacing IMessage.TOKEN with any specified args.
   * @param args List of arguments to replace in the message
   * @return Returns the String form of the message
   * @throws IllegalArgumentException if the instances of IMessage.TOKEN in the instance are not
   *             equal to the length of args
   */
  String getString(String... args);
}
