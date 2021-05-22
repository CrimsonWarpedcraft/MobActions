package com.snowypeaksystems.mobactions.actions;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Actions that can be run from Mobs.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface MobAction {
  /** Executes the action. */
  void run(MobActionsUser player) throws PlayerException;
}
