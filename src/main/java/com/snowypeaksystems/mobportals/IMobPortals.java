package com.snowypeaksystems.mobportals;

import com.snowypeaksystems.mobportals.mobs.IPortalMob;
import com.snowypeaksystems.mobportals.warps.IWarps;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * An object to provide access to other APIs.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IMobPortals {
  /** Returns the IPortalMob for this LivingEntity if it exists, or null otherwise. */
  IPortalMob getPortalMob(LivingEntity entity);

  /** Returns the loaded warps. */
  IWarps getWarps();

  /** Returns the IMPPlayer object for this Player. */
  IMobPortalPlayer getPlayer(Player player);
}
