package com.snowypeaksystems.mobportals.mobs;

import static com.snowypeaksystems.mobportals.messages.Messages.gm;

import com.snowypeaksystems.mobportals.AbstractMobPortals;
import com.snowypeaksystems.mobportals.IMobCommand;
import com.snowypeaksystems.mobportals.MobCommand;
import com.snowypeaksystems.mobportals.exceptions.MobAlreadyExists;
import java.util.Collections;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

/**
 * Object that represents LivingEntities that store commands.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandMob implements ICommandMob {
  private final LivingEntity entity;
  private final NamespacedKey commandKey;
  private final NamespacedKey nameKey;

  /** Constructs a CommandMob from then given entity and AbstractMobPortals. */
  public CommandMob(LivingEntity entity, AbstractMobPortals mp) throws MobAlreadyExists {
    if (entity instanceof Player) {
      throw new IllegalArgumentException("Players may not be used as command mobs!");
    }

    if (!Collections.disjoint(entity.getPersistentDataContainer().getKeys(), mp.getKeys())) {
      throw new MobAlreadyExists("Mob already exists!");
    }

    this.entity = entity;
    this.nameKey = ICommandMob.getNameKey(mp);
    this.commandKey = ICommandMob.getCommandKey(mp);
  }

  @Override
  public void create(IMobCommand data) {
    entity.getPersistentDataContainer().set(nameKey, PersistentDataType.STRING, data.getName());
    entity.getPersistentDataContainer().set(commandKey, PersistentDataType.STRING, data.getKey());
    entity.setCustomName(gm("command-portal-text", data.getName()));
    entity.setCustomNameVisible(true);
    entity.setRemoveWhenFarAway(false);
  }

  @Override
  public IMobCommand destroy() {
    String name = entity.getPersistentDataContainer().get(nameKey, PersistentDataType.STRING);
    String rawCommand = entity.getPersistentDataContainer()
        .get(commandKey, PersistentDataType.STRING);

    if (name == null || rawCommand == null) {
      return null;
    }

    IMobCommand command = new MobCommand(name, rawCommand);

    entity.remove();

    return command;
  }

  @Override
  public IMobCommand getData() {
    String name = entity.getPersistentDataContainer().get(nameKey, PersistentDataType.STRING);
    String rawCommand = entity.getPersistentDataContainer()
        .get(commandKey, PersistentDataType.STRING);

    if (name == null || rawCommand == null) {
      return null;
    }

    return new MobCommand(name, rawCommand);
  }

  @Override
  public boolean hasData() {
    return getData() != null;
  }

  @Override
  public LivingEntity getLivingEntity() {
    return entity;
  }
}
