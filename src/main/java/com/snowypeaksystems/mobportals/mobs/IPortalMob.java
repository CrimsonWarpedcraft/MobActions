package com.snowypeaksystems.mobportals.mobs;

import com.snowypeaksystems.mobportals.warps.IWarp;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Any mob that may be a portal mob.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IPortalMob extends IMob<IWarp> {
  /** Returns the key used for this IMob's persistent storage. */
  static NamespacedKey getKey(JavaPlugin plugin) {
    return new NamespacedKey(plugin, "portalDest");
  }
}
