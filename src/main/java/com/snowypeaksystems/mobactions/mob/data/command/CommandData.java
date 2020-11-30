package com.snowypeaksystems.mobactions.mob.data.command;

/**
 * Stores a String command.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandData implements ICommandData {
  private final int tokens;
  private final String name;
  private final String command;
  private final String tokenStr;

  /** Constructs a command given a name and command to execute. */
  public CommandData(String name, String command) {
    this.name = name;
    this.command = command;
    this.tokenStr = String.valueOf(new char[]{TOKEN_PREFIX, TOKEN_SUFFIX});

    int tokens = 0;

    for (int i = 0, j = 0; i < command.length(); i++) {
      if (command.charAt(i) == tokenStr.charAt(j)) {
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
    int last = 0;
    int tokens = 0;
    int[] positions = new int[2];
    StringBuilder newString = new StringBuilder();

    for (int i = 0, j = 0; i < command.length(); i++) {
      if (command.charAt(i) == tokenStr.charAt(j)) {
        positions[j] = i;
        j++;
      }

      if (j == positions.length) {
        if (args.length > tokens) {
          String segment = command.substring(last, positions[0]);
          newString.append(segment).append(args[tokens]);
          last = i + 1;
        }

        j = 0;
        tokens++;
      }
    }

    newString.append(command, last, command.length());

    return newString.toString();
  }

  @Override
  public int getTokenCount() {
    return tokens;
  }

  @Override
  public String getAlias() {
    return name;
  }
}
