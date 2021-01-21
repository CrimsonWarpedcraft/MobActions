package com.snowypeaksystems.mobactions;

import com.snowypeaksystems.mobactions.data.IncompleteDataException;
import com.snowypeaksystems.mobactions.mobevent.IMobEventManager;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.warp.IWarpManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Provides access to useful objects stored by this instance.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IMobActions {
  /** Returns the IWarpManager. */
  IWarpManager getWarpManager();

  /** Returns the MobActionsUser object for the provided Player. */
  MobActionsUser getPlayer(Player player);

  /** Returns the MobsActionsUser object for the provided CommandSender. */
  MobActionsUser getPlayer(CommandSender sender);

  /** Returns the IInteractiveMob for this non-Player entity. */
  IInteractiveMob getInteractiveMob(LivingEntity entity) throws IncompleteDataException;

  IMobEventManager getMobEventManager();
}
