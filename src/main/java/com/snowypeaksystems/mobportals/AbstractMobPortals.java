package com.snowypeaksystems.mobportals;

import com.snowypeaksystems.mobportals.exceptions.MobAlreadyExists;
import com.snowypeaksystems.mobportals.mobs.IMob;
import com.snowypeaksystems.mobportals.persistence.IMobWritable;
import com.snowypeaksystems.mobportals.warps.IWarps;
import java.util.Set;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * An object to provide access to other APIs.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public abstract class AbstractMobPortals extends JavaPlugin {
  /**
   * Returns the IMob for this LivingEntity if it exists, or null otherwise.
   * @param entity the entity for the IMob
   * @return The IMob associated with this object if it exists, or null otherwise
   * @throws MobAlreadyExists if a mob with more than one configuration is specified
   */
  public abstract IMob<? extends IMobWritable> getMob(LivingEntity entity) throws MobAlreadyExists;

  /** Returns the loaded warps. */
  public abstract IWarps getWarps();

  /** Returns the available keys. */
  public abstract Set<NamespacedKey> getKeys();

  /** Returns the IMPPlayer object for this Player. */
  public abstract IMobPortalPlayer getPlayer(Player player);
}
