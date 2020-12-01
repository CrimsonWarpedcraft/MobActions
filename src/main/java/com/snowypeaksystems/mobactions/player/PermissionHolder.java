package com.snowypeaksystems.mobactions.player;

import com.snowypeaksystems.mobactions.data.ICommandData;
import com.snowypeaksystems.mobactions.warp.IWarp;

/**
 * Object that can hold plugin permissions.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface PermissionHolder {
  boolean canUseWarp(IWarp warp);

  boolean canRunCommand(ICommandData command);

  boolean canCreatePortal();

  boolean canCreateCommand();

  boolean canRemove();

  boolean canUseWarpCommand();

  boolean canSetWarp();

  boolean canRemoveWarp();

  boolean canReload();
}
