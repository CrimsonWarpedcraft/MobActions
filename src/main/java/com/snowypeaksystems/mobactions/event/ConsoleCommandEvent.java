package com.snowypeaksystems.mobactions.event;

/**
 * CommandEvents that may be runnable by the console.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface ConsoleCommandEvent extends CommandEvent {
  boolean isConsoleCommand();
}
