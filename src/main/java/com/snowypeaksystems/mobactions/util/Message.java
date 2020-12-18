package com.snowypeaksystems.mobactions.util;

import org.bukkit.ChatColor;

/**
 * Implementation of IMessage.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Message implements IMessage {
  private final String message;
  private final int tokens;
  private final String tokenStr;

  Message(String message) {
    this.tokenStr = String.valueOf(new char[]{TOKEN_PREFIX, TOKEN_SUFFIX});
    this.message = ChatColor.translateAlternateColorCodes('&', message);

    int tokens = 0;
    for (int i = 0, j = 0; i < message.length(); i++) {
      if (message.charAt(i) == tokenStr.charAt(j)) {
        j++;
      }

      if (j == 2) {
        j = 0;
        tokens++;
      }
    }

    this.tokens = tokens;
  }

  @Override
  public String replace(String... args) {
    if (args.length < tokens) {
      throw new IllegalArgumentException(
          "Expected " + tokens + " arguments, but found " + args.length);
    }

    return replaceAndColorize(args);
  }

  private String replaceAndColorize(String... args) {
    int last = 0;
    int tokens = 0;
    int[] positions = new int[2];
    StringBuilder newString = new StringBuilder();
    for (int i = 0, j = 0; i < message.length(); i++) {
      if (message.charAt(i) == tokenStr.charAt(j)) {
        positions[j] = i;
        j++;
      }

      if (j == positions.length) {
        if (args.length > tokens) {
          String tokenFormat = message.substring(positions[0] + 1, positions[1]);
          String segment = message.substring(last, positions[0]);
          String previousFormat = ChatColor.getLastColors(segment);
          newString.append(segment).append(ChatColor.RESET).append(tokenFormat).append(args[tokens])
              .append(ChatColor.RESET).append(previousFormat);
          last = i + 1;
        }

        j = 0;
        tokens++;
      }
    }

    newString.append(message, last, message.length());

    return newString.toString();
  }
}
