package com.snowypeaksystems.mobactions.player;

import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;

/**
 * Console based implementation of MobActionsUser capable of running commands.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandConsoleUser extends ConsoleUser {
  public CommandConsoleUser(ConsoleCommandSender console, Server server) {
    super(console, server);
  }

  @Override
  public boolean performCommand(String command) {
    // TODO Mason, implement
    return false;
  }
}
