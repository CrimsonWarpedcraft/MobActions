package com.snowypeaksystems.mobportals.persistence;

/**
 * Used to store data in IMobs.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IMobWritable extends IWritable {
  /** Returns the key that represents this object. */
  String getKey();
}
