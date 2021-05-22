package com.snowypeaksystems.mobactions;

import com.snowypeaksystems.mobactions.data.IncompleteDataException;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Implementation of InteractiveMob.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class InteractiveMobImpl implements InteractiveMob {
  private final JavaPlugin plugin;
  private final LivingEntity entity;
  private MobData data;

  InteractiveMobImpl(LivingEntity entity, JavaPlugin plugin) throws IncompleteDataException {
    if (entity instanceof Player) {
      throw new IllegalArgumentException("The entity cannot be a player");
    }
    
    this.entity = entity;
    this.plugin = plugin;

    String key = entity.getPersistentDataContainer()
        .get(new NamespacedKey(plugin, DATA_KEY), PersistentDataType.STRING);

    if (key != null) {
      if (MobData.DATA_KEY_MAP.containsKey(key)) {
        try {
          Constructor<? extends MobData> c = MobData.DATA_KEY_MAP.get(key)
              .getConstructor(LivingEntity.class, JavaPlugin.class);
          data = c.newInstance(entity, plugin);
        } catch (NoSuchMethodException e) {
          Bukkit.getLogger().log(Level.WARNING, "Unrecognized data key found", e.getCause());
        } catch (InvocationTargetException e) {
          if (e.getCause() instanceof IncompleteDataException) {
            throw (IncompleteDataException) e.getCause();
          } else {
            DebugLogger.getLogger().log("Error running constructor");
            Bukkit.getLogger().log(Level.SEVERE, "Error running constructor", e.getCause());
          }
        } catch (IllegalAccessException | InstantiationException e) {
          DebugLogger.getLogger().log("Error creating mob from recognized class");
          Bukkit.getLogger().log(Level.SEVERE, "Error creating mob from recognized class",
              e.getCause());
        }
      }
    }
  }

  @Override
  public void store() {
    if (data != null) {
      if (!exists()) {
        int doRemove = entity.getRemoveWhenFarAway() ? 1 : 0;
        entity.getPersistentDataContainer().set(
            new NamespacedKey(plugin, REMOVE_DEFAULT_KEY), PersistentDataType.INTEGER, doRemove);
        DebugLogger.getLogger().log("Original despawn setting: " + (doRemove == 1));
      }

      entity.getPersistentDataContainer()
          .set(new NamespacedKey(plugin, DATA_KEY), PersistentDataType.STRING, data.getKeyString());

      data.store(entity, plugin);

      entity.setRemoveWhenFarAway(false);
      entity.setCustomNameVisible(true);
      entity.setCustomName(data.getNametagString());
    }
  }

  @Override
  public void purge() {
    Integer removeDefault = entity.getPersistentDataContainer().get(
        new NamespacedKey(plugin, REMOVE_DEFAULT_KEY), PersistentDataType.INTEGER);
    if (removeDefault != null) {
      boolean original = removeDefault == 1;
      entity.setRemoveWhenFarAway(original);
      DebugLogger.getLogger().log("Reset original despawn setting: " + original);
    }

    entity.setCustomNameVisible(false);
    entity.setCustomName(null);

    entity.getPersistentDataContainer().remove(new NamespacedKey(plugin, DATA_KEY));
    entity.getPersistentDataContainer().remove(new NamespacedKey(plugin, REMOVE_DEFAULT_KEY));

    if (data != null) {
      data.purge(entity, plugin);
    }
  }

  @Override
  public boolean exists() {
    return entity.getPersistentDataContainer()
        .has(new NamespacedKey(plugin, DATA_KEY), PersistentDataType.STRING);
  }

  @Override
  public MobData getData() {
    return data;
  }

  @Override
  public void setData(MobData data) {
    this.data = data;
  }
}
