package com.snowypeaksystems.mobactions.player;

/**
 * Users that can run ConsoleCommands.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface ConsoleActionUser extends MobActionsUser {
  boolean canUseConsoleCommand();
}
