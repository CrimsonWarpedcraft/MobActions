package com.snowypeaksystems.mobactions.command;

import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;

/**
 * Runs when a player issues a MobActions command.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface PlayerCommand {
  /** Runs the command with the given arguments. */
  void run(MobActionsUser player) throws PlayerException;
}
