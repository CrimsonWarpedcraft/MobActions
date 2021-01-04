package com.snowypeaksystems.mobactions.data;

/**
 * Objects that store tokenized command data.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface ICommandData extends MobData {
  char TOKEN_PREFIX = '{';
  char TOKEN_SUFFIX = '}';
  String COMMAND_KEY = "command";
  String COMMAND_DESCRIPTION_KEY = "command-description";

  /** Returns the command String with the token replaced by name. */
  String getCommand(String name);
}
