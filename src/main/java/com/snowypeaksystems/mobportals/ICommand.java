package com.snowypeaksystems.mobportals;

import com.snowypeaksystems.mobportals.messages.ITokenized;
import com.snowypeaksystems.mobportals.persistence.IMobWritable;

/**
 * Objects that store tokenized command data.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface ICommand extends IMobWritable, ITokenized {
  String TOKEN = "{}";

  /** Returns the name/short description of the ICommand. */
  String getName();
}
