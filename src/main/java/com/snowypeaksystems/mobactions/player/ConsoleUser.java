package com.snowypeaksystems.mobactions.player;

import java.util.concurrent.CompletableFuture;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;

/**
 * Console based implementation of MobActionsUser.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class ConsoleUser implements MobActionsUser {
  private final ConsoleCommandSender console;

  public ConsoleUser(ConsoleCommandSender console) {
    this.console = console;
  }

  @Override
  public boolean canUseWarp(String warp) {
    return false;
  }

  @Override
  public boolean canRunCommand(String command) {
    return false;
  }

  @Override
  public boolean canCreate() {
    return false;
  }

  @Override
  public boolean canRemove() {
    return false;
  }

  @Override
  public boolean canUseWarpCommand() {
    return false;
  }

  @Override
  public boolean canSetWarp() {
    return false;
  }

  @Override
  public boolean canRemoveWarp() {
    return true;
  }

  @Override
  public boolean canReload() {
    return true;
  }

  @Override
  public IStatus getStatus() {
    throw new UnsupportedOperationException();
  }

  @Override
  public CompletableFuture<Boolean> teleport(Location location) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void sendMessage(String... messages) {
    console.sendMessage(messages);
  }

  @Override
  public boolean performCommand(String command) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Location getLocation() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getName() {
    return "Console";
  }
}
