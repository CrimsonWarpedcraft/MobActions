package com.snowypeaksystems.mobactions;

import com.snowypeaksystems.mobactions.data.CommandData;
import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.data.IWarpData;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.data.WarpData;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class InteractiveMob implements IInteractiveMob {
  private final JavaPlugin plugin;
  private final LivingEntity entity;
  private MobData data;

  InteractiveMob(LivingEntity entity, JavaPlugin plugin) {
    if (entity instanceof Player) {
      throw new IllegalArgumentException("The entity cannot be a player");
    }
    
    this.entity = entity;
    this.plugin = plugin;

    if (exists()) {
      String key = entity.getPersistentDataContainer()
          .get(new NamespacedKey(plugin, DATA_KEY), PersistentDataType.STRING);

      if (key != null) {
        if (key.equals(ICommandData.COMMAND_KEY)) {
          data = new CommandData(entity, plugin);
        } else if (key.equals(IWarpData.WARP_KEY)) {
          data = new WarpData(entity, plugin);
        }
      }
    }
  }

  @Override
  public void store() {
    if (data != null) {
      if (!exists()) {
        int doRemove = entity.getRemoveWhenFarAway() ? 1 : 0;
        entity.getPersistentDataContainer()
            .set(new NamespacedKey(plugin, REMOVE_DEFAULT_KEY), PersistentDataType.INTEGER, doRemove);
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
      DebugLogger.getLogger().log("Reset original depspawn setting: " + original);
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
