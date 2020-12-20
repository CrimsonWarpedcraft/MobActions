package com.snowypeaksystems.mobactions.player;

import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.warp.IWarp;
import io.papermc.lib.PaperLib;
import java.util.concurrent.CompletableFuture;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Player based implementation of MobActionsUser.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MobActionsPlayer implements MobActionsUser {
  private final Player player;
  private final IStatus status;

  public MobActionsPlayer(Player player) {
    this.player = player;
    this.status = new Status();
  }

  @Override
  public IStatus getStatus() {
    return status;
  }

  @Override
  public CompletableFuture<Boolean> teleport(Location location) {
    return PaperLib.teleportAsync(player, location);
  }

  @Override
  public void sendMessage(String... messages) {
    player.sendMessage(messages);
  }

  @Override
  public boolean performCommand(String command) {
    return false;
  }

  @Override
  public Location getLocation() {
    return player.getLocation();
  }

  @Override
  public String getDisplayName() {
    return player.getDisplayName();
  }

  @Override
  public boolean canUseWarp(IWarp warp) {
    return player.hasPermission("mobactions.warp.*")
        || player.hasPermission("mobactions.warp." + warp.getAlias());
  }

  @Override
  public boolean canRunCommand(ICommandData command) {
    return player.hasPermission("mobactions.command.*")
        || player.hasPermission("mobactions.command." + command.getAlias());
  }

  @Override
  public boolean canCreate() {
    return player.hasPermission("mobactions.admin.create");
  }

  @Override
  public boolean canRemove() {
    return player.hasPermission("mobactions.admin.remove");
  }

  @Override
  public boolean canUseWarpCommand() {
    return player.hasPermission("mobactions.warp");
  }

  @Override
  public boolean canSetWarp() {
    return player.hasPermission("mobactions.admin.setwarp");
  }

  @Override
  public boolean canRemoveWarp() {
    return player.hasPermission("mobactions.admin.delwarp");
  }

  @Override
  public boolean canReload() {
    return player.hasPermission("mobactions.admin.reload");
  }
}
