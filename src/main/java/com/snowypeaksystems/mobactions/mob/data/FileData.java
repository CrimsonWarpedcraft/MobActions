package com.snowypeaksystems.mobactions.mob.data;

import java.io.IOException;

/**
 * An object that can be written to storage.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface FileData {
  /** Writes the configuration to storage. */
  void save() throws IOException;

  /** Deletes the configuration from storage, if present. */
  boolean delete();
}
