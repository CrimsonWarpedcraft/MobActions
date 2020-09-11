package com.snowypeaksystems.mobportals;

import static com.snowypeaksystems.mobportals.messages.Messages.gm;

import com.snowypeaksystems.mobportals.persistence.IMobWritable;
import com.snowypeaksystems.mobportals.warps.IWarp;
import io.papermc.lib.PaperLib;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import org.bukkit.entity.Player;

/**
 * Implementation of IMPPlayer.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MobPortalPlayer implements IMobPortalPlayer {
  private final Player player;
  private final HashMap<Player, IMobWritable> editors;

  MobPortalPlayer(Player player, HashMap<Player, IMobWritable> editors) {
    this.player = player;
    this.editors = editors;
  }

  @Override
  public synchronized void setCreation(IMobWritable data) {
    if (data != null) {
      editors.put(player, data);
    } else if (isCreating()) {
      editors.remove(player);
    }
  }

  @Override
  public synchronized IMobWritable getCreation() {
    return editors.get(player);
  }

  @Override
  public synchronized boolean isCreating() {
    return editors.containsKey(player) && getCreation() != null;
  }

  @Override
  public synchronized void setDestroying(boolean value) {
    if (value) {
      editors.put(player, null);
    } else if (isDestroying()) {
      editors.remove(player);
    }
  }

  @Override
  public synchronized boolean isDestroying() {
    return editors.containsKey(player) && getCreation() == null;
  }

  @Override
  public Player getPlayer() {
    return player;
  }

  @Override
  public void warp(IWarp warp) {
    CompletableFuture<Boolean> future = new CompletableFuture<>();
    future.thenAccept(success -> {
      if (success) {
        player.sendMessage(gm("warp-success", warp.getName()));
      }
    });

    warp(warp, future);
  }

  @Override
  public void warp(IWarp warp, CompletableFuture<Boolean> future) {
    PaperLib.getChunkAtAsync(warp.getDestination())
        .thenAccept(chunk -> future.complete(player.teleport(warp.getDestination())));
  }
}
