package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.event.SetWarpEvent;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.player.WarpCreateException;
import com.snowypeaksystems.mobactions.player.WarpExistsException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import com.snowypeaksystems.mobactions.warp.IWarpManager;
import java.io.IOException;
import org.bukkit.Bukkit;

public class SetWarpCommand implements ISetWarpCommand {
  private final IWarpManager warpManager;
  private final String name;

  /** Creates a SetWarpCommand object. */
  public SetWarpCommand(String name, IWarpManager warpManager) {
    this.name = name;
    this.warpManager = warpManager;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Setting warp");
    if (!player.canSetWarp()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    if (warpManager.exists(name)) {
      DebugLogger.getLogger().log("Warp already exists");
      throw new WarpExistsException(name);
    }

    SetWarpEvent event = new SetWarpEvent(player, name, player.getLocation());
    Bukkit.getPluginManager().callEvent(event);
    if (!event.isCancelled()) {
      try {
        warpManager.makeWarp(name, player.getLocation());
        player.sendMessage(gm("warp-create-success", name));
      } catch (IOException e) {
        DebugLogger.getLogger().log("Warp save error");
        throw new WarpCreateException();
      }

      DebugLogger.getLogger().log("Warp set");
    } else {
      DebugLogger.getLogger().log("Event cancelled");
    }
  }
}
