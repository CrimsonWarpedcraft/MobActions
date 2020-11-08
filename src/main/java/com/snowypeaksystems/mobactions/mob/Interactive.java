package com.snowypeaksystems.mobactions.mob;

/**
 * A mob that can be used to store MobAction data.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface Interactive {
  enum ActionType {
    PORTAL,
    COMMAND,
    NONE
  }

  ActionType getActionType();
}
