package com.snowypeaksystems.mobportals;

import com.snowypeaksystems.mobportals.mobs.IPortalMob;
import com.snowypeaksystems.mobportals.warps.IWarps;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * An object to provide access to other APIs.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public abstract class AbstractMobPortals extends JavaPlugin {
  /** Returns the IPortalMob for this LivingEntity if it exists, or null otherwise. */
  public abstract IPortalMob getPortalMob(LivingEntity entity);

  /** Returns the loaded warps. */
  public abstract IWarps getWarps();

  /** Returns the IMPPlayer object for this Player. */
  public abstract IMobPortalPlayer getPlayer(Player player);
}
