package com.snowypeaksystems.mobactions.player;

import com.snowypeaksystems.mobactions.mob.data.command.ICommand;
import com.snowypeaksystems.mobactions.mob.data.warp.IWarp;

/**
 * Object that can hold plugin permissions.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface PermissionHolder {
  boolean canUseWarp(IWarp warp);

  boolean canRunCommand(ICommand command);

  boolean canCreatePortal();

  boolean canCreateCommand();

  boolean canRemove();

  boolean canUseWarpCommand();

  boolean canSetWarp();

  boolean canRemoveWarp();

  boolean canReload();
}
