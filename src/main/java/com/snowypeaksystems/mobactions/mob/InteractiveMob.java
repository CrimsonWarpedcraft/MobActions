package com.snowypeaksystems.mobactions.mob;

import org.bukkit.entity.LivingEntity;

public class InteractiveMob implements IInteractiveMob {
  //TODO: If data present, construct appropriate action based on data.

  @Override
  public void store() {

  }

  @Override
  public void purge() {

  }

  @Override
  public boolean exists() {
    return false;
  }

  @Override
  public LivingEntity getLivingEntity() {
    return null;
  }

  @Override
  public ActionType getActionType() {
    return null;
  }
}
