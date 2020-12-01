package com.snowypeaksystems.mobactions.data;

import com.snowypeaksystems.mobactions.util.Substitutable;

/**
 * Objects that store tokenized command data.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface ICommandData extends Substitutable, MobData {
  String COMMAND_KEY = "command";
  String COMMAND_ALIAS_KEY = "command-alias";
}
