package com.snowypeaksystems.mobactions.player;

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
    return player.performCommand(command);
  }

  @Override
  public Location getLocation() {
    return player.getLocation();
  }

  @Override
  public String getName() {
    return player.getName();
  }

  @Override
  public boolean canUseWarp(String warp) {
    return player.hasPermission("mobactions.warp.*")
        || player.hasPermission("mobactions.warp." + warp);
  }

  @Override
  public boolean canRunCommand() {
    return player.hasPermission("mobactions.command");
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
  public boolean canUseWarpsCommand() {
    return canUseWarpCommand();
  }

  @Override
  public boolean canSetWarp() {
    return player.hasPermission("mobactions.admin.warps.set");
  }

  @Override
  public boolean canRemoveWarp() {
    return player.hasPermission("mobactions.admin.warps.remove");
  }

  @Override
  public boolean canReload() {
    return player.hasPermission("mobactions.admin.reload");
  }
}
