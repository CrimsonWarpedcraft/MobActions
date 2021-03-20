package com.snowypeaksystems.mobactions.actions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.AMobActions;
import com.snowypeaksystems.mobactions.data.ConsoleCommandData;
import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.data.IWarpData;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.mobevent.UnsupportedMobEventMobDataException;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import com.snowypeaksystems.mobactions.warp.IWarp;
import com.snowypeaksystems.mobactions.warp.WarpNotFoundException;

public class EventMobStartAction implements IEventMobStartAction {
  private final MobData data;
  private final AMobActions ma;

  /** Create action for ICommandData or IWarpData to run once an event has started. */
  public EventMobStartAction(MobData data, AMobActions ma) {
    if (!(data instanceof IWarpData) && !(data instanceof ICommandData)) {
      throw new UnsupportedMobEventMobDataException();
    }

    this.data = data;
    this.ma = ma;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    if (data instanceof IWarpData) {
      DebugLogger.getLogger().log("Warping player");
      String warpName = ((IWarpData) data).getAlias();

      if (!ma.getWarpManager().exists(warpName)) {
        DebugLogger.getLogger().log("Warp not found");
        throw new WarpNotFoundException(warpName);
      }

      IWarp warp = ma.getWarpManager().getWarp(warpName);

      player.teleport(warp.getDestination()).thenAccept(success -> {
        if (success) {
          player.sendMessage(gm("warp-success", warpName));
          DebugLogger.getLogger().log("Player warped");
        }
      });

    } else if (data instanceof ICommandData) {
      DebugLogger.getLogger().log("Executing command");
      String commandStr = ((ICommandData) data).getCommand(player.getName());
      DebugLogger.getLogger().log("Command: " + commandStr);
      boolean isConsoleCommand = data instanceof ConsoleCommandData
          && ((ConsoleCommandData) data).isConsoleCommand();

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
