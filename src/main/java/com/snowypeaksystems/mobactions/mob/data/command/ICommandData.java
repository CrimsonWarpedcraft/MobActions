package com.snowypeaksystems.mobactions.mob.data.command;

import com.snowypeaksystems.mobactions.mob.data.MobData;
import com.snowypeaksystems.mobactions.util.Substitutable;

/**
 * Objects that store tokenized command data.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface ICommandData extends Substitutable, MobData {
  String NAME_KEY = "command-name";
}
