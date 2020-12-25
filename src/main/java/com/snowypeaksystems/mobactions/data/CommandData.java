package com.snowypeaksystems.mobactions.data;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Stores a String command.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandData implements ICommandData {
  private final String name;
  private final String command;
  private final String description;
  private final String tokenStr = String.valueOf(new char[]{TOKEN_PREFIX, TOKEN_SUFFIX});

  /** Constructs CommandData from an entity. */
  public CommandData(LivingEntity entity, JavaPlugin plugin) {
    PersistentDataContainer container = entity.getPersistentDataContainer();

    this.name = container.get(
        new NamespacedKey(plugin, COMMAND_ALIAS_KEY), PersistentDataType.STRING);
    this.command = container.get(new NamespacedKey(plugin, COMMAND_KEY), PersistentDataType.STRING);
    this.description = container.get(
        new NamespacedKey(plugin, COMMAND_DESCRIPTION_KEY), PersistentDataType.STRING);
  }

  /** Constructs a command given a name and command to execute. */
  public CommandData(String name, String command, String description) {
    this.name = name;
    this.command = command;
    this.description = description;
  }

  @Override
  public String getCommand(String name) {
    int last = 0;
    int[] positions = new int[2];
    StringBuilder newString = new StringBuilder();

    for (int i = 0, j = 0; i < command.length(); i++) {
      if (command.charAt(i) == tokenStr.charAt(j)) {
        positions[j] = i;
        j++;
      }

      if (j == positions.length) {
        String segment = command.substring(last, positions[0]);
        newString.append(segment).append(name);
        last = i + 1;
        j = 0;
      }
    }

    newString.append(command, last, command.length());

    return newString.toString();
  }

  @Override
  public String getAlias() {
    return name;
  }

  @Override
  public void store(LivingEntity entity, JavaPlugin plugin) {
    entity.getPersistentDataContainer()
        .set(new NamespacedKey(plugin, COMMAND_KEY), PersistentDataType.STRING, command);
    entity.getPersistentDataContainer()
        .set(new NamespacedKey(plugin, COMMAND_ALIAS_KEY), PersistentDataType.STRING, name);
    entity.getPersistentDataContainer()
        .set(new NamespacedKey(plugin, COMMAND_DESCRIPTION_KEY),
            PersistentDataType.STRING, description);
  }

  @Override
  public void purge(LivingEntity entity, JavaPlugin plugin) {
    entity.getPersistentDataContainer().remove(new NamespacedKey(plugin, COMMAND_KEY));
    entity.getPersistentDataContainer().remove(new NamespacedKey(plugin, COMMAND_ALIAS_KEY));
    entity.getPersistentDataContainer().remove(new NamespacedKey(plugin, COMMAND_DESCRIPTION_KEY));
  }

  @Override
  public String getKeyString() {
    return COMMAND_KEY;
  }

  @Override
  public String getNametagString() {
    return gm("nametag-command-text", description);
  }
}
