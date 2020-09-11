package com.snowypeaksystems.mobportals.mobs;

import static com.snowypeaksystems.mobportals.messages.Messages.gm;

import com.snowypeaksystems.mobportals.MobPortals;
import com.snowypeaksystems.mobportals.warps.IWarp;
import com.snowypeaksystems.mobportals.warps.IWarps;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

/**
 * An implementation of an IPortalMob that stores an IWarp in a LivingEntity.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class PortalMob implements IPortalMob {
  private final IWarps warps;
  private final NamespacedKey key;
  private final LivingEntity entity;

  /**
   * Constructs a new PortalMob from an entity and plugin.
   * @param entity the non-Player LivingEntity
   * @param mp the MobPortals instance
   * @throws IllegalArgumentException if entity is an instance of Player
   */
  public PortalMob(LivingEntity entity, MobPortals mp) {
    if (entity instanceof Player) {
      throw new IllegalArgumentException("Players may not be used as portals!");
    }

    this.entity = entity;
    this.warps = mp.getWarps();
    this.key = IPortalMob.getKey(mp);
  }

  @Override
  public synchronized void create(IWarp warp) {
    entity.getPersistentDataContainer().set(key, PersistentDataType.STRING, warp.getKey());
    entity.setCustomName(gm("mob-nametag-text", warp.getName()));
    entity.setCustomNameVisible(true);
    entity.setRemoveWhenFarAway(false);
  }

  @Override
  public synchronized IWarp destroy() {
    IWarp warp = warps.get(entity.getPersistentDataContainer().get(key, PersistentDataType.STRING));

    if (warp != null) {
      entity.getPersistentDataContainer().remove(key);
      entity.setCustomName(null);
      entity.setCustomNameVisible(false);
      entity.setRemoveWhenFarAway(true);
    }

    return warp;
  }

  @Override
  public synchronized IWarp getData() {
    return warps.get(entity.getPersistentDataContainer().get(key, PersistentDataType.STRING));
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
