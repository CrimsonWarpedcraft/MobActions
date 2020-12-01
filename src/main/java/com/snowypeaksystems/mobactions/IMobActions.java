package com.snowypeaksystems.mobactions;

import com.snowypeaksystems.mobactions.player.IMobActionsPlayer;
import com.snowypeaksystems.mobactions.warp.IWarpManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Provides access to useful objects stored by this instance.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IMobActions {
  /** Returns the IWarpManager. */
  IWarpManager getWarpManager();

  /** Returns the IMobActionsPlayer object for the provided player. */
  IMobActionsPlayer getPlayer(Player player);

  /** Returns the IInteractiveMob for this non-Player entity. */
  IInteractiveMob getInteractiveMob(LivingEntity entity);
}
