package com.snowypeaksystems.mobportals;

import com.snowypeaksystems.mobportals.persistence.IMobWritable;
import com.snowypeaksystems.mobportals.warps.IWarp;
import java.util.concurrent.CompletableFuture;
import org.bukkit.entity.Player;

/**
 * An object that represents a player interacting with the plugin.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IMobPortalPlayer {
  enum Type {
    PORTAL,
    COMMAND,
    NONE
  }

  /**
   * Marks the IMPPlayer as creating something and stores the IMobWritable data, or unmarks them
   * if data is null.
   * @param type the type of creation being made
   * @param data data to store if the player is creating, or null
   */
  void setCreation(Type type, IMobWritable data);

  /** Retrieves the IMobWritable data if the IMPPlayer is creating, null otherwise. */
  IMobWritable getCreation();

  /** Returns the Type of creation the IMPPlayer is creating. */
  Type getCreationType();

  /** Returns true if creating, false otherwise. */
  boolean isCreating();

  /** Marks the player as destroying if true, or not destroying if false. */
  void setDestroying(boolean value);

  /** Returns true if destroying, false otherwise. */
  boolean isDestroying();

  /** Returns the player represented by the IMPPlayer. */
  Player getPlayer();

  /** Warps the player to the IWarp's destination. */
  void warp(IWarp warp);

  /** Warps the player to the IWarp's destination and complete the future on success. */
  void warp(IWarp warp, CompletableFuture<Boolean> future);
}
