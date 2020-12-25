package com.snowypeaksystems.mobactions.player;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

/**
 * Thrown if there is an error running a command.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandActionException extends PlayerException {
  public CommandActionException() {
    super(gm("command-error"));
  }
}
