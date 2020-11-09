package com.snowypeaksystems.mobactions.mob.actions;

/**
 * Action used to make a player perform an ICommandData command.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface ICommandAction extends MobAction {
  String COMMAND_KEY = "command";
  String COMMAND_ALIAS_KEY = "command-alias";
}