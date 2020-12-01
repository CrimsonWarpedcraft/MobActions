package com.snowypeaksystems.mobactions.warp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.snowypeaksystems.mobactions.mock.FakeWorld;
import java.io.File;
import java.io.FileNotFoundException;
import org.bukkit.Location;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests for the WarpManager class.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class WarpManagerTest {
  private File file;
  @BeforeAll
  void setUp() {
    file = new File("test");
    file.mkdirs();
  }
  @AfterAll
  void tearDown() {
    if (file != null) {
      file.delete();
    }
  }
  @Test
  void makeWarp() {
    Location loc1 = new Location(new FakeWorld(), 0, 0, 0);
    Location loc2 = new Location(new FakeWorld(), 0, 0, 0);
    IWarpManager newWarpManager = null;
    try {
      newWarpManager = new WarpManager(file);
    } catch (FileNotFoundException e) {
      fail();
    }
    //assert null
    IWarp newWarp1 = newWarpManager.makeWarp("name", loc1);
    assertEquals(newWarp1.getAlias(), "name");
    assertEquals(newWarp1.getDestination(), loc1);

    IWarp newWarp2 = newWarpManager.makeWarp("name", loc2);
    assertEquals(newWarp2.getAlias(), "name");
    assertEquals(newWarp2.getDestination(), loc2);


  }
  @Test
  void getWarp() {
  }

  @Test
  void unregister() {
  }

  @Test
  void exists() {
  }

  @Test
  void getLoadedWarpNames() {
  }
}