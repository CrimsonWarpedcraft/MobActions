package com.snowypeaksystems.mobactions.player;

import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.warp.IWarp;
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
  public boolean canUseWarp(IWarp warp) {
    return false;
  }

  @Override
  public boolean canRunCommand(ICommandData command) {
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
  public void teleport(Location location) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void sendMessage(String... messages) {
    console.sendMessage(messages);
  }

  @Override
  public boolean performCommand(String command) {
    return false;
  }

  @Override
  public String getDisplayName() {
    return "Console";
  }
}
