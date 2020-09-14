package com.snowypeaksystems.mobportals.mock;

import com.snowypeaksystems.mobportals.persistence.IMobWritable;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Dummy implementation of an IMobWritable.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
@SuppressFBWarnings
public class FakeMobWritable implements IMobWritable {

  @Override
  public String getKey() {
    return null;
  }
}
