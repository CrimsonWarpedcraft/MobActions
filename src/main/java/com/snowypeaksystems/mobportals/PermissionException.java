package com.snowypeaksystems.mobportals;

/**
 * Exception that is thrown whenever a user does not have required privileges.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class PermissionException extends Exception {
  private final String missingPermission;

  public PermissionException(String permission) {
    super("Missing permission " + permission);
    missingPermission = permission;
  }

  public String getMissingPermission() {
    return missingPermission;
  }
}
