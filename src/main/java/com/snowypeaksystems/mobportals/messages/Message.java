package com.snowypeaksystems.mobportals.messages;

import org.bukkit.ChatColor;

/**
 * Implementation of IMessage.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Message implements IMessage {
  private final String message;
  private final String token;

  public Message(String message) {
    this(message, null);
  }

  public Message(String message, String token) {
    this.message = message;
    this.token = token;
  }

  @Override
  public String getMessage(String... args) {
    return replaceAndColorize(args);
  }

  private String replaceAndColorize(String... args) {
    if (args.length == 0 || token == null) {
      return message;
    }

    int i = 0;
    int last = 0;
    int tokens = 0;
    int[] positions = new int[2];
    StringBuilder sb = new StringBuilder();

    for (int j = 0; i < message.length(); i++) {
      if (j == positions.length) {
        if (tokens == args.length) {
          throw new IllegalArgumentException("Number of arguments must match number of tokens");
        }

        String segment = message.substring(last, positions[0]);
        String lastColors = ChatColor.getLastColors(segment);


        String newColors = ChatColor.getLastColors(
            message.substring(positions[0] + 1, positions[1]));

        sb.append(segment).append(ChatColor.RESET)
            .append(newColors).append(args[tokens])
            .append(ChatColor.RESET).append(lastColors);

        j = 0;
        last = i;
        tokens++;
      }

      if (message.charAt(i) == token.charAt(j)) {
        positions[j] = i;
        j++;
      }
    }

    if (tokens != args.length) {
      throw new IllegalArgumentException("Number of arguments must match number of tokens");
    }

    sb.append(message, last, i);
    return sb.toString();
  }
}
