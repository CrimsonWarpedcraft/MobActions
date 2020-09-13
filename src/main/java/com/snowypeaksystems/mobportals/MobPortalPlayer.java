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
  private final HashMap<Player, Creation> editors;

  MobPortalPlayer(Player player, HashMap<Player, Creation> editors) {
    this.player = player;
    this.editors = editors;
  }

  @Override
  public synchronized void setCreation(Type mode, IMobWritable data) {
    if (mode != Type.NONE) {
      editors.put(player, new Creation(mode, data));
    } else if (isCreating()) {
      editors.remove(player);
    }
  }

  @Override
  public synchronized IMobWritable getCreation() {
    return editors.get(player).creation;
  }

  @Override
  public Type getCreationType() {
    if (!isCreating()) {
      return Type.NONE;
    }

    return editors.get(player).mode;
  }

  @Override
  public synchronized boolean isCreating() {
    return editors.containsKey(player) && editors.get(player).mode != Type.NONE;
  }

  @Override
  public synchronized void setDestroying(boolean value) {
    if (value) {
      editors.put(player, new Creation(Type.NONE, null));
    } else if (isDestroying()) {
      editors.remove(player);
    }
  }

  @Override
  public synchronized boolean isDestroying() {
    return editors.containsKey(player) && editors.get(player).mode == Type.NONE;
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

  static class Creation {
    private final Type mode;
    private final IMobWritable creation;

    public Creation(Type mode, IMobWritable creation) {
      this.mode = mode;
      this.creation = creation;
    }
  }
}
