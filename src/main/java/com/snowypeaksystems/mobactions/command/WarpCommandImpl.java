package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.event.WarpCommandEvent;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import com.snowypeaksystems.mobactions.warp.Warp;
import com.snowypeaksystems.mobactions.warp.WarpManager;
import com.snowypeaksystems.mobactions.warp.WarpNotFoundException;
import org.bukkit.Bukkit;

/**
 * Implementation of WarpCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WarpCommandImpl implements WarpCommand {
  private final String warpName;
  private final WarpManager warpManager;

  /** Creates a warp command. */
  public WarpCommandImpl(String warp, WarpManager warpManager) {
    this.warpName = warp;
    this.warpManager = warpManager;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Warping player");
    if (!player.canUseWarpCommand() || !player.canUseWarp(warpName)) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (!warpManager.exists(warpName)) {
      DebugLogger.getLogger().log("Warp not found");
      throw new WarpNotFoundException(warpName);
    }

    Warp warp = warpManager.getWarp(warpName);
    WarpCommandEvent event = new WarpCommandEvent(player, warp);
    Bukkit.getPluginManager().callEvent(event);
    if (!event.isCancelled()) {
      player.teleport(warp.getDestination()).thenAccept(success -> {
        if (success) {
          player.sendMessage(gm("warp-success", warpName));
          DebugLogger.getLogger().log("Player warped");
        }
      });
    } else {
      DebugLogger.getLogger().log("Event cancelled");
    }
  }
}
