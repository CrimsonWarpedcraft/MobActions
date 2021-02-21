package com.snowypeaksystems.mobactions.data;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.player.PlayerException;

public class IncompleteDataException extends PlayerException {
  public IncompleteDataException() {
    super(gm("mob-corrupt-error"));
  }
}
