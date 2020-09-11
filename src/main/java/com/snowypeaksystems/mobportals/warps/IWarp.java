package com.snowypeaksystems.mobportals.warps;

import com.snowypeaksystems.mobportals.persistence.IMobWritable;
import com.snowypeaksystems.mobportals.persistence.IStorageWritable;
import org.bukkit.Location;

/**
 * An object to store the name and location of warps.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IWarp extends IStorageWritable, IMobWritable {
  /** Returns the name of the warp. */
  String getName();

  /** Returns the location of the warp's destination. */
  Location getDestination();
}
