package com.snowypeaksystems.mobactions.player;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

/**
 * Exception caused by insufficient permissions.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class PermissionException extends PlayerException {
  public PermissionException() {
    super(gm("permission-error"));
  }
}
