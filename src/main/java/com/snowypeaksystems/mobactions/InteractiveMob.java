package com.snowypeaksystems.mobactions;

import com.snowypeaksystems.mobactions.data.CommandData;
import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.data.IWarpData;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.data.WarpData;
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
    entity.getPersistentDataContainer()
        .set(new NamespacedKey(plugin, DATA_KEY), PersistentDataType.STRING, data.getKeyString());

    data.store(entity, plugin);
  }

  @Override
  public void purge() {
    entity.getPersistentDataContainer().remove(new NamespacedKey(plugin, DATA_KEY));

    data.purge(entity, plugin);
  }

  @Override
  public boolean exists() {
    return entity.getPersistentDataContainer()
        .has(new NamespacedKey(plugin, DATA_KEY), PersistentDataType.STRING);
  }

  @Override
  public LivingEntity getLivingEntity() {
    return entity;
  }

  @Override
  public String getActionType() {
    return entity.getPersistentDataContainer()
        .get(new NamespacedKey(plugin, DATA_KEY), PersistentDataType.STRING);
  }
}
