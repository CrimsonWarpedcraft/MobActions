package com.snowypeaksystems.mobactions.data;

import static java.util.Map.entry;

import java.util.Map;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Data that can be stored by a Mob.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface MobData {
  /** Stores the data on the entity. */
  void store(LivingEntity entity, JavaPlugin plugin);

  /** Remotes the data from the entity. */
  void purge(LivingEntity entity, JavaPlugin plugin);

  /** Returns the key String for the type of data. */
  String getKeyString();

  /** Returns the String to be used as instructions for this data. */
  String getNametagString();

  Map<String, Class<? extends MobData>> DATA_KEY_MAP = Map.ofEntries(
      entry(CommandData.COMMAND_KEY, CommandDataImpl.class),
      entry(WarpData.WARP_KEY, WarpDataImpl.class),
      entry(EventData.EVENT_KEY, EventDataImpl.class));
}
