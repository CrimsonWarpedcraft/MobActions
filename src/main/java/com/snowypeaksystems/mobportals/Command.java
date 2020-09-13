package com.snowypeaksystems.mobportals;

/**
 * Stores a String command.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Command implements ICommand {
  private final int tokens;
  private final String name;
  private final String command;

  /** Constructs a command given a name and command to execute (use {} as player placeholder). */
  public Command(String name, String command) {
    this.name = name;
    this.command = command;

    int tokens = 0;

    for (int i = 0, j = 0; i < command.length(); i++) {
      if (command.charAt(i) == ICommand.TOKEN.charAt(j)) {
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
  public String getString(String... args) {
    return replace(args);
  }

  @Override
  public String getKey() {
    return command;
  }

  @Override
  public String getName() {
    return name;
  }

  private String replace(String... args) {
    if (tokens != args.length) {
      throw new IllegalArgumentException(
          "Expected " + args.length + " tokens, " + tokens + " found");
    }

    int last = 0;
    int tokens = 0;
    int[] positions = new int[2];
    StringBuilder newString = new StringBuilder();
    for (int i = 0, j = 0; i < command.length(); i++) {
      if (command.charAt(i) == ICommand.TOKEN.charAt(j)) {
        positions[j] = i;
        j++;
      }

      if (j == positions.length) {
        String segment = command.substring(last, positions[0]);

        newString.append(segment).append(args[tokens]);

        j = 0;
        last = i + 1;
        tokens++;
      }
    }

    newString.append(command, last, command.length());

    return newString.toString();
  }
}
