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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the WarpManager class.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class WarpManagerTest {
  static private File file;
  private Location testLoc1;
  private Location testLoc2;
  private IWarpManager testWarpManager;
  private IWarp testWarp1;
  private IWarp testWarp2;
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
  @BeforeEach
  void start() {
    testLoc1 = new Location(new FakeWorld(), 0, 0, 0);
    testLoc2 = new Location(new FakeWorld(), 0, 0, 0);
    try {
      testWarpManager = new WarpManager(file);

    }catch(FileNotFoundException e) {
      fail();
    }
    testWarp1 = null;
    testWarp2 = null;
  }
  @Test
  void makeWarp() {
    // had to make the expected lowercase ?? is that cool
    testWarp1 = testWarpManager.makeWarp("testWarp1", testLoc1);
    assertEquals("testwarp1", testWarp1.getAlias());
    assertEquals(testLoc1, testWarp1.getDestination());

    testWarp2 = testWarpManager.makeWarp("testWarp2", testLoc2);
    assertEquals("testwarp2", testWarp2.getAlias());
    assertEquals(testLoc2, testWarp2.getDestination());

  }

  @Test
  void getWarp() {
    assertNull(testWarpManager.getWarp("testWarp1"));
    testWarp1 = testWarpManager.makeWarp("testWarp1", testLoc1);
    assertEquals(testWarp1, testWarpManager.getWarp("testWarp1"));
  }

  @Test
  void unregister() {
    //is this part redundant
    testWarp1 = testWarpManager.makeWarp("testWarp1", testLoc1);
    assertEquals(testWarp1, testWarpManager.getWarp("testWarp1"));

    testWarpManager.unregister("testWarp1");
    assertNull(testWarpManager.unregister("testWarp1"));
  }

  @Test
  void exists() {
    assertFalse(testWarpManager.exists("testWarp"));
    testWarpManager.makeWarp("testWarp", testLoc1);
    assertTrue(testWarpManager.exists("testWarp"));
  }

  @Test
  void getLoadedWarpNames() {
    assertEquals(0, testWarpManager.getLoadedWarpNames().size());

    testWarpManager.makeWarp("testWarp", testLoc1);
    testWarpManager.makeWarp("testWarp", testLoc2);
    assertTrue(testWarpManager.exists("testWarp"));
    assertTrue(testWarpManager.exists("testWarp"));

  }
}