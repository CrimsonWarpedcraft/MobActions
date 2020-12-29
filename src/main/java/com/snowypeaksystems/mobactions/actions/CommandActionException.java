package com.snowypeaksystems.mobactions.actions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Thrown if there is an error running a command.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandActionException extends PlayerException {
  public CommandActionException() {
    super(gm("command-error"));
  }
}
