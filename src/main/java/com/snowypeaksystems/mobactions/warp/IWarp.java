package com.snowypeaksystems.mobactions.warp;

import com.snowypeaksystems.mobactions.data.AliasedData;
import com.snowypeaksystems.mobactions.data.FileData;
import org.bukkit.Location;

/**
 * An object to store the name and location of warps.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IWarp extends FileData, AliasedData {
  /** Returns the destination Location for this warp. */
  Location getDestination();
}
