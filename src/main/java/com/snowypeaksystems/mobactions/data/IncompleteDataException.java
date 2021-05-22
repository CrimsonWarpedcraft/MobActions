package com.snowypeaksystems.mobactions.data;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Thrown when an interactive mob does not have a complete data set.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class IncompleteDataException extends PlayerException {
  public IncompleteDataException() {
    super(gm("mob-corrupt-error"));
  }
}
