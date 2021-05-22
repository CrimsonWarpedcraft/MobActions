package com.snowypeaksystems.mobactions;

import com.snowypeaksystems.mobactions.data.IncompleteDataException;
import com.snowypeaksystems.mobactions.mobevent.MobEventManager;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.warp.WarpManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Provides access to useful objects stored by this instance.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface MobActionsInterface {
  /** Returns the WarpManager. */
  WarpManager getWarpManager();

  /** Returns the MobActionsUser object for the provided Player. */
  MobActionsUser getPlayer(Player player);

  /** Returns the MobsActionsUser object for the provided CommandSender. */
  MobActionsUser getPlayer(CommandSender sender);

  /** Returns the InteractiveMob for this non-Player entity. */
  InteractiveMob getInteractiveMob(LivingEntity entity) throws IncompleteDataException;

  MobEventManager getMobEventManager();
}
