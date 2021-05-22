package com.snowypeaksystems.mobactions.mock;

import com.snowypeaksystems.mobactions.warp.Warp;
import java.io.IOException;
import org.bukkit.Location;

/**
 * Fake Warp implementation for testing.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class FakeWarp implements Warp {
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
