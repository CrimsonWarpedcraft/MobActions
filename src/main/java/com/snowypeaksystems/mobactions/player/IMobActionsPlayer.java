package com.snowypeaksystems.mobactions.player;

import org.bukkit.entity.Player;

/**
 * An object that represents a player interacting with the plugin.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IMobActionsPlayer extends Editor, PermissionHolder {
  /** Returns the player represented by the IMPPlayer. */
  Player getPlayer();
}
