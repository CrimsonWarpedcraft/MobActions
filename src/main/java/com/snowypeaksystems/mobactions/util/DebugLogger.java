package com.snowypeaksystems.mobactions.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;

/**
 * Logger for debugging information.
 * Only works if the "MA-DEBUG" environment variable is set to true.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class DebugLogger {
  private static DebugLogger loggerWrapper;
  private final Logger logger;

  /** Get the a logger object. */
  public static DebugLogger getLogger() {
    if (loggerWrapper == null) {
      loggerWrapper = new DebugLogger();
    }

    return loggerWrapper;
  }

  private DebugLogger() {
    logger = Bukkit.getLogger();
  }

  /** Logs the message to the console as a warning message. */
  public void log(String message) {
    String doLog = System.getenv("MA_DEBUG");
    if (doLog != null && doLog.equalsIgnoreCase("true")) {
      logger.log(Level.WARNING, "[DEBUG]" + message);
    }
  }
}
