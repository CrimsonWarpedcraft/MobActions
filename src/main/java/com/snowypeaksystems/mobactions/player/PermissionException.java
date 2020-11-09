package com.snowypeaksystems.mobactions.player;

/**
 * Exception caused by insufficient permissions.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class PermissionException extends PlayerException {
  @Override
  String getPlayerFormattedString() {
    return null;
  }
}