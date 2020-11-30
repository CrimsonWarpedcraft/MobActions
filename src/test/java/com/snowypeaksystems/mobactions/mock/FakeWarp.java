package com.snowypeaksystems.mobactions.mock;

import com.snowypeaksystems.mobactions.mob.data.warp.IWarp;
import java.io.IOException;
import org.bukkit.Location;

/**
 * Fake IWarp implementation for testing.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class FakeWarp implements IWarp {
  private String alias;

  public FakeWarp(String alias) {
    this.alias = alias;
  }

  @Override
  public Location getDestination() {
    return null;
  }

  @Override
  public void save() throws IOException {

  }

  @Override
  public boolean delete() {
    return false;
  }

  @Override
  public String getAlias() {
    return alias;
  }
}
