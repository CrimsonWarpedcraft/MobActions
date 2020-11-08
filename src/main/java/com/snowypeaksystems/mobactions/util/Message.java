package com.snowypeaksystems.mobactions.util;

import org.bukkit.ChatColor;

/**
 * Implementation of IMessage.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Message implements Substitutable {
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
    return replaceAndColorize(args);
  }

  @Override
  public int getTokenCount() {
    return tokens;
  }

  private String replaceAndColorize(String... args) {
    if (tokens > args.length) {
      throw new IllegalArgumentException(
          "Expected at least" + tokens + " arguments, " + args.length + " found");
    }

    int last = 0;
    int tokens = 0;
    int[] positions = new int[2];
    StringBuilder newString = new StringBuilder();
    StringBuilder colors = new StringBuilder();
    for (int i = 0, j = 0; i < message.length(); i++) {
      if (message.charAt(i) == tokenStr.charAt(j)) {
        positions[j] = i;
        j++;
      }

      if (j == positions.length) {
        String segment = message.substring(last, positions[0]);
        colors.append(ChatColor.getLastColors(segment));

        String newColors = ChatColor.getLastColors(
            message.substring(positions[0] + 1, positions[1]));

        newString.append(segment).append(ChatColor.RESET)
            .append(newColors).append(args[tokens])
            .append(ChatColor.RESET).append(colors.toString());

        j = 0;
        last = i + 1;
        tokens++;
      }
    }

    newString.append(message, last, message.length());

    return newString.toString();
  }
}
