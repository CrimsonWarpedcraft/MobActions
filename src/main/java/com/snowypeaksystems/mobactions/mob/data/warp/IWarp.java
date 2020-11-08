package com.snowypeaksystems.mobactions.mob.data.warp;

import com.snowypeaksystems.mobactions.mob.data.FileData;
import com.snowypeaksystems.mobactions.mob.data.MobData;
import org.bukkit.Location;

/**
 * An object to store the name and location of warps.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IWarp extends FileData, MobData {
  /** Returns the destination Location for this warp. */
  Location getDestination();
}
