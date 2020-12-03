package com.snowypeaksystems.mobactions.warp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
  static private File file;

  @BeforeAll
  static void setUp() {
    file = new File("test");
    if (!file.mkdirs()) {
      fail();
    }
  }

  @AfterAll
  static void tearDown() {
    if (file == null || !file.delete()) {
      fail();
    }
  }

  @Test
  void makeWarp() {
    Location loc1 = new Location(new FakeWorld(), 0, 0, 0);
    IWarpManager newWarpManager;

    try {
      newWarpManager = new WarpManager(file);

      IWarp newWarp1 = newWarpManager.makeWarp("name", loc1);
      assertEquals(newWarp1.getAlias(), "name");
      assertEquals(newWarp1.getDestination(), loc1);

      Location loc2 = new Location(new FakeWorld(), 0, 0, 0);
      IWarp newWarp2 = newWarpManager.makeWarp("name", loc2);
      assertEquals(newWarp2.getAlias(), "name");
      assertEquals(newWarp2.getDestination(), loc2);

    } catch (FileNotFoundException e) {
      fail();
    }
  }

  @Test
  void getWarp() {
    Location loc1 = new Location(new FakeWorld(), 0, 0, 0);

    IWarpManager newWarpManager = null;
    try {
      newWarpManager = new WarpManager(file);
    } catch (FileNotFoundException e) {
      fail();
    }
    assertNull(newWarpManager.getWarp("name"));
    IWarp newWarp1 = newWarpManager.makeWarp("name", loc1);
    assertEquals(newWarpManager.getWarp("name"), newWarp1);
  }

  @Test
  void unregister() {
    Location loc1 = new Location(new FakeWorld(), 0, 0, 0);
    IWarpManager newWarpManager = null;
    try {
      newWarpManager = new WarpManager(file);
    } catch (FileNotFoundException e) {
      fail();
    }

    IWarp newWarp1 = newWarpManager.makeWarp("name", loc1);
    assertEquals(newWarp1, newWarpManager.getWarp("name"));

    newWarpManager.unregister("name");
    assertNull(newWarpManager.unregister("name"));
  }

  @Test
  void exists() {
    Location loc1 = new Location(new FakeWorld(), 0, 0, 0);
    IWarpManager newWarpManager = null;
    try {
      newWarpManager = new WarpManager(file);
    } catch (FileNotFoundException e) {
      fail();
    }

    assertFalse(newWarpManager.exists("name"));
    newWarpManager.makeWarp("name", loc1);
    assertTrue(newWarpManager.exists("name"));
  }

  @Test
  void getLoadedWarpNames() {
    Location loc1 = new Location(new FakeWorld(), 0, 0, 0);
    IWarpManager newWarpManager = null;
    try {
      newWarpManager = new WarpManager(file);
    } catch (FileNotFoundException e) {
      fail();
    }

    assertEquals(0, newWarpManager.getLoadedWarpNames().size());

    Location loc2 = new Location(new FakeWorld(), 0, 0, 0);
    newWarpManager.makeWarp("name1", loc1);
    newWarpManager.makeWarp("name2", loc2);
    assertTrue(newWarpManager.exists("name1"));
    assertTrue(newWarpManager.exists("name2"));

  }
}