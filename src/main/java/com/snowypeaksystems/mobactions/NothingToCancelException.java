package com.snowypeaksystems.mobactions;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Thrown when attempting to cancel without already editing.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class NothingToCancelException extends PlayerException {
  public NothingToCancelException() {
    super(gm("edit-cancel-error"));
  }
}
