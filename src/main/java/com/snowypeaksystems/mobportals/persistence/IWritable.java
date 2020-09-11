package com.snowypeaksystems.mobportals.persistence;

/**
 * An object that can be written and deleted.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface IWritable {
  /** Deletes the configuration from storage, if present. */
  boolean delete();
}
