package com.snowypeaksystems.mobactions.data;

/**
 * ICommandData that may be runnable from the console.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface ConsoleCommandData extends ICommandData {
  boolean isConsoleCommand();
}
