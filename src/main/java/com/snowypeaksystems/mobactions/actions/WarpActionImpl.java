package com.snowypeaksystems.mobactions.actions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.InteractiveMob;
import com.snowypeaksystems.mobactions.data.WarpData;
import com.snowypeaksystems.mobactions.event.WarpInteractEvent;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import com.snowypeaksystems.mobactions.warp.Warp;
import com.snowypeaksystems.mobactions.warp.WarpManager;
import com.snowypeaksystems.mobactions.warp.WarpNotFoundException;
import org.bukkit.Bukkit;

/**
 * Implementation of WarpAction.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WarpActionImpl implements WarpAction {
  private final InteractiveMob mob;
  private final WarpData warpData;
  private final WarpManager warpManager;

  /** Creates a warp action. */
  public WarpActionImpl(InteractiveMob mob, WarpData data, WarpManager warpManager) {
    this.mob = mob;
    this.warpManager = warpManager;
    this.warpData = data;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Warping player");
    String warpName = warpData.getAlias();

    if (!player.canUseWarp(warpName)) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (!warpManager.exists(warpName)) {
      DebugLogger.getLogger().log("Warp not found");
      throw new WarpNotFoundException(warpName);
    }

    Warp warp = warpManager.getWarp(warpName);

    if (!callEvent(player, warp)) {
      player.teleport(warp.getDestination()).thenAccept(success -> {
        if (success) {
          player.sendMessage(gm("warp-success", warpName));
          DebugLogger.getLogger().log("Player warped");
        }
      });
    }
  }

  private boolean callEvent(MobActionsUser player, Warp warp) {
    WarpInteractEvent event = new WarpInteractEvent(player, mob, warp);
    Bukkit.getPluginManager().callEvent(event);
    return event.isCancelled();
  }
}
