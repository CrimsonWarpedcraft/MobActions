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
      if (message.charAt(i) == tokenStr.charAt(j) && (i == 0 || message.charAt(i - 1) != '\\'
          || (i >= 2 && message.charAt(i - 2) == '\\'))) {
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
    int start = 0;
    int tokens = 0;
    int[] positions = new int[2];
    StringBuilder newString = new StringBuilder();
    StringBuilder formatCodes = new StringBuilder();
    for (int i = 0, j = 0; i < message.length(); i++) {
      if (message.charAt(i) == tokenStr.charAt(j) && (i == 0 || message.charAt(i - 1) != '\\'
          || (i >= 2 && message.charAt(i - 2) == '\\'))) {
        positions[j] = i;
        j++;
      }

      if (j == positions.length) {
        if (args.length > tokens) {
          int end = positions[0];
          if (positions[0] >= 2 && message.startsWith("\\\\", positions[0] - 2)) {
            end = positions[0] - 1;
          }
          String segment = message.substring(start, end)
              .replaceAll("(?<!\\\\)\\\\\\{", "{").replaceAll("(?<!\\\\)\\\\}", "}");
          formatCodes.append(ChatColor.getLastColors(segment));
          newString.append(segment);
          String tokenFormat = message.substring(positions[0] + 1, positions[1]);
          String formatted = ChatColor.translateAlternateColorCodes('&', args[tokens]);
          if (tokenFormat.length() > 0) {
            newString.append(ChatColor.RESET).append(tokenFormat).append(formatted)
                .append(ChatColor.RESET).append(formatCodes.toString());
          } else {
            newString.append(formatted);
          }

          start = i + 1;
        }

        j = 0;
        tokens++;
      }
    }

    newString.append(message.substring(start).replaceAll("(?<!\\\\)\\\\\\{", "{")
        .replaceAll("(?<!\\\\)\\\\}", "}"));

    return newString.toString();
  }
}
