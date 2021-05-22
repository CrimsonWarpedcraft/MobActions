package com.snowypeaksystems.mobactions.actions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.AbstractMobActions;
import com.snowypeaksystems.mobactions.data.CommandData;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.data.WarpData;
import com.snowypeaksystems.mobactions.mobevent.UnsupportedMobEventMobDataException;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import com.snowypeaksystems.mobactions.warp.Warp;
import com.snowypeaksystems.mobactions.warp.WarpNotFoundException;

/**
 * Implementation of EventMobStartAction.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventMobStartActionImpl implements EventMobStartAction {
  private final MobData data;
  private final AbstractMobActions ma;

  /** Create action for CommandData or WarpData to run once an event has started. */
  public EventMobStartActionImpl(MobData data, AbstractMobActions ma) {
    if (!(data instanceof WarpData) && !(data instanceof CommandData)) {
      throw new UnsupportedMobEventMobDataException();
    }

    this.data = data;
    this.ma = ma;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    if (data instanceof WarpData) {
      DebugLogger.getLogger().log("Warping player");
      String warpName = ((WarpData) data).getAlias();

      if (!ma.getWarpManager().exists(warpName)) {
        DebugLogger.getLogger().log("Warp not found");
        throw new WarpNotFoundException(warpName);
      }

      Warp warp = ma.getWarpManager().getWarp(warpName);

      player.teleport(warp.getDestination()).thenAccept(success -> {
        if (success) {
          player.sendMessage(gm("warp-success", warpName));
          DebugLogger.getLogger().log("Player warped");
        }
      });

    } else if (data instanceof CommandData) {
      DebugLogger.getLogger().log("Executing command");
      String commandStr = ((CommandData) data).getCommand(player.getName());
      DebugLogger.getLogger().log("Command: " + commandStr);
      boolean isConsoleCommand = ((CommandData) data).isConsoleCommand();

      MobActionsUser user;
      if (isConsoleCommand) {
        user = ma.getPlayer(ma.getServer().getConsoleSender());
      } else {
        user = player;
      }

      String[] commandList = commandStr.split("(?<!\\\\);");
      for (String command : commandList) {
        if (!user.performCommand(command.trim())) {
          DebugLogger.getLogger().log("Command execution failed");
          throw new CommandActionException();
        }
      }

      DebugLogger.getLogger().log("Command executed");

    } else {
      // A bit unnecessary, but could prove useful, so why not?
      throw new UnsupportedMobEventMobDataException();
    }
  }
}
