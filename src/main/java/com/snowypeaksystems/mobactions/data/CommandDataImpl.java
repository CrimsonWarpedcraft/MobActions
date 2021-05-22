package com.snowypeaksystems.mobactions.data;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Implementation of CommandData.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandDataImpl implements CommandData {
  private final String command;
  private final String description;
  private final Boolean isConsoleCommand;
  private final String tokenStr = String.valueOf(new char[]{TOKEN_PREFIX, TOKEN_SUFFIX});

  /** Constructs CommandData from an entity. */
  public CommandDataImpl(LivingEntity entity, JavaPlugin plugin) throws IncompleteDataException {
    PersistentDataContainer container = entity.getPersistentDataContainer();
    NamespacedKey commandKey = new NamespacedKey(plugin, COMMAND_KEY);
    NamespacedKey descriptionKey = new NamespacedKey(plugin, COMMAND_DESCRIPTION_KEY);
    NamespacedKey consoleKey = new NamespacedKey(plugin, CONSOLE_COMMAND_KEY);

    if (!container.has(commandKey, PersistentDataType.STRING)
        || !container.has(descriptionKey, PersistentDataType.STRING)) {
      throw new IncompleteDataException();
    }

    // This is to upgrade legacy mobs without causing an error
    if (!container.has(consoleKey, PersistentDataType.INTEGER)) {
      container.set(consoleKey, PersistentDataType.INTEGER, 0);
    }

    Integer consoleNum = container.get(consoleKey, PersistentDataType.INTEGER);
    this.command = container.get(commandKey, PersistentDataType.STRING);
    this.description = container.get(descriptionKey, PersistentDataType.STRING);
    this.isConsoleCommand = consoleNum != null && consoleNum == 1;
  }

  /** Constructs a command given a command to execute. */
  public CommandDataImpl(String command) {
    this(command, null, false);
  }

  /** Constructs a command given a command to execute and boolean to toggle console command. */
  public CommandDataImpl(String command, boolean isConsoleCommand) {
    this(command, null, isConsoleCommand);
  }

  /** Constructs a command given a command and description. */
  public CommandDataImpl(String command, String description) {
    this(command, description, false);
  }

  /** Constructs a command given a command, description, and boolean to toggle console command. */
  public CommandDataImpl(String command, String description, boolean isConsoleCommand) {
    this.command = command;
    this.isConsoleCommand = isConsoleCommand;

    if (description != null && description.length() > 0) {
      this.description = description;
    } else {
      this.description = "\"" + command + "\"";
    }
  }

  @Override
  public String getCommand(String name) {
    int start = 0;
    int[] positions = new int[2];
    StringBuilder newString = new StringBuilder();

    for (int i = 0, j = 0; i < command.length(); i++) {
      if (command.charAt(i) == tokenStr.charAt(j) && (i == 0 || command.charAt(i - 1) != '\\'
          || (i >= 2 && command.charAt(i - 2) == '\\'))) {
        positions[j] = i;
        j++;
      }

      if (j == positions.length) {
        int end = positions[0];
        if (positions[0] >= 2 && command.startsWith("\\\\", positions[0] - 2)) {
          end = positions[0] - 1;
        }
        String segment = command.substring(start, end).replaceAll("(?<!\\\\)\\\\\\{", "{")
            .replaceAll("(?<!\\\\)\\\\}", "}");
        newString.append(segment).append(name);
        start = i + 1;
        j = 0;
      }
    }

    newString.append(command.substring(start).replaceAll("(?<!\\\\)\\\\\\{", "{")
        .replaceAll("(?<!\\\\)\\\\}", "}"));

    return newString.toString();
  }

  @Override
  public void store(LivingEntity entity, JavaPlugin plugin) {
    entity.getPersistentDataContainer()
        .set(new NamespacedKey(plugin, COMMAND_KEY), PersistentDataType.STRING, command);
    entity.getPersistentDataContainer()
        .set(new NamespacedKey(plugin, COMMAND_DESCRIPTION_KEY),
            PersistentDataType.STRING, description);
    entity.getPersistentDataContainer()
        .set(new NamespacedKey(plugin, CONSOLE_COMMAND_KEY),
            PersistentDataType.INTEGER, isConsoleCommand ? 1 : 0);
  }

  @Override
  public void purge(LivingEntity entity, JavaPlugin plugin) {
    entity.getPersistentDataContainer().remove(new NamespacedKey(plugin, COMMAND_KEY));
    entity.getPersistentDataContainer().remove(new NamespacedKey(plugin, COMMAND_DESCRIPTION_KEY));
    entity.getPersistentDataContainer().remove(new NamespacedKey(plugin, CONSOLE_COMMAND_KEY));
  }

  @Override
  public String getKeyString() {
    return COMMAND_KEY;
  }

  @Override
  public String getNametagString() {
    return gm("nametag-command-text", description);
  }

  @Override
  public String toString() {
    return command;
  }

  @Override
  public boolean isConsoleCommand() {
    return isConsoleCommand;
  }
}
